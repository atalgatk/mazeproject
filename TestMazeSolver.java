import org.junit.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestMazeSolver {
    // start at o, end at *

    public ArrayList<MazeGridLocation> maze1Solution() {
        ArrayList<MazeGridLocation> lst = new ArrayList<MazeGridLocation>();
        MazeGridLocation startLoc = new MazeGridLocation(1, 5, 'o');
        MazeGridLocation loc1 = new MazeGridLocation(2, 5, '.');
        MazeGridLocation loc2 = new MazeGridLocation(2, 4, '.');
        MazeGridLocation loc3 = new MazeGridLocation(2, 3, '.');
        MazeGridLocation loc4 = new MazeGridLocation(1, 3, '.');
        MazeGridLocation loc5 = new MazeGridLocation(1, 2, '.');
        MazeGridLocation loc6 = new MazeGridLocation(1, 1, '.');
        MazeGridLocation goalLoc = new MazeGridLocation(2, 1, '*');
        lst.add(startLoc);
        lst.add(loc1);
        lst.add(loc2);
        lst.add(loc3);
        lst.add(loc4);
        lst.add(loc5);
        lst.add(loc6);
        lst.add(goalLoc);
        return lst;
    }

    @Test
    public void noPath() {
        try {
            Maze m = new Maze("mazefile_nopath.txt");
            MazeGUI mg = new MazeGUI(m);
            StackAgenda a = new StackAgenda();
            MazeSolver ms = new MazeSolver(a);
            ArrayList<MazeGridLocation> solution = ms.solveMaze(m, mg);
            assertTrue(solution.isEmpty());
        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    @Test
    public void startIncluded() {
        try {
            Maze m = new Maze("mazefile1.txt");
            MazeGUI mg = new MazeGUI(m);
            QueueAgenda a = new QueueAgenda();
            MazeSolver ms = new MazeSolver(a);
            ArrayList<MazeGridLocation> actual = ms.solveMaze(m, mg);
            MazeGridLocation start = new MazeGridLocation(1, 5, 'o');
            assertEquals(start, actual.get(0));

        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    @Test
    public void goalIncluded() {
        try {
            Maze m = new Maze("mazefile1.txt");
            MazeGUI mg = new MazeGUI(m);
            QueueAgenda a = new QueueAgenda();
            MazeSolver ms = new MazeSolver(a);
            ArrayList<MazeGridLocation> actual = ms.solveMaze(m, mg);
            MazeGridLocation goal = new MazeGridLocation(2, 1, '*');
            assertEquals(goal, actual.get(actual.size() - 1));

        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    @Test
    public void mazefile1PathSize() {
        try {
            Maze m = new Maze("mazefile1.txt");
            MazeGUI mg = new MazeGUI(m);
            QueueAgenda a = new QueueAgenda();
            MazeSolver ms = new MazeSolver(a);
            ArrayList<MazeGridLocation> actual = ms.solveMaze(m, mg);
            assertEquals(8, actual.size());

        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    @Test
    public void mazefile1Path() {
        try {
            Maze m = new Maze("mazefile1.txt");
            MazeGUI mg = new MazeGUI(m);
            StackAgenda a = new StackAgenda();
            MazeSolver ms = new MazeSolver(a);
            ArrayList<MazeGridLocation> expected = maze1Solution();
            ArrayList<MazeGridLocation> actual = ms.solveMaze(m, mg);
            for (int i = 0; i < expected.size(); i++) {
                assertEquals("" + i, expected.get(i).row, actual.get(i).row);
                assertEquals("" + i, expected.get(i).col, actual.get(i).col);
            }
        } catch (FileNotFoundException e) {
            fail("File not found");
        }
    }

    // lifted from tim 10032
    public boolean areNeighbor(MazeGridLocation mgl1, MazeGridLocation mgl2) {
        if (mgl1.row == mgl2.row && Math.abs(mgl1.col - mgl2.col) == 1) {
            return true;
        } else if (mgl1.col == mgl2.col && Math.abs(mgl1.row - mgl2.row) == 1) {
            return true;
        }
        return false;
    }

    public Maze runStackMS(String str) {
        Maze maze = null;
        try {
            maze = new Maze(str);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file: " + str);
        }
        assertTrue(maze != null);
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new StackAgenda();
        MazeSolver ms = new MazeSolver(ag);
        ms.solveMaze(maze, mgui);
        return maze;
    }

    public Maze runQueueMS(String str) {
        Maze maze = null;
        try {
            maze = new Maze(str);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file: " + str);
        }
        assertTrue(maze != null);
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new QueueAgenda();
        MazeSolver ms = new MazeSolver(ag);
        ms.solveMaze(maze, mgui);
        return maze;
    }

    public ArrayList<MazeGridLocation> solveStack(String str) {
        Maze maze = null;
        try {
            maze = new Maze(str);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file: " + str);
        }
        assertTrue(maze != null);
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new StackAgenda();
        MazeSolver ms = new MazeSolver(ag);
        return ms.solveMaze(maze, mgui);
    }

    public ArrayList<MazeGridLocation> solveQueue(String str) {
        Maze maze = null;
        try {
            maze = new Maze(str);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file: " + str);
        }
        assertTrue(maze != null);
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new QueueAgenda();
        MazeSolver ms = new MazeSolver(ag);
        return ms.solveMaze(maze, mgui);
    }

    @Test
    public void areStartAndGoalVisitedS_mazefile1() {
        Maze m = runStackMS("mazefile1.txt");
        assertTrue("Should have visited start location ", m.isVisited(m.getStartLocation()));
        assertTrue("Should have visited goal location", m.isVisited(m.getGoalLocation()));
    }

    @Test
    public void areStartAndGoalVisitedQ_mazefile1() {
        Maze m = runQueueMS("mazefile1.txt");
        assertTrue("Should have visited start location", m.isVisited(m.getStartLocation()));
        assertTrue("Should have visited goal location", m.isVisited(m.getGoalLocation()));
    }

    @Test
    public void areStartAndGoalVisitedS_cantReachGoal() {
        Maze m = runStackMS("cantReach.txt");
        assertTrue("Should have visited start location", m.isVisited(m.getStartLocation()));
        assertFalse("Shouldn't have visited goal location", m.isVisited(m.getGoalLocation()));
    }

    @Test
    public void areStartAndGoalVisitedQ_cantReachGoal() {
        Maze m = runQueueMS("cantReach.txt");
        assertTrue("Should have visited start location", m.isVisited(m.getStartLocation()));
        assertFalse("Shouldn't have visited goal location", m.isVisited(m.getGoalLocation()));
    }

    @Test
    public void TestClear_cantReachGoal() {
        // this test gives the program a maze that can't be solved if starting
        // at the proper start point, but the test feeds the agenda a point that WILL
        // allow the goal to be found. the goal should NOT be found because the agenda
        // should
        // be cleared/confirmed to be empty before the program begins to solve the maze

        // when put up against the solution code, this test returns a null pointer
        // exception

        // FIXED!! >>> solution code was not checking for this case scenario, added an
        // if(!a.isEmpty)
        // before the startLocation was added

        // should be noted that the test crashed rather than having an assertion error
        Maze maze = null;
        try {
            maze = new Maze("cantReach.txt");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file");
        }
        assertTrue("cantReach maze should initialize ", maze != null);
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new QueueAgenda();
        MazeSolver ms = new MazeSolver(ag);
        MazeGridLocation mgl = maze.getLocation(2, 3);
        ag.addLocation(mgl);
        try {
            ms.solveMaze(maze, mgui);
        } catch (Exception e) {
            fail("Exception " + e.getMessage()
                    + " occurred.  The goal is not reachable if the agenda is empty when starting the maze solver");
        }
        assertFalse("Agenda should be cleared before solving new maze ", maze.isVisited(maze.getGoalLocation()));

    }

    @Test
    public void TestStack2by2() {
        Maze m = runStackMS("simpleMaze.txt");
        assertFalse("Improper stack behavior: shouldn't have visited (1,0) ", m.isVisited(m.getLocation(1, 0)));
        assertTrue("Improper stack behavior: should have visited (0,1) ", m.isVisited(m.getLocation(0, 1)));
    }

    @Test
    public void TestQueue2by2() {
        Maze m = runQueueMS("simpleMaze.txt");
        assertTrue("Improper queue behavior: should have visited (1,0) ", m.isVisited(m.getLocation(1, 0)));
        assertTrue("Improper queue behavior: should have visited (0,1) ", m.isVisited(m.getLocation(0, 1)));
    }

    @Test
    public void TestQ_straightPath() {
        ArrayList<MazeGridLocation> queue = solveQueue("openMaze.txt");
        MazeGridLocation one = new MazeGridLocation(2, 2, 'c');// (2,2)
        MazeGridLocation two = new MazeGridLocation(1, 2, 'c');// (1,2)
        MazeGridLocation three = new MazeGridLocation(0, 2, 'c');
        ArrayList<MazeGridLocation> path = new ArrayList<MazeGridLocation>();
        path.add(one);
        path.add(two);
        path.add(three);
        assertEquals("QueueAgenda should return shortest path to goal ", path, queue);
    }

    @Test
    public void qPath1() {
        Maze maze = null;
        try {
            maze = new Maze("openMaze.txt");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Cannot find maze file");
        }
        MazeGUI mgui = new MazeGUI(maze);
        Agenda ag = new QueueAgenda();
        MazeSolver ms = new MazeSolver(ag);
        MazeGridLocation topLCorner = maze.getLocation(0, 0);
        MazeGridLocation bottomLCorner = maze.getLocation(4, 0);
        MazeGridLocation topRCorner = maze.getLocation(0, 4);
        MazeGridLocation bottomRCorner = maze.getLocation(4, 4);
        MazeGridLocation[] qShouldntVisit = { topLCorner, bottomLCorner, topRCorner, bottomRCorner };
        ms.solveMaze(maze, mgui);
        for (int i = 0; i < qShouldntVisit.length; i++) {
            assertFalse("" + qShouldntVisit[i] + " shouldn't have been visited by queue ",
                    maze.isVisited(qShouldntVisit[i]));
        }
    }

    @Test
    public void checkStartIsFirstS() {
        ArrayList<MazeGridLocation> stack = solveStack("mazefile3.txt");
        MazeGridLocation start = new MazeGridLocation(0, 0, 'c');
        assertEquals("start is first MGL in stack", start, stack.get(0));
    }

    @Test
    public void checkGoalIsLastS() {// what is this setup
        ArrayList<MazeGridLocation> stack = solveStack("mazefile3.txt");
        MazeGridLocation goal = new MazeGridLocation(4, 5, 'c');
        assertEquals("goal is last MGL in stack ", goal, stack.get(stack.size() - 1));
    }

    @Test
    public void checkStartIsFirstQ() {
        ArrayList<MazeGridLocation> stack = solveQueue("mazefile3.txt");
        MazeGridLocation start = new MazeGridLocation(0, 0, 'c');
        assertEquals("start is first MGL in queue", start, stack.get(0));

    }

    @Test
    public void checkGoalIsLastQ() {
        ArrayList<MazeGridLocation> stack = solveStack("mazefile3.txt");
        MazeGridLocation goal = new MazeGridLocation(4, 5, 'c');
        assertEquals("goal is last MGL in queue ", goal, stack.get(stack.size() - 1));
    }

    @Test
    public void checkPathIsntBackwardsS() {
        ArrayList<MazeGridLocation> stack = solveStack("mazefile3.txt");
        MazeGridLocation test1 = new MazeGridLocation(4, 5, 'c');
        MazeGridLocation test2 = new MazeGridLocation(0, 0, 'c');
        assertTrue("Stack should initialize", stack != null);
        boolean pass = true;
        if (test1.equals(stack.get(0)) || test2.equals(stack.get(stack.size() - 1))) {
            pass = false;
            fail("Stack path should not be backwards, should start with start point and end with goal. ");
        } else {
            assertTrue("Stack path seems to be in correct order", pass);
        }
    }

    @Test
    public void checkPathIsntBackwardsQ() {
        ArrayList<MazeGridLocation> queue = solveQueue("mazefile3.txt");
        MazeGridLocation test1 = new MazeGridLocation(4, 5, 'c');
        MazeGridLocation test2 = new MazeGridLocation(0, 0, 'c');
        assertTrue("Queue should initialize", queue != null);
        boolean pass = true;
        if (test1.equals(queue.get(0)) || test2.equals(queue.get(queue.size() - 1))) {
            pass = false;
            fail("Queue path should not be backwards, should start with start point and end with goal.");
        } else {
            assertTrue("Queue path seems to be in correct order", pass);
        }
    }

    @Test
    public void checkIsPathConnectedS() {
        ArrayList<MazeGridLocation> stack = solveStack("mazefile3.txt");
        MazeGridLocation curr = stack.get(0);
        for (int i = 1; i < stack.size(); ++i) {
            assertTrue("Successive MGLs don't seem to be neighbors ", areNeighbor(curr, stack.get(i)));
            curr = stack.get(i);
        }
    }

    @Test
    public void checkIsPathConnectedQ() {
        ArrayList<MazeGridLocation> queue = solveQueue("mazefile3.txt");
        MazeGridLocation curr = queue.get(0);
        for (int i = 1; i < queue.size(); ++i) {
            assertTrue("Successive MGLs don't seem to be neighbors ", areNeighbor(curr, queue.get(i)));
            curr = queue.get(i);
        }
    }
}