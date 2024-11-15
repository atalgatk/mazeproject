import org.junit.*;
import static org.junit.Assert.*;

public class TestStackAgenda{

    public MazeGridLocation mgl1=new MazeGridLocation(1, 1, '.');
    public MazeGridLocation mgl2=new MazeGridLocation(2, 2, '.');
    public MazeGridLocation mgl3=new MazeGridLocation(4, 3, '.');

    //sample stacks
    //----addLocation()----
    public StackAgenda OneItemList(){
        StackAgenda sa=new StackAgenda();
        sa.addLocation(mgl1);
        return sa;
    }
    public StackAgenda ThreeItemList(){
        StackAgenda sa=new StackAgenda();
        sa.addLocation(mgl1);
        sa.addLocation(mgl2);
        sa.addLocation(mgl3);
        return sa;
    }

    //----isEmpty()----
    @Test
    public void testIsEmpty1() {
        StackAgenda sa=new StackAgenda();
        assertTrue(sa.isEmpty());
    }
    @Test
    public void testIsEmpty2() {
        StackAgenda sa=OneItemList();
        assertFalse(sa.isEmpty());
    }
    @Test
    public void testIsEmpty3() {
        StackAgenda sa=ThreeItemList();
        assertFalse(sa.isEmpty());
    }

    //----removeLocation()----
    @Test
    public void testRemove_remove1from1ItemStack() {
        StackAgenda sa=OneItemList();
        sa.removeLocation();
        assertTrue("Should be empty after 1 removal",sa.isEmpty());
    }
    @Test
    public void testRemove_remove1from3itemStack() {
        StackAgenda sa=OneItemList();
        sa.addLocation(mgl1);
        sa.removeLocation();
        assertFalse("Shouldn't be empty after 1 removal ",sa.isEmpty());
    }
    //--remove correct MazeGridLocation--
    @Test
    public void testRemove3() {
        StackAgenda sa=ThreeItemList();
        MazeGridLocation rm = sa.removeLocation();
        assertEquals("Stack should follow LIFO removal precedure ",mgl3,rm);
    }
    @Test
    public void testRemove4(){
        StackAgenda sa=ThreeItemList();
        sa.removeLocation();
        MazeGridLocation rm = sa.removeLocation();
        assertEquals("Stack should follow LIFO removal precedure ",mgl2,rm);
    }
    @Test
    public void testRemove5(){
        StackAgenda sa = ThreeItemList();
        sa.removeLocation();
        sa.removeLocation();
        MazeGridLocation rm = sa.removeLocation();
        assertEquals("Stack should follow LIFO removal precedure ",mgl1,rm);
    }

    //----clear()----
    @Test
    public void testClear_1itemAgenda() {
        StackAgenda sa=OneItemList();
        sa.clear();
        assertTrue("StackAgenda should be empty after .clear() ",sa.isEmpty());
    }
    @Test
    public void testClear_10itemAgenda() {
        StackAgenda sa=new StackAgenda();
        MazeGridLocation loc1 =new MazeGridLocation(1, 1, 'c');
        for(int i=0;i<10;i++){
            sa.addLocation(loc1);
        }
        sa.clear();
        assertTrue("StackAgenda should be empty after .clear() ",sa.isEmpty());
    }
    @Test
    public void testClear_emptyAgenda() {
        StackAgenda sa=new StackAgenda();
        sa.clear();
        assertTrue("StackAgenda should be empty after .clear() ",sa.isEmpty());
    }
    @Test
    public void testClear_toStringShowsEmpty() {
        StackAgenda sa=new StackAgenda();
        MazeGridLocation loc1 =new MazeGridLocation(1, 1, 'c');
        for(int i=0;i<10;i++){
            sa.addLocation(loc1);
        }
        sa.clear();
        String ts=sa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue("StackAgenda toString() should be empty after .clear() ",ts.equals(""));
    }

    //----toString()----
    @Test
    public void testToString1(){
        StackAgenda sa=OneItemList();
        String ts=sa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue(ts.equals("11"));

    }
    @Test
    public void testToString2(){
        StackAgenda sa=ThreeItemList();
        String ts=sa.toString();
        ts=ts.replaceAll("[^\\d.]", "");
        assertTrue(ts.equals("112243"));
    }

}