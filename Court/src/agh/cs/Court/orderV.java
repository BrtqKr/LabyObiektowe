package agh.cs.Court;

import java.util.LinkedHashMap;

public class orderV
{
    public orderV(LinkedHashMap<String, Judge> judges,String name)
    {
        Judge tmp=judges.get(name);
        System.out.println(tmp.getCasesNumber());
    }
}
