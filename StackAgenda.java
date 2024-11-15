import java.util.ArrayList;

public class StackAgenda extends Agenda{
    ArrayList<MazeGridLocation> agenda;

    StackAgenda(){
        agenda = new ArrayList<MazeGridLocation>();
    }

    @Override
    public ArrayList<MazeGridLocation> getAgenda() {
        return agenda;
    }

    @Override
    public void addLocation(MazeGridLocation loc) {
        agenda.add(loc);
    }

    @Override
    public MazeGridLocation removeLocation() {
        if (!agenda.isEmpty()) {
            return agenda.remove(agenda.size() - 1); 
        } else {
            return null; 
        }
    }

    @Override
    public MazeGridLocation getLocation() {
        if (!agenda.isEmpty()) {
            return agenda.get(agenda.size() - 1); 
        } else {
            return null; 
        }
    }

    @Override
    public boolean isEmpty() {
        return agenda.isEmpty();
    }

    @Override
    public String toString() {
        return agenda.toString();
    }

    @Override
    public void clear() {
        agenda.clear();
    }

    public MazeGridLocation getLocation(int index) {
        return agenda.get(index);
    }

    public int getSize() {
        return agenda.size();
    }

    public static void main(String[] args) {
        StackAgenda dataStruc = new StackAgenda();

        MazeGridLocation a = new MazeGridLocation(1, 1, 'o');
        MazeGridLocation b = new MazeGridLocation(1, 2, '.');
        MazeGridLocation c = new MazeGridLocation(1, 3, '#');

        dataStruc.addLocation(a);
        dataStruc.addLocation(b);
        dataStruc.addLocation(c);
        System.out.println(dataStruc.toString()); //[(1, 1), (1, 2), (1, 3)]

        dataStruc.removeLocation();
        System.out.println(dataStruc.toString()); //[(1, 1), (1, 2)]

        System.out.println(dataStruc.getLocation(0).toString()); //(1, 1)

        dataStruc.clear();
        System.out.println(dataStruc.isEmpty()); 
    }
}
