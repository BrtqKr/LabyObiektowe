package agh.cs.lab7;

public class HayStack implements IObject
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
