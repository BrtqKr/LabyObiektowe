package agh.cs.Court;

import java.util.LinkedHashMap;

public class orderIII
{
    public orderIII(LinkedHashMap<String,Verdict> verdicts, String signature)
    {
        Verdict tmp=verdicts.get(signature);
        tmp.printTextContent();
    }
}
