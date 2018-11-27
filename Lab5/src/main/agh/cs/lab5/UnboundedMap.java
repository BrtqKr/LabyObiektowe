package agh.cs.lab5;

import java.util.ArrayList;
import java.util.List;

public class UnboundedMap extends AbstractWorldMap
{
    private List<HayStack>stackList;
    private Position bottomLeft;
    private Position upperRight;

    public UnboundedMap(List<HayStack> s)
    {
        this.stackList=s;
        this.cars=new ArrayList<>();
        Position maxpos=new Position(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Position minpos=new Position(Integer.MAX_VALUE,Integer.MAX_VALUE);
        for(HayStack stack:stackList)
        {
            maxpos=(stack.getPosition().upperRight(maxpos));
            minpos=(stack.getPosition().lowerLef(minpos));
        }
        this.upperRight =maxpos;
        this.bottomLeft =minpos;
    }

    public boolean canMoveTo(Position position)
    {
        if(!isOccupied(position))
        {
            Position a=position.lowerLef(this.bottomLeft);
            Position b=position.upperRight(this.upperRight);
            this.upperRight =b;
            this.bottomLeft =a;
            return true;
        }
        return false;
    }

    public boolean isOccupied(Position position)
    {
        /*for (Car carIter:cars)
        {
            if (position.equals(carIter.getPosition()))
                return true;
        }*/

        for(HayStack stackIter:stackList)
        {
            if(position.equals(stackIter.getPosition()))
            return true;
        }
        return super.isOccupied(position);
    }

    public Object objectAt(Position position)
    {
        /*for (Car carIter:cars)
        {
            if (carIter.getPosition().equals(position))
                return carIter;
        }*/

        for(HayStack stackIter:stackList)
        {
            if(stackIter.getPosition().equals(position))
                return stackIter;
        }
        return super.objectAt(position);
    }

    public String toString()
    {
        return new MapVisualizer(this).draw(new Position(this.bottomLeft.getX(),this.bottomLeft.getY()),new Position(this.upperRight.getX(),this.upperRight.getY()));
    }
    public Position getBottomLeft()
    {
        return this.bottomLeft;
    }
    public Position getUpperRight()
    {
        return this.upperRight;
    }
}

