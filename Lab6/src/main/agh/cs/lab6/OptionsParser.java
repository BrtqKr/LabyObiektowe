package agh.cs.lab6;

public class OptionsParser
{
    public static MoveDirection[] parse(String[] moves)
    {
        MoveDirection[] result=new MoveDirection[moves.length];
        int i=0;
        for(String move: moves)
        {
            if(move.equals("f")||move .equals("forward"))result[i]=MoveDirection.FORWARD;
            else if(move.equals("b")||move.equals("backward"))result[i]=MoveDirection.BACKWARD;
            else if(move.equals("l")||move.equals("left"))result[i]=MoveDirection.LEFT;
            else if(move.equals("r")||move.equals("right"))result[i]=MoveDirection.RIGHT;
            else throw new IllegalArgumentException(move+" is not legal move specitication");
            i++;
        }
        MoveDirection[] betterResult= new MoveDirection[i];
        for(int j=0;j<i;j++)betterResult[j]=result[j];
        return betterResult;
    }
}
