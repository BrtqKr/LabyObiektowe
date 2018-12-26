package agh.cs.Court.orders;

import agh.cs.Court.structures.regulation;
import agh.cs.Court.structures.verdict;

import java.util.*;

public class orderVIII     // 10 najczęściej przywoływanych ustaw
{
    private String regs;
    private LinkedHashMap<String, Integer> regulationsStats;   //tytuł, liczba
    public orderVIII(LinkedHashMap<String, verdict> verdicts) {
        this.regulationsStats = new LinkedHashMap<>();
        Set<String> keys = verdicts.keySet();
        for (String k : keys) {
            LinkedList<regulation> regulations = verdicts.get(k).getRegulations();
            for (regulation tmp : regulations) {
                if (!this.regulationsStats.containsKey(tmp.getTitle())) {
                    this.regulationsStats.put(tmp.getTitle(), 0);
                } else {

                    Integer currCounter = this.regulationsStats.get(tmp.getTitle());
                    currCounter++;
                    this.regulationsStats.put(tmp.getTitle(), currCounter);
                }
            }
        }
        List l = new ArrayList<>(this.regulationsStats.values());
        Collections.sort(l);
        LinkedList<String> result=new LinkedList<>();
        StringBuilder s=new StringBuilder();
        for (int i = l.size() - 1; i >l.size() - 11; i--)//iteruje po największych wartościach
        {
            for (String title : regulationsStats.keySet()) //iteruje po tytułach i szuka tytułu największej
            {

                if (regulationsStats.get(title).equals(l.get(i))&&!result.contains(title)) {

                    s.append((l.size()-i)+")"+title+"...................."+l.get(i)+System.lineSeparator());
                    result.add(title);
                }


            }
        }
        this.regs=s.toString();
    }

    public String getRegs() {
        return regs;
    }
}
