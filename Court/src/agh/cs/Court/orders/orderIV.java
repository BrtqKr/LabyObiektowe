package agh.cs.Court;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class orderIV
{
    public orderIV(ArrayList<String>Signatures, LinkedHashMap<String, verdict> verdicts)
    {
        for(int i=0;i<Signatures.size();i++)
        {
            verdict tmp=verdicts.get(Signatures.get(i));
            tmp.getRubrum().printMetryka();
        }
    }
}
