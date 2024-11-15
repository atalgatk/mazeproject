import org.junit.*;
import static org.junit.Assert.*;

public class TestQueueAgenda{
    
    public MazeGridLocation mgl1=new MazeGridLocation(1, 1, '.');
    public MazeGridLocation mgl2=new MazeGridLocation(2, 2, '.');
    public MazeGridLocation mgl3=new MazeGridLocation(4, 3, '.');
    
    //sample queues
    //----addLocation()----
    public QueueAgenda OneItemList(){
        QueueAgenda qa=new QueueAgenda();
        qa.addLocation(mgl1);
        return qa;
    }
    public QueueAgenda ThreeItemList(){
        QueueAgenda qa=new QueueAgenda();
        qa.addLocation(mgl1);
        qa.addLocation(mgl2);
        qa.addLocation(mgl3);
        return qa;
    }

    //----isEmpty()----
    @Test
    public void testIsEmpty1() {
        QueueAgenda qa=new QueueAgenda();
        assertTrue(qa.isEmpty());
    }
    @Test
    public void testIsEmpty2() {
        QueueAgenda qa=OneItemList();
        assertFalse(qa.isEmpty());
    }
    @Test
    public void testIsEmpty3() {
        QueueAgenda qa=ThreeItemList();
        assertFalse(qa.isEmpty());
    }

    //----removeLocation()----
    @Test
    public void testRemove_remove1from1ItemQueue() {
        QueueAgenda qa=OneItemList();
        qa.removeLocation();
        assertTrue("Should be empty after 1 removal ",qa.isEmpty());
    }
    @Test
    public void testRemove_remove1from3ItemQueue() {
        QueueAgenda qa=OneItemList();
        qa.addLocation(mgl1);
        qa.removeLocation();
        assertFalse("Shouldn't be empty after 1 removal ",qa.isEmpty());
    }
    //--remove correct MazeGridLocation--
    @Test
    public void testRemove3() {
        QueueAgenda qa=ThreeItemList();
        MazeGridLocation rm = qa.removeLocation();
        assertEquals("Queue should follow FIFO removal precedure ",mgl1,rm);
    }
    @Test
    public void testRemove4(){
        QueueAgenda qa=ThreeItemList();
        qa.removeLocation();
        MazeGridLocation rm = qa.removeLocation();
        assertEquals("Queue should follow FIFO removal precedure ",mgl2, rm);
    }
    @Test
    public void testRemove5(){
        QueueAgenda qa = ThreeItemList();
        qa.removeLocation();
        qa.removeLocation();
        MazeGridLocation rm = qa.removeLocation();
        assertEquals("Queue should follow FIFO removal precedure ",mgl3,rm);
    }

    //----clear()----
    @Test
    public void testClear_10itemAgenda() {
        QueueAgenda qa=OneItemList();
        qa.clear();
        assertTrue("QueueAgenda should be empty after .clear() ",qa.isEmpty());
    }
    @Test
    public void testClear_1itemAgenda() {
        QueueAgenda qa=new QueueAgenda();
        MazeGridLocation loc1 =new MazeGridLocation(1, 1, 'c');
        for(int i=0;i<10;i++){
            qa.addLocation(loc1);
        }
        qa.clear();
        assertTrue("QueueAgenda should be empty after .clear() ",qa.isEmpty());
    }
    @Test
    public void testClear_emptyAgenda() {
        QueueAgenda qa=new QueueAgenda();
        qa.clear();
        assertTrue("QueueAgenda should be empty after .clear() ",qa.isEmpty());
    }
    @Test
    public void testClear_toStringShowsEmpty() {
        QueueAgenda qa=new QueueAgenda();
        MazeGridLocation loc1 =new MazeGridLocation(1, 1, 'c');
        for(int i=0;i<10;i++){
            qa.addLocation(loc1);
        }
        qa.clear();
        String ts=qa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue("QueueAgenda toString() should be empty after .clear() ",ts.equals(""));
    }

    //----toString()----
    @Test
    public void testToString1(){
        QueueAgenda qa=OneItemList();
        String ts=qa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue(ts.equals("11"));

    }
    @Test
    public void testToString2(){
        QueueAgenda qa=ThreeItemList();
        String ts=qa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue(ts.equals("112243"));
    }

}