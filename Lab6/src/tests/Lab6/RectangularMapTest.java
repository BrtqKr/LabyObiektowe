package Lab6;

import org.junit.Test;
import agh.cs.lab6.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import static org.junit.Assert.assertEquals;

public class RectangularMapTest
{
    @Test
    public void initTest()
    {
        RectangularMap map=new RectangularMap(5,5);
        assertEquals(5,map.getHeight());
        assertEquals(5,map.getWidth());
    }
    @Test
    public void integrationTest()
    {
        RectangularMap map=new RectangularMap(5,5);
        assertEquals(false,map.isOccupied(new Position(2,2)));
        Car testCar=new Car(map); //pozycja domyslna (2,2)
        //placement test
        assertEquals(false,map.isOccupied(new Position(2,2)));
        map.place(testCar);
        assertEquals(true,map.isOccupied(new Position(2,2)));
        //borders test
        assertEquals(true,map.canMoveTo(new Position(4,4)));
        assertEquals(false,map.canMoveTo(new Position(5,5)));
        assertEquals(true,map.canMoveTo(new Position(0,0)));
        assertEquals(false,map.canMoveTo(new Position(-1,-1)));
        //objectAt test
        assertEquals(testCar,map.objectAt(new Position(2,2)));
        //null test
        assertEquals(null,map.objectAt(new Position(1,1)));

    }
    @Test
    public void runTest()throws InterruptedException
    {
        RectangularMap runMap=new RectangularMap(5,5);
        Car papaMobile1=new Car(runMap);
        Car papaMobile2=new Car(runMap,new Position(4,4));
        runMap.place(papaMobile1);
        runMap.place(papaMobile2);
        String tab[] = {"f", "b", "r", "l", "f","r","f","l","f","f"};
        MoveDirection[] testDirections = new OptionsParser().parse(tab);
        runMap.run(testDirections);
        assertEquals(new Position(3,3),papaMobile1.getPosition());
        assertEquals(MapDirection2.EAST,papaMobile1.getOrientacja());
        assertEquals(new Position(4,3),papaMobile2.getPosition());
        assertEquals(MapDirection2.WEST,papaMobile2.getOrientacja());
    }
}
