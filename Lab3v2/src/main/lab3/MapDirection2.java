package lab3;

public enum MapDirection2
{
    NORTH,SOUTH,WEST,EAST,ERR;

    public String ToString()
    {
        switch (this)
        {
            case NORTH:return "NORTH";
            case EAST:return "EAST";
            case WEST:return "WEST";
            case SOUTH:return "SOUTH";
            default:return "";
        }

    }
    public MapDirection2 next()
    {
        switch (this)
        {
            case NORTH:return EAST;
            case SOUTH:return WEST;
            case WEST:return NORTH;
            case EAST:return SOUTH;
            default:return ERR;
        }

    }
    public MapDirection2 previous()
    {
        switch (this)
        {
            case NORTH:return WEST;
            case SOUTH:return EAST;
            case EAST:return NORTH;
            case WEST:return SOUTH;
            default:return ERR;
        }

    }


}