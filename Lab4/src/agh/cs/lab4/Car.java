package agh.cs.lab4;

public class Car{
    private MapDirection2 orientacja;
    private Position pozycja;
    private IWorldMap map;

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
            Position vect = getVector(direction);
            Position newP = this.pozycja.add(vect);
            if(this.map.canMoveTo(newP))
                this.pozycja=newP;
        }
    }

    public Position getPosition(){
        return this.pozycja;
    }

}
