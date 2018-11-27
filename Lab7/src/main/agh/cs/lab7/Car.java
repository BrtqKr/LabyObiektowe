package agh.cs.lab7;

import java.util.ArrayList;
import java.util.List;

public class Car implements IObject
{
    private MapDirection2 orientacja;
    private Position pozycja;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers=new ArrayList<>();
    public Car(IWorldMap map, Position initialPosition) {
        this.pozycja = initialPosition;
        this.orientacja = MapDirection2.NORTH;
        this.map=map;
    }

    public Car(IWorldMap map) {
        this.pozycja = new Position(2,2);
        this.orientacja = MapDirection2.NORTH;
        this.map=map;
    }

    public String toString() {
        return orientacja.ToString();
    }


    public Position getUnitVector(){
        switch(this.orientacja) {
            case NORTH: return new Position(0, 1);
            case EAST: return new Position(1, 0);
            case SOUTH: return new Position(0, -1);
            case WEST: return new Position(-1, 0);
        }
        return null;
    }

    public Position getVector(MoveDirection direction){
        Position zero = new Position(0,0);
        if (direction.equals(MoveDirection.FORWARD)) {
            return zero.add(getUnitVector());
        } else if (direction.equals(MoveDirection.BACKWARD)) {
            return zero.subtract(getUnitVector());
        } else {
            return zero;
        }
    }

    public void move (MoveDirection direction) {

        if (direction.equals(MoveDirection.RIGHT))
            this.orientacja = this.orientacja.next();
        else if (direction.equals(MoveDirection.LEFT))
            this.orientacja = this.orientacja.previous();
        else {
            Position before=this.pozycja;
            Position vect = getVector(direction);
            Position newP = this.pozycja.add(vect);
            if(this.map.canMoveTo(newP))
            {
                this.pozycja=newP;
                carPositionChanged(before,this.getPosition());
            }

        }
    }
    public void addObserver(IPositionChangeObserver observer)
    {
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer)
    {
        observers.remove(observer);
    }
    void carPositionChanged(Position oldPosition, Position newPosition)
    {
        for(IPositionChangeObserver observer:observers)
        {
            observer.positionChanged(oldPosition,newPosition);
        }
    }
    public Position getPosition(){
        return this.pozycja;
    }
    public MapDirection2 getOrientacja(){return this.orientacja;}

    public IWorldMap getMap(){
        return this.map;
    }
}
