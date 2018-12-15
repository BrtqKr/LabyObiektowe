package agh.cs.Court.orders;

import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;
import java.util.Set;

public class orderVII
{
    private LinkedHashMap<String,Integer>courtTypeStats;
    public orderVII(LinkedHashMap<String, verdict>verdicts)
    {
        this.courtTypeStats=new LinkedHashMap<>();
        this.courtTypeStats.put("COMMON",0);
        this.courtTypeStats.put("SUPREME",0);
        this.courtTypeStats.put("ADMINISTRATIVE",0);
        this.courtTypeStats.put("CONSTITUTIONAL_TRIBUNAL",0);
        this.courtTypeStats.put("NATIONAL_APPEAL_CHAMBER",0);
        Set<String> keys = verdicts.keySet();
        for (String k : keys)
        {
            rubrum r=verdicts.get(k).getRubrum();
            Integer judgmentNum=this.courtTypeStats.get(r.getCourtType());
            judgmentNum++;
            this.courtTypeStats.put(r.getCourtType(),judgmentNum);
        }
    }
    public LinkedHashMap<String,Integer> getCourtTypeStats()
    {
        return this.courtTypeStats;
    }
}

