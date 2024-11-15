import java.util.ArrayList;

public class QueueAgenda extends Agenda {
    ArrayList<MazeGridLocation> agenda;

    QueueAgenda() {
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
        return agenda.remove(0);
    }

    public MazeGridLocation getLocation(int index) {
        return agenda.get(index);
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

    @Override
    public MazeGridLocation getLocation() {
		if (!isEmpty()) {
			return agenda.get(0);
		} else {
			return null; 
		}
	}

    public static void main(String[] args) {
        QueueAgenda dataStruc = new QueueAgenda();

        MazeGridLocation a = new MazeGridLocation(1, 1, 'o');
        MazeGridLocation b = new MazeGridLocation(1, 2, '.');
        MazeGridLocation c = new MazeGridLocation(1, 3, '#');

        dataStruc.addLocation(a);
        dataStruc.addLocation(b);
        dataStruc.addLocation(c);
        System.out.println(dataStruc.toString()); // [(1, 1), (1, 2), (1, 3)]

        dataStruc.removeLocation();
        System.out.println(dataStruc.toString()); // [(1, 2), (1, 3)]

        System.out.println(dataStruc.getLocation(0).toString()); // (1, 2)

        dataStruc.clear();
        System.out.println(dataStruc.isEmpty()); 
    }
}
