package agh.cs.lab6;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
