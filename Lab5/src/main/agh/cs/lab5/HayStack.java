package agh.cs.lab5;

public class HayStack
{
    private final Position position;
    public HayStack(Position stackPosition)
    {
        this.position=stackPosition;
    }
    public Position getPosition()
    {
        return this.position;
    }
    public String toString()
    {
        return "S";
    }
}
