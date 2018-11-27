package agh.cs.Court ;

import java.util.ArrayList;

public class Court
{
    private String courtID;
    private ArrayList<String>cases;
    private courtType type;

    public Court(String courtID,ArrayList<String>cases,courtType type)
    {
        this.courtID=courtID;
        this.cases=cases;
        this.type=type;
    }
}
