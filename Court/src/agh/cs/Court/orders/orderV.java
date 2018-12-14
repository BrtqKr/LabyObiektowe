package agh.cs.Court;

import java.util.LinkedHashMap;

public class orderV
{
    public orderV(LinkedHashMap<String, judge> judges, String name)
    {
        judge tmp=judges.get(name);
        System.out.println(tmp.getCasesNumber());
    }
}
