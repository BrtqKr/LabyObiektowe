package agh.cs.Court.structures;

import agh.cs.Court.structures.judge;

import java.util.LinkedList;

public class rubrum
{
    private String caseNumber;
    private String judgmentDate;
    private String courtType;
    private LinkedList<judge> judges;

    public rubrum(String id, String judgmentDate, String courtType, LinkedList<judge> judges)
    {
        this.caseNumber=id;
        this.judgmentDate=judgmentDate;
        this.courtType=courtType;
        this.judges=judges;
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

    public void printMetryka()
    {
        System.out.println("Sygnatura orzeczenia: "+this.caseNumber);
        System.out.println("Data wydania orzeczenia: "+this.judgmentDate);
        System.out.println("Typ sądu: "+ this.courtType);                     //zakładam że zamieniam court type przed przekazaniem na stringa

        System.out.println("Skład orzekający: ");
        for(judge judgeIter:judges)
        {
            System.out.print("Sędzia: "+judgeIter.getName()+", Role w postępowaniu: ");
            if(judgeIter.getRoles(this.caseNumber).isEmpty())System.out.println("brak ról");
            else System.out.println(judgeIter.getRoles(this.caseNumber));
        }
    }

    public String getDate()
    {
        return this.judgmentDate;
    }
    public String getCourtType()
    {
        return this.courtType;
    }
}
