package agh.cs.Court.orders;

import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;

public class orderII { //wyświetla rubrum

    private LinkedHashMap<String, verdict> verdicts;
    public orderII(LinkedHashMap<String, verdict> verdicts)
    {
        this.verdicts=verdicts;
    }
    public rubrum executeOII(String caseNum)
    {
       return this.verdicts.get(caseNum).getRubrum();

    }

}
