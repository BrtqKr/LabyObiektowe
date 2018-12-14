package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.LinkedHashMap;
import java.util.Set;

public class orderV {
    public orderV(LinkedHashMap<String, judge> judges) //10 sędziów z największą liczbą orzeczeń
    {


        Set<String> keys = judges.keySet();
        judge[] ranking=new judge[keys.size()];         //zastanowić się nad efektywnym sortowaniem
        for (String k : keys)
        {
            judge tmp=judges.get(k);

        }

    }
}