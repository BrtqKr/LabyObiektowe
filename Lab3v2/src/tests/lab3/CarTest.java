package lab3;

 import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CarTest
{
    Car testcar=new Car();

    @Test
    public void inittest()
    {
        Position p= new Position(2,2);
        assertEquals(p,testcar.getPosition());
        assertEquals(MapDirection2.NORTH,testcar.getOrient());
    }
    @Test
    public void tostringtest()
    {
        assertEquals("(2,2) NORTH",testcar.toString());
    }
    @Test
    public void movetest()
    {
        testcar.move(MoveDirection.LEFT);
        assertEquals(MapDirection2.WEST,testcar.getOrient());
        testcar.move(MoveDirection.FORWARD);
        assertEquals(new Position(1,2),testcar.getPosition());
        testcar.move(MoveDirection.LEFT);
        testcar.move(MoveDirection.FORWARD);
        testcar.move(MoveDirection.FORWARD);
        testcar.move(MoveDirection.FORWARD);
        assertEquals(new Position(1,0),testcar.getPosition());
        assertEquals(MapDirection2.SOUTH,testcar.getOrient());
    }

}
