/*package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PositionTest
{
    Position position1=new Position(1,2);
    Position position2=new Position(0,1);
    @Test
    public void tostringtest()
    {
        String s=position1.toString();
        as
    }
   @Test
    public void smallertest()
    {
        boolean b=position1.smaller(position2);
        assertFalse(b);

    }
    @Test
    public void largertest()
    {
        boolean b=position1.smaller(position2);
        assertTrue(b);
    }
    @Test
    public void upperrighttest()
    {
        String s=position1.upperRight(position2).toString();
        assertEquals("(1,2)",s);
    }
    @Test
    public void lowerlefttest()
    {
        String s=position1.upperRight(position2).toString();
        assertEquals("(0,1)",s);
    }
    @Test
    public void addtest()
    {
        String s=position1.add(position2).toString();
        assertEquals("(1,3)",s);
    }
}
*/
package agh.cs.lab2;

/**
 * Created by Student39 on 2018-10-09.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest
{
    Position a = new Position(1, 3);
    Position b = new Position(2, 5);

    @Test
    public void testToString() {
        String str = a.ToString();
        assertEquals("(1,3)",str);
    }

    @Test
    public void testSmaller() {
        boolean wynik = a.smaller(b);
        assertEquals(true, wynik);
    }

    @Test
    public void testLarger() {
        boolean wynik = b.larger(a);
        assertEquals(true, wynik);
    }

    @Test
    public void testUpperRight() {
        String wynik = a.upperRight(b).ToString();
        assertEquals("(2,5)", wynik);
    }
    @Test
    public void testLowerLeft() {
        String wynik = a.lowerLef(b).ToString();
        assertEquals("(1,3)", wynik);
    }
    @Test
    public void testAdd() {
        String wynik = a.add(b).ToString();
        assertEquals("(3,8)", wynik);
    }
}