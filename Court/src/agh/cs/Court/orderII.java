package agh.cs.Court;

import java.util.LinkedHashMap;

public class orderII {

    private LinkedHashMap<String,Verdict> verdicts;
    public orderII(LinkedHashMap<String,Verdict> verdicts)
    {
        this.verdicts=verdicts;
    }
    public void execute(String caseNum)
    {
        this.verdicts.get(caseNum).getMetryka().printMetryka();

    }

}
