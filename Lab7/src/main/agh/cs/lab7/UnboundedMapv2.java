package agh.cs.lab7;

import java.util.*;

public class UnboundedMapv2 extends AbstractWorldMap
{
    private Position Bottom_Left;
    private Position Upper_Right;
    private List<HayStack>stacks;

    public UnboundedMapv2(ArrayList<HayStack>h)
    {
        this.stacks=h;
        Position maxpos=new Position(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Position minpos=new Position(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for(HayStack stack:stacks)
        {
                maxpos = stack.getPosition().upperRight(maxpos);
                minpos = stack.getPosition().lowerLef(minpos);
                this.hashObjects.put(stack.getPosition(),stack);
        }
        this.Upper_Right=maxpos;
        this.Bottom_Left=minpos;
    }

    public boolean canMoveTo(Position position)
    {

            Position a=position.lowerLef(this.Bottom_Left);
            Position b=position.upperRight(this.Upper_Right);
            this.Upper_Right=b;
            this.Bottom_Left=a;
            return true;

    }
    public void run(MoveDirection[] directions)
    {
        super.run(directions);
    }

    public Object objectAt(Position position)
    {
        for(HayStack stackIter:stacks)
        {
            if(stackIter.getPosition().equals(position))
                return stackIter;
        }
        return super.objectAt(position);
    }

    public String toString()
    {
        return new MapVisualizer(this).draw(new Position(this.Bottom_Left.getX(),this.Bottom_Left.getY()),new Position(this.Upper_Right.getX(),this.Upper_Right.getY()));
    }
    public Position getBottomLeft()
    {
        return this.Bottom_Left;
    }
    public Position getUpperRight()
    {
        return this.Upper_Right;
    }

    @Override
    public void positionChanged(Position oldPosition, Position newPosition) {

    }
}

