package agh.cs.lab5;

import java.util.ArrayList;

public class CarSystem
{
    public static void main(String[] args)throws InterruptedException
    {
        String tab[] = {"f", "b", "r", "r", "f", "f", "r", "l", "f", "f", "f", "f", "f", "f", "f", "f",  "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(tab);
        ArrayList<HayStack>stacks=new ArrayList<>();
        stacks.add(new HayStack(new Position(-4,-4)));
        stacks.add(new HayStack(new Position(7,7)));
        stacks.add(new HayStack(new Position(3,6)));
        stacks.add(new HayStack(new Position(2,0)));
        IWorldMap map = new UnboundedMap(stacks);
        map.place(new Car(map));
        map.place(new Car(map, new Position(3,4)));
        map.run(directions);
    }
}
