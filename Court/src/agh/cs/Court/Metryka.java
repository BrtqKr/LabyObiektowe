package agh.cs.Court;

import java.util.LinkedList;

public class Metryka
{
    private String id;
    private String judgmentDate;
    private String courtType;
    private LinkedList<Judge> judges;       //tymczasowo string potem spróbować przełożyć na klasę Judge

    public Metryka(String id,String judgmentDate,String courtType,LinkedList<Judge> judges)
    {
        this.id=id;
        this.judgmentDate=judgmentDate;
        this.courtType=courtType;
        this.judges=judges;
    }
    public void printMetryka()
    {
        System.out.println("Sygnatura orzeczenia: "+this.id);
        System.out.println("Data wydania orzeczenia: "+this.judgmentDate);
        System.out.println("Typ sądu: "+this.courtType.toString());                     //zapytać o przechowywanie typu

        System.out.println("Skład orzekający: ");
        for(Judge judgeIter:judges)
        {
            System.out.print("Sędzia: "+judgeIter.getName()+" Role w postępowaniu: ");
            System.out.print(judgeIter.getRoles(this.id));
        }
    }
}
