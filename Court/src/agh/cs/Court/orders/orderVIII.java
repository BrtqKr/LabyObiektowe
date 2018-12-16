package agh.cs.Court.orders;

import agh.cs.Court.structures.regulation;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.util.*;

public class orderVIII     // 10 najczęściej przywoływanych ustaw
{
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
        for (int i = l.size() - 1; i > l.size() - 11; i--)//iteruje po największych wartościach
        {
            for (String title : regulationsStats.keySet()) //iteruje po tytułach i szuka tytułu największej
            {
                if (regulationsStats.get(title) == l.get(i)) {
                    /*for (String k : keys)//iteruje po orzeczeniach
                    {
                        LinkedList<regulation> regulations = verdicts.get(k).getRegulations();
                        for (regulation tmp : regulations)//szuka
                        {
                            if (tmp.getTitle().equals(title)) System.out.println(tmp.getText()+" "+l.get(i));
                        }
                    }
                    */
                    System.out.println(title+" "+l.get(i));//zapytać czy to tak ma być, czy ma wyświetlać tekst?
                }

            }
        }
    }
}
