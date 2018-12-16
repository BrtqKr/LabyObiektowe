package agh.cs.Court.orders;

import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;
import java.util.Set;

public class orderIX
{
    public orderIX(String[] jury, LinkedHashMap<String, verdict> verdicts)//wyświetla liczbę spraw przypadających na określony skład sędziowski (określoną liczbę sędziów)
    {
        Set<String> keys = verdicts.keySet();
        for (String k : keys)
        {
            verdict tmp=verdicts.get(k).getRubrum()
        }

    }
}
