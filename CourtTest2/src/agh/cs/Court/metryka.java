package agh.cs.Court;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class metryka
{
    private String sentenceDate;
    private courtType court;
    private ArrayList<Judge> judges;

    public void addDate(String date)
    {
        this.sentenceDate=date;
    }
    public void addCourtType(courtType court)
    {
        this.court=court;
    }
    public void updateJudges(Judge judge)
    {
        this.judges.add(judge);
    }
}
