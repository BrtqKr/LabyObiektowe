package lab3;


public class CarSystem
{
    public static void main(String[] args)
    {
        String[] tab={"f" };
        Car papamobile=new Car();
        for(MoveDirection iter : new OptionsParser().parse(tab))
        {
            papamobile.move(iter);
        }
        papamobile.move(MoveDirection.FORWARD);

        System.out.println(papamobile.getOrient().toString());
        System.out.print(papamobile.getPosition().toString());

    }
}
