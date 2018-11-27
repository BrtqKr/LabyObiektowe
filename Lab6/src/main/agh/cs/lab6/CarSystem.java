package agh.cs.lab6;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CarSystem
{
    public static void main(String[] args)throws InterruptedException
    {
        try {
            String tab[] = {"f", "f", "r", "f", "f", "f", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(tab);
            ArrayList<HayStack>stacks=new ArrayList<>();
            stacks.add(new HayStack(new Position(3,4)));
            stacks.add(new HayStack(new Position(5,7)));
            IWorldMap map = new UnboundedMapv2(stacks);
            map.place(new Car(map));
            map.place(new Car(map, new Position(5, 5)));
            map.place(new Car(map, new Position(5, 5)));
            map.run(directions);
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
