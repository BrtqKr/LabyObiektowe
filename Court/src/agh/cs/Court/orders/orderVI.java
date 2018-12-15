package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

public class orderVI
{
    private int[] stats;
    public orderVI(LinkedHashMap<String, verdict> verdicts) throws IllegalArgumentException //wyświetla liczbę orzeczeń w zadanych miesiącach
    {
        this.stats=new int[13];

        int[] months=new int[13];

        Set<String> keys = verdicts.keySet();
        for (String k : keys) {
            rubrum tmp = verdicts.get(k).getRubrum();
            String date = tmp.getDate();
            String[] dateTab = date.split("-", 3);
            switch (dateTab[1]) {
                case "01":
                    months[1]++;
                    break;
                case "02":
                    months[2]++;
                    break;
                case "03":
                    months[3]++;
                    break;
                case "04":
                    months[4]++;
                    break;
                case "05":
                    months[5]++;
                    break;
                case "06":
                    months[6]++;
                    break;
                case "07":
                    months[7]++;
                    break;
                case "08":
                    months[8]++;
                    break;
                case "09":
                    months[9]++;
                    break;
                case "10":
                    months[10]++;
                    break;
                case "11":
                    months[11]++;
                    break;
                case "12":
                    months[12]++;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            this.stats = months;
        }
    }
    public int[] getStats()
    {
        return this.stats;
    }
}
