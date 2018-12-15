package agh.cs.Court;

import agh.cs.Court.orders.orderII;
import agh.cs.Court.orders.orderIII;
import agh.cs.Court.orders.orderIV;
import agh.cs.Court.orders.orderVI;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.verdict;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class main
{
    public static void main(String[] args)throws ParseException,IllegalArgumentException
    {
        try
        {
            Path dir=Paths.get("C:/Users/barte/OneDrive/Pulpit/json");
            parser parser=new parser(dir);
            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            LinkedHashMap<String, judge>judges =parser.getJudges();

            orderII o2=new orderII(verdicts);
            o2.execute("II AKa 105/11");

            orderIII o3=new orderIII(verdicts,"II AKa 105/11");

            orderIV o4=new orderIV(judges,"Andrzej Rzepliński");

            orderVI o6=new orderVI(verdicts);
            int tab[]= o6.getStats();
            for(int i=1;i<tab.length;i++)
            {
                System.out.println("Miesiąc "+i+" liczba wyroków: "+tab[i]);
            }

        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
