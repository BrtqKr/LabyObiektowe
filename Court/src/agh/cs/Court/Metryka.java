package agh.cs.Court;

import java.util.LinkedList;

public class Metryka
{
    private String id;
    private String judgmentDate;
    private courtType courtType;
    private LinkedList<Judge> judges;

    public Metryka(String id,String judgmentDate,courtType courtType,LinkedList<Judge> judges)
    {
        this.id=id;
        this.judgmentDate=judgmentDate;
        this.courtType=courtType;
        this.judges=judges;
    }

    void setID(String id)
    {
        this.id=id;
    }
    void setDate(String date)
    {
        this.judgmentDate=date;
    }
    void setType(courtType type)
    {
        this.courtType=type;
    }

    public void printMetryka()
    {
        System.out.println("Sygnatura orzeczenia: "+this.id);
        System.out.println("Data wydania orzeczenia: "+this.judgmentDate);
        System.out.println("Typ sądu: "+this.courtType);                     //zakładam że zamieniam court type przed przekazaniem na stringa

        System.out.println("Skład orzekający: ");
        for(Judge judgeIter:judges)
        {
            System.out.print("Sędzia: "+judgeIter.getName()+" Role w postępowaniu: ");
            System.out.print(judgeIter.getRoles(this.id));
        }
    }
}
