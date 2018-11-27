package lab3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest
{
    Position a = new Position(1, 3);
    Position b = new Position(2, 5);

    @Test
    public void testToString() {
        String str = a.toString();
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
        String wynik = a.upperRight(b).toString();
        assertEquals("(2,5)", wynik);
    }
    @Test
    public void testLowerLeft() {
        String wynik = a.lowerLef(b).toString();
        assertEquals("(1,3)", wynik);
    }
    @Test
    public void testAdd() {
        String wynik = a.add(b).toString();
        assertEquals("(3,8)", wynik);
    }
}