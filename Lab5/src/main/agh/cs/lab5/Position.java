package agh.cs.lab5;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean smaller(Position a) {
        if (this.x <= a.x && this.y <= a.y) return true;
        else return false;
    }

    public boolean larger(Position a) {
        if (this.x > a.x && this.y > a.y) return true;
        else return false;
    }

    public Position upperRight(Position a) {
        int x;
        int y;
        if (this.x > a.x) x = this.x;
        else x = a.x;
        if (this.y > a.y) y = this.y;
        else y = a.y;
        return new Position(x, y);
    }

    public Position lowerLef(Position a) {
        int x;
        int y;
        if (this.x > a.x) x = a.x;
        else x = this.x;
        if (this.y > a.y) y = a.y;
        else y = this.y;
        return new Position(x, y);
    }

    public Position add(Position a) {
        return new Position(this.x + a.x, this.y + a.y);
    }
    public Position subtract(Position a) {
        return new Position(this.x - a.x, this.y - a.y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }


    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Position))
            return false;
        Position that = (Position) other;
        if (this.x == that.x && this.y == that.y) return true;
        else return false;
    }

    public Position addd(int x, int y){
        return new Position(this.x+x, this.y+y);
    }

}
