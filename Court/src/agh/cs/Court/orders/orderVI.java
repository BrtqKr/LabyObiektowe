package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.LinkedHashMap;

public class orderVI
{
    public orderVI(LinkedHashMap<String, judge> judges,String judgeName)  //wyświetla liczbę orzeczeń wybranego sędziego
    {
        judge j=judges.get(judgeName);
        System.out.println("Sędzia "+judgeName+", liczba orzeczeń: "+j.getCasesNumber());
    }
}
