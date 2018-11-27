package agh.cs.lab4;


public class CarSystem
{
    public static void main(String[] args)throws InterruptedException
    {
        String tab[] = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f",};
        MoveDirection[] directions = new OptionsParser().parse(tab);
        IWorldMap map = new RectangularMap(10,5);
        map.place(new Car(map));
        map.place(new Car(map, new Position(3,4)));
        map.run(directions);
    }
}
