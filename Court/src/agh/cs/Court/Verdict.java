package agh.cs.Court;

import java.util.LinkedList;

public class Verdict
{
    private String caseNum;
    private String id;
    private Metryka metryka; //sygnatura, data, rodzaj sądu, skład sędziów
    private LinkedList<String>caseNumbers;
    private String type;
    private LinkedList<Regulation>regulations;
    private String textContent;

    public Verdict(Metryka metryka, LinkedList<String>caseNumbers, String type, LinkedList<Regulation>regulations, String textContent,String id,String caseNum)
    {
        this.metryka=metryka;
        this.caseNumbers=new LinkedList<>();
        this.caseNumbers=caseNumbers;                                   //zapisać wszystko w zmiennych w parserze i zainicjalizować verdict za 1 razem
        this.type=type;
        this.regulations=new LinkedList<>();
        this.regulations=regulations;
        this.textContent=textContent;
        this.id=id;
        this.caseNum=caseNum;
    }
    void updateID(String id)
    {
        this.metryka.setID(id);
    }
    void updateDate(String date)
    {
        this.metryka.setDate(date);
    }
    void updateCourtType(String type)
    {
        this.metryka.setType(type);
    }
    void updateCaseNumbers(LinkedList<String>caseNumbers)
    {
        this.caseNumbers=caseNumbers;
    }
    void updateJudgmentType(String type)
    {
        this.type=type;
    }

    public void printTextContent()
    {
        System.out.println(this.textContent);
    }
    public Metryka getMetryka()
    {
        return this.metryka;
    }
}
