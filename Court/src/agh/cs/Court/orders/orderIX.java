package agh.cs.Court.orders;

import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class orderIX
{
    private int total;
    public orderIX(LinkedList<String> jury, LinkedHashMap<String, verdict> verdicts,int judgesNo)//wyświetla liczbę spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)
    {
        Set<String> keys = verdicts.keySet();
        int counter=0;
        for (String k : keys)
        {
           LinkedList<String> tmp=verdicts.get(k).getRubrum().getJury();
           if(tmp.containsAll(jury)&&tmp.size()==judgesNo)counter++;
        }
        this.total=counter;

    }
    public int getTotal()
    {
        return this.total;
    }
}