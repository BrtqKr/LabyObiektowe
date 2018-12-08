package agh.cs.Court;

import java.util.LinkedList;
import java.util.List;

public class Verdict
{
    private Metryka metryka; //sygnatura, data, rodzaj sądu, skład sędziów
    private LinkedList<String>caseNumbers;
    private judgmentType type;
    private LinkedList<Regulation>regulations;

    public Verdict(Metryka metryka,LinkedList<String>caseNumbers,judgmentType type,LinkedList<Regulation>regulations)
    {
        this.metryka=metryka;
        this.caseNumbers=new LinkedList<>();
        this.caseNumbers=caseNumbers;                                   //zapisać wszystko w zmiennych w parserze i zainicjalizować verdict za 1 razem
        this.type=type;
        this.regulations=new LinkedList<>();
        this.regulations=regulations;
    }
    void updateID(String id)
    {
        this.metryka.setID(id);
    }
    void updateDate(String date)
    {
        this.metryka.setDate(date);
    }
    void updateCourtType(courtType type)
    {
        this.metryka.setType(type);
    }
    void updateCaseNumbers(LinkedList<String>caseNumbers)
    {
        this.caseNumbers=caseNumbers;
    }
    void updateJudgmentType(judgmentType type)
    {
        this.type=type;
    }
}
