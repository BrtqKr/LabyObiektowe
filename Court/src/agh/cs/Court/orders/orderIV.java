package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.LinkedHashMap;

public class orderIV
{
    public orderIV(LinkedHashMap<String, judge > judges, String name)
    {
            judge tmp=judges.get(name);
            System.out.println(name+", liczba orzeczeń: "+tmp.getCasesNumber());
    }
}
