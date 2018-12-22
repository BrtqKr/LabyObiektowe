package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.*;

public class orderV {
    private LinkedHashMap<String,Integer>verdictCounter;
    public orderV(LinkedHashMap<String, judge> judges) //10 sędziów z największą liczbą orzeczeń
    {
        Set<String> keys = judges.keySet();
        this.verdictCounter=new LinkedHashMap<>();
        for(String key:keys)
        {
                verdictCounter.put(key,judges.get(key).getCasesNumber());

        }
        List l = new ArrayList<>(this.verdictCounter.values());
        Collections.sort(l);
        for(int i=l.size()-1;i>l.size()-11;i--)
        {
            for(String judgeKey:keys)
            {
                if(judges.get(judgeKey).getCasesNumber().equals(l.get(i)))
                {
                    System.out.println(judgeKey+", liczba orzeczeń: "+l.get(i));
                }
            }
        }

    }
}