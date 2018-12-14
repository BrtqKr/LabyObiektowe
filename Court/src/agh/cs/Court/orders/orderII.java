package agh.cs.Court;

import java.util.LinkedHashMap;

public class orderII { //wyświetla rubrum

    private LinkedHashMap<String, verdict> verdicts;
    public orderII(LinkedHashMap<String, verdict> verdicts)
    {
        this.verdicts=verdicts;
    }
    public void execute(String caseNum)
    {
        this.verdicts.get(caseNum).getRubrum().printMetryka();

    }

}
