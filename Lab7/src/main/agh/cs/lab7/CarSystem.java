package agh.cs.lab7;

import java.util.ArrayList;

public class CarSystem
{
    public static void main(String[] args)throws InterruptedException
    {
        try {
            String tab[] = {"f", "f", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(tab);
            ArrayList<HayStack>stacks=new ArrayList<>();
            stacks.add(new HayStack(new Position(3,4)));
            stacks.add(new HayStack(new Position(5,7)));
            IWorldMap map = new UnboundedMapv2(stacks);
            IPositionChangeObserver testObserver=(UnboundedMapv2) map;
            Car car1=new Car(map);
            Car car2=new Car(map,new Position(5,4));
            car1.addObserver(testObserver);
            car2.addObserver(testObserver);
            map.place(car1);
            map.place(car2);
            map.run(directions);
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
