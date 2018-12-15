package agh.cs.Court;

import agh.cs.Court.orders.*;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.verdict;
import org.json.simple.parser.ParseException;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class main
{
    public static void main(String[] args)throws ParseException,IllegalArgumentException
    {
        try
        {
            /*Path dir=Paths.get("C:/Users/barte/OneDrive/Pulpit/json");
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

            orderVII o7=new orderVII(verdicts);
            LinkedHashMap<String,Integer> statsPerType=o7.getCourtTypeStats();

            System.out.println("COMMON: "+statsPerType.get("COMMON"));
            System.out.println("SUPREME: "+statsPerType.get("SUPREME"));
            System.out.println("ADMINISTRATIVE: "+statsPerType.get("ADMINISTRATIVE"));
            System.out.println("CONSTITUTIONAL_TRIBUNAL: " +statsPerType.get("CONSTITUTIONAL_TRIBUNAL"));
            System.out.println("NATIONAL_APPEAL_CHAMBER: "+statsPerType.get("NATIONAL_APPEAL_CHAMBER"));
            */
            Scanner s=new Scanner(System.in);
            System.out.print("Podaj ścieżkę...");
            String path=s.nextLine();
            Path dir=Paths.get(path);
            parser parser=new parser(dir);
            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            System.out.println();
            System.out.print(">");
            String command=s.nextLine();
            while(true)
            {
                switch (command)
                {
                    case "Quit": break;
                    case "help":
                        System.out.println("Available commands: ");
                        System.out.println("rubrum - wyświetlenie metryki jednego lub wielu orzeczeń, na podstawie sygnatury");
                        System.out.println("content - wyświetlenie uzasadnienia");
                        System.out.println("judge - wyświetlenie liczby orzeczeń dla wybranego sędziego");
                        System.out.println("judges - wyświetlenie 10 sędziów o największej liczbie wydanych orzeczeń");
                        System.out.println("months - wyświetlenie liczby orzeczeń w poszczególnych miesiącach");
                        System.out.println("courts - wyświetlenie liczby orzeczeń ze względu na typ sądu");
                        System.out.println("regulations - wyświetlenie 10 najczęściej przywoływanych ustaw");
                        System.out.println("jury - wyświetlenie liczby spraw przypadających na określony skład sędziowski");
                        break;
                    case "rubrum":
                        System.out.println("Podaj sygnaturę orzeczenia...");
                        System.out.print(">");
                        command=s.nextLine();

                }
            }
        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
