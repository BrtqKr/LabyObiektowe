package lab3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OptionsParserTest
{
    Car testcar=new Car();
    @Test
    public void parsertest()
    {
        String[] mov={"l","forward","f","f","l","b","backward","","GJIOERWGJ"," ","backward"};
        MoveDirection[] movparsed=OptionsParser.parse(mov);
        for(MoveDirection iterator:movparsed)
        {
            testcar.move(iterator);
        }
        assertEquals(new Position(0,4),testcar.getPosition());
        assertEquals(MapDirection2.SOUTH,testcar.getOrient());
    }
}
