package lab3;

public class Car {

    private MapDirection2 orient;
    private Position position;

    public Car() {
        this.orient = MapDirection2.NORTH;
        this.position = new Position(2, 2);
    }


    public String toString() {
        String posString = position.toString();
        String orientString = orient.ToString();
        return posString + " " + orientString;
    }

    public void move(MoveDirection direction) {
        Position upperLimit = new Position(3, 3);
        Position lowerLimit = new Position(1, 1);
        switch (direction) {
            case LEFT:
                this.orient = this.orient.previous();
                break;
            case RIGHT:
                this.orient = this.orient.next();
                break;
            case FORWARD:
                switch (this.orient) {
                    case NORTH:
                        if (this.position.smaller(upperLimit)) this.position = this.position.add(new Position(0, 1));
                        break;
                    case SOUTH:
                        if (this.position.larger(lowerLimit)) this.position = this.position.add(new Position(0, -1));
                        break;
                    case EAST:
                        if (this.position.smaller(upperLimit)) this.position = this.position.add(new Position(1, 0));
                        break;
                    case WEST:
                        if (this.position.larger(lowerLimit)) this.position = this.position.add(new Position(-1, 0));
                        break;
                }
                break;
            case BACKWARD:
                switch (this.orient) {
                    case SOUTH:
                        if (this.position.smaller(upperLimit)) this.position = this.position.add(new Position(0, 1));
                        break;
                    case WEST:
                        if (this.position.smaller(upperLimit)) this.position = this.position.add(new Position(1, 0));
                        break;
                    case NORTH:
                        if (this.position.larger(lowerLimit)) this.position = this.position.add(new Position(0, -1));
                        break;
                    case EAST:
                        if (this.position.larger(lowerLimit)) this.position = this.position.add(new Position(-1, 0));
                        break;

                }
                break;
        }
    }

    public MapDirection2 getOrient() {
        return this.orient;
    }

    public Position getPosition() {
        return this.position;
    }


}
