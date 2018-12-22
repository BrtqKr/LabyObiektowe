package agh.cs.Court.orders;

import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class orderIX
{
    private int[] total;
    public orderIX(LinkedHashMap<String, verdict> verdicts)//wyświetla liczbę spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)
    {

       Set<String>keys=verdicts.keySet();
       int[] counter=new int[20];
       for(String k:keys)
       {
           int r=verdicts.get(k).getRubrum().getJudgesNo();
           counter[r]++;
       }
       this.total=counter;
    }
    public int[] getTotal()
    {
        return this.total;
    }
}
