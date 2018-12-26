package agh.cs.Court.orders;

import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.util.LinkedHashMap;
import java.util.Set;

public class orderVII
{
    private LinkedHashMap<String,Integer>courtTypeStats;
    public orderVII(LinkedHashMap<String, verdict>verdicts) //rozkład statystyczny liczby orzeczeń na typ sądu
    {
        this.courtTypeStats=new LinkedHashMap<>();
        this.courtTypeStats.put("COMMON",0);
        this.courtTypeStats.put("SUPREME",0);
        this.courtTypeStats.put("ADMINISTRATIVE",0);
        this.courtTypeStats.put("CONSTITUTIONAL_TRIBUNAL",0);
        this.courtTypeStats.put("NATIONAL_APPEAL_CHAMBER",0);
        this.courtTypeStats.put("Naczelny Sąd Administracyjny",0);
        this.courtTypeStats.put("Wojewódzki Sąd Administracyjny",0);
        Set<String> keys = verdicts.keySet();
        for (String k : keys)
        {
            rubrum r=verdicts.get(k).getRubrum();
            Integer judgmentNum=this.courtTypeStats.get(r.getCourtType());
            judgmentNum++;
            this.courtTypeStats.put(r.getCourtType(),judgmentNum);


        }
    }
    public String getCourtTypeStats()
    {
        StringBuilder s=new StringBuilder();
        s.append("Sądy Powszechne, liczba orzeczeń: " + this.courtTypeStats.get("COMMON")+System.lineSeparator());
        s.append("Sąd Najwyższy, liczba orzeczeń: " + this.courtTypeStats.get("SUPREME")+System.lineSeparator());
        int admTotal=this.courtTypeStats.get("Wojewódzki Sąd Administracyjny")+this.courtTypeStats.get("Naczelny Sąd Administracyjny");
        s.append("Sądy Administracyjne, liczba orzeczeń: " + admTotal+" ,w tym..."+System.lineSeparator());
        s.append("    Wojewódzkie Sądy Administracyjne, liczba orzeczeń: "+this.courtTypeStats.get("Wojewódzki Sąd Administracyjny")+System.lineSeparator());
        s.append("    Naczelne Sądy Administracyjne, liczba orzeczeń: "+this.courtTypeStats.get("Naczelny Sąd Administracyjny")+System.lineSeparator());
        s.append("Trybunał Konstytucyjny, liczba orzeczeń: " + this.courtTypeStats.get("CONSTITUTIONAL_TRIBUNAL")+System.lineSeparator());
        s.append("Krajowa Izba Odwoławcza, liczba orzeczeń: " + this.courtTypeStats.get("NATIONAL_APPEAL_CHAMBER")+System.lineSeparator());
        return s.toString();
    }
}

