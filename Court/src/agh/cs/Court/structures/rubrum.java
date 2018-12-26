package agh.cs.Court.structures;

import agh.cs.Court.structures.judge;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class rubrum
{
    private String caseNumber;
    private String judgmentDate;
    private String courtType;
    private LinkedList<judge> judges;
    private LinkedList<String>jury;

    public rubrum(String id, String judgmentDate, String courtType, LinkedList<judge> judges,LinkedList<String>jury)
    {
        this.caseNumber=id;
        this.judgmentDate=judgmentDate;
        this.courtType=courtType;
        this.judges=judges;
        this.jury=jury;
    }

    public void setID(String id)
    {
        this.caseNumber=id;
    }
    public void setDate(String date)
    {
        this.judgmentDate=date;
    }
    public void setType(String type)
    {
        this.courtType=type;
    }

    public String getRubrum()
    {
        StringBuilder s=new StringBuilder();

        s.append("Sygnatura orzeczenia: "+this.caseNumber+System.lineSeparator());
        s.append("Data wydania orzeczenia: "+this.judgmentDate+System.lineSeparator());
        s.append("Typ sądu: "+ this.courtType+System.lineSeparator());
        s.append("Skład orzekający: "+System.lineSeparator());
        for(judge judgeIter:judges)
        {
            s.append("Sędzia: "+judgeIter.getName()+", Role w postępowaniu: ");
            if(judgeIter.getRoles(this.caseNumber).isEmpty())s.append("brak ról"+System.lineSeparator());
            else s.append(judgeIter.getRoles(this.caseNumber)+System.lineSeparator());
        }
        return s.toString();
    }

    public String getDate()
    {
        return this.judgmentDate;
    }
    public String getCourtType()
    {
        return this.courtType;
    }
    public LinkedList<String> getJury(){return this.jury;}
    public int getJudgesNo(){return this.judges.size();}
}
