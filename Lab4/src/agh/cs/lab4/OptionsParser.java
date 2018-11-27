package agh.cs.lab4;

public class OptionsParser
{
    public static MoveDirection[] parse(String[] moves)
    {
        MoveDirection[] result=new MoveDirection[moves.length];
        int counter=0;
        for(int i=0;i<moves.length;i++)
        {
            if(moves[i].equals("f")||moves[i].equals("forward"))result[i]=MoveDirection.FORWARD;
            else if(moves[i].equals("b")||moves[i].equals("backward"))result[i]=MoveDirection.BACKWARD;
            else if(moves[i].equals("l")||moves[i].equals("left"))result[i]=MoveDirection.LEFT;
            else if(moves[i].equals("r")||moves[i].equals("right"))result[i]=MoveDirection.RIGHT;
            else
            {
                result[i]=MoveDirection.ERR;
                counter--;
            }
            counter++;
        }
        MoveDirection[] resfinal=new MoveDirection[counter];
        int a=0;
        for(int i=0;i<moves.length;i++)
        {
            if(result[i].equals(MoveDirection.FORWARD)||result[i].equals(MoveDirection.BACKWARD)||result[i].equals(MoveDirection.LEFT)||result[i].equals(MoveDirection.RIGHT))
            {
                resfinal[a]=result[i];
                a++;
            }

        }
        return resfinal;
    }
}
