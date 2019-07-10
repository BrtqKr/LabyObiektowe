package agh.cs.lab2;

import org.junit.Test;

import java.lang.reflect.MalformedParameterizedTypeException;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest
{
    @Test
    public void mdirt()
    {
        assertEquals(MapDirection2.EAST,MapDirection2.NORTH.next());
        assertEquals(MapDirection2.WEST,MapDirection2.NORTH.previous());
        assertEquals(MapDirection2.SOUTH,MapDirection2.EAST.next());
        assertEquals(MapDirection2.WEST,MapDirection2.SOUTH.next());
        assertEquals(MapDirection2.SOUTH,MapDirection2.WEST.previous());
    }
    @Test
    public void tostring_test()
    {
        assertEquals(MapDirection2.NORTH.ToString(),"NORTH");
        assertEquals(MapDirection2.SOUTH.ToString(),"SOUTH");
        assertEquals(MapDirection2.WEST.ToString(),"WEST");
        assertEquals(MapDirection2.EAST.ToString(),"EAST");
    }
}
