package agh.cs.CourtProject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class metryka
{
    private String sentenceDate;
    private courtType court;
    private ArrayList<Judge> judges;

    public metryka(String sentenceSignature,String sentenceDate,courtType court,ArrayList<Judge>judges)
    {
        this.sentenceDate=sentenceDate;
        this.court=court;
        this.judges=judges;
    }
}
