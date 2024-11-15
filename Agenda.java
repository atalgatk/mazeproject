import java.util.ArrayList;

abstract class Agenda{
	abstract public ArrayList<MazeGridLocation> getAgenda();

	abstract public void addLocation(MazeGridLocation loc);
	
	abstract public MazeGridLocation removeLocation();
	
	abstract public MazeGridLocation getLocation(); 

	abstract public boolean isEmpty();

	abstract public String toString();
	
	abstract public void clear(); 
}