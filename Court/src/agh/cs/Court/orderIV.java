package agh.cs.Court;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class orderIV
{
    public orderIV(ArrayList<String>Signatures, LinkedHashMap<String,Verdict> verdicts)
    {
        for(int i=0;i<Signatures.size();i++)
        {
            Verdict tmp=verdicts.get(Signatures.get(i));
            tmp.getMetryka().printMetryka();
        }
    }
}
