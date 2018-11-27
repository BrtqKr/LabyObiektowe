package Lab7;

import agh.cs.lab7.Car;
import agh.cs.lab7.HayStack;
import agh.cs.lab7.Position;
import agh.cs.lab7.UnboundedMapv2;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnboundedMapTest
{
    @Test
    public void initialTest()
    {
        ArrayList<HayStack>stacks=new ArrayList<>();
        HayStack testStack1=new HayStack(new Position(2,2));
        HayStack testStack2=new HayStack(new Position(5,5));
        stacks.add(testStack1);
        stacks.add(testStack2);
        UnboundedMapv2 testMap=new UnboundedMapv2(stacks);
        assertEquals(new Position(2,2),testMap.getBottomLeft());
        assertEquals(new Position(5,5),testMap.getUpperRight());
    }
    @Test
    public  void CanMoveToTest()
    {
        ArrayList<HayStack>stacks=new ArrayList<>();
        HayStack testStack1=new HayStack(new Position(2,2));
        HayStack testStack2=new HayStack(new Position(5,5));
        stacks.add(testStack1);
        stacks.add(testStack2);
        UnboundedMapv2 testMap=new UnboundedMapv2(stacks);
        assertEquals(true,testMap.isOccupied(new Position(2,2)));
        assertEquals(true,testMap.isOccupied(new Position(5,5)));
        assertEquals(false,testMap.isOccupied(new Position(3,3)));
        Car testcar=new Car(testMap,new Position(3,3));
        testMap.place(testcar);
        assertEquals(true,testMap.isOccupied(new Position(3,3)));
    }
    @Test
    public void isOccupiedTest()
    {
        ArrayList<HayStack>stacks=new ArrayList<>();
        HayStack testStack1=new HayStack(new Position(2,2));
        HayStack testStack2=new HayStack(new Position(5,5));
        stacks.add(testStack1);
        stacks.add(testStack2);
        UnboundedMapv2 testMap=new UnboundedMapv2(stacks);
        assertEquals(false,testMap.isOccupied(new Position(3,3)));
        Car testcar=new Car(testMap,new Position(3,3));
        testMap.place(testcar);
        assertEquals(true,testMap.isOccupied(new Position(2,2)));
        assertEquals(false,testMap.isOccupied(new Position(4,4)));
        assertEquals(true,testMap.isOccupied(new Position(3,3)));
    }
    @Test
    public void objectAtTest()
    {
        ArrayList<HayStack>stacks=new ArrayList<>();
        HayStack testStack1=new HayStack(new Position(2,2));
        HayStack testStack2=new HayStack(new Position(5,5));
        stacks.add(testStack1);
        stacks.add(testStack2);
        UnboundedMapv2 testMap=new UnboundedMapv2(stacks);
        Car testcar=new Car(testMap,new Position(3,3));
        testMap.place(testcar);
        assertEquals(null,testMap.objectAt(new Position(3,4)));
        assertEquals(testStack1,testMap.objectAt(new Position(2,2)));
        assertEquals(testcar,testMap.objectAt(new Position(3,3)));
    }
}
