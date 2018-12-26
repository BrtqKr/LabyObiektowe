package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.*;

public class orderV {
    private LinkedHashMap<String,Integer>verdictCounter;
    private String ranking;
    public orderV(LinkedHashMap<String, judge> judges) //10 sędziów z największą liczbą orzeczeń z uwzględnieniem powtórek wartości
    {
        Set<String> keys = judges.keySet();
        this.verdictCounter=new LinkedHashMap<>();
        for(String key:keys)
        {
                verdictCounter.put(key,judges.get(key).getCasesNumber());

        }
        List l = new ArrayList<>(this.verdictCounter.values());
        Collections.sort(l);
        LinkedList<String>result=new LinkedList<>();
        StringBuilder s=new StringBuilder();
        for(int i=l.size()-1;i>l.size()-11;i--)
        {
            for(String judgeKey:keys)
            {
                if(judges.get(judgeKey).getCasesNumber().equals(l.get(i)))
                {
                    if(!result.contains(judgeKey))
                    {
                        s.append(judgeKey+", liczba orzeczeń: "+l.get(i)+System.lineSeparator());
                        result.add(judgeKey);
                    }

                }
            }
        }
        this.ranking=s.toString();
    }

    public String getRanking() {
        return ranking;
    }
}