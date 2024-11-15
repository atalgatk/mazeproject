import java.util.ArrayList;

class MazeSolver {
    Agenda agenda;

    public MazeSolver(Agenda a) {
        agenda = a;
    }

    public ArrayList<MazeGridLocation> solveMaze(Maze m, MazeGUI mg) {
        ArrayList<MazeGridLocation> correctPath = new ArrayList<MazeGridLocation>();
        ArrayList<MazeGridLocation> backwardsPath = new ArrayList<MazeGridLocation>();

        int rows = m.getNumRows();
        int cols = m.getNumColumns();
        MazeGridLocation currLoc = m.getStartLocation();
        boolean goal = false;
        boolean complete = false;

        MazeGridLocation[][] maze = new MazeGridLocation[rows][cols];
        agenda.addLocation(currLoc);

        while (!goal && currLoc != null) {
            m.markVisited(currLoc);
            mg.addLocToAgenda(currLoc);
            mg.visitLoc(currLoc);
            agenda.removeLocation();

            char currChar = currLoc.character;

            if (currChar != '*') {
                for (MazeGridLocation loc : m.getOpenNeighbors(currLoc)) {
                    char neighborChar = loc.character;
                    if (loc != null && !loc.isWall() && !m.isVisited(loc) && (cols >= loc.getColumn()) && (rows >= loc.getRow())) {
                        agenda.addLocation(loc);
                        mg.addLocToPath(currLoc);
                        maze[loc.row][loc.col] = currLoc;
                    }
                }
                currLoc = agenda.getLocation();
            } else {
                goal = true; 
            }
        }

        if (currLoc != null) {
            int temp = 0;
            int currRow = currLoc.row;
            int currCol = currLoc.col;

            backwardsPath.add(currLoc);
            while (!complete && currLoc != null) {
                backwardsPath.add(maze[currRow][currCol]);
                if (currRow == m.getStartLocation().row && currCol == m.getStartLocation().col) {
                    complete = true;
                } else {
                    temp = currRow;
                    currRow = maze[currRow][currCol].row;
                    currCol = maze[temp][currCol].col;
                }
            }

            backwardsPath.remove(backwardsPath.size() - 1);
            for (int i = backwardsPath.size() - 1; i >= 0; i--) {
                correctPath.add(backwardsPath.get(i));
            }
        }

        return correctPath; 
    }
} {
    
}
