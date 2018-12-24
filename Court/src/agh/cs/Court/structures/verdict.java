package agh.cs.Court.structures;

import agh.cs.Court.structures.regulation;
import agh.cs.Court.structures.rubrum;

import java.util.LinkedList;

public class verdict
{
    private String caseNum;
    private String id;
    private agh.cs.Court.structures.rubrum rubrum; //sygnatura, data, rodzaj sądu, skład sędziów
    private LinkedList<String>caseNumbers;
    private String type;
    private LinkedList<regulation>regulations;
    private String textContent;

    public verdict(rubrum rubrum, /*LinkedList<String>caseNumbers String type,*/ LinkedList<regulation>regulations, String textContent,String caseNum)
    {
        this.rubrum = rubrum;
        this.caseNumbers=new LinkedList<>();
        //this.caseNumbers=caseNumbers;
        //this.type=type;
        this.regulations=new LinkedList<>();
        this.regulations=regulations;
        this.textContent=textContent;
        this.caseNum=caseNum;
    }
    void updateID(String id)
    {
        this.rubrum.setID(id);
    }
    void updateDate(String date)
    {
        this.rubrum.setDate(date);
    }
    void updateCourtType(String type)
    {
        this.rubrum.setType(type);
    }
    void updateCaseNumbers(LinkedList<String>caseNumbers)
    {
        this.caseNumbers=caseNumbers;
    }
    void updateJudgmentType(String type)
    {
        this.type=type;
    }

    public String getTextContent()
    {
        return this.textContent;
    }
    public rubrum getRubrum()
    {
        return this.rubrum;
    }
    public LinkedList<regulation> getRegulations()
    {
        return this.regulations;
    }
}
