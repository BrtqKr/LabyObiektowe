package lab3;

public class Position
{
    public final int x;
    public final int y;

    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public String toString()
    {
        return "(" + this.x+","+this.y+")";
    }
    public boolean smaller(Position object2)
    {
        if(this.y<=object2.y && this.x<=object2.x)return true;
        else return false;
    }
    public boolean larger(Position object2)
    {
        if(this.x>=object2.x && this.y>=object2.y)return true;
        else return false;
    }
    public Position upperRight(Position object2)
    {
        int x;
        int y;
        if (this.x > object2.x) x = this.x;
        else x = object2.x;
        if (this.y > object2.y) y = this.y;
        else y = object2.y;
        return new Position(x, y);

    }
    public Position lowerLef(Position object2)
    {
        int x;
        int y;
        if (this.x > object2.x) x = object2.x;
        else x = this.x;
        if (this.y > object2.y) y = object2.y;
        else y = this.y;
        return new Position(x, y);

    }
    public Position add(Position object2)
    {
        return new Position(this.x+object2.x,this.y+object2.y);
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
}

