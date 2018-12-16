package agh.cs.Court;

import agh.cs.Court.orders.*;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class main
{
    public static void main(String[] args)throws ParseException,IllegalArgumentException
    {

        try
        {
            /*Path dir=Paths.get("C:/Users/barte/OneDrive/Pulpit/jsonVerdicts");
            //C:/Users/barte/OneDrive/Pulpit/htmlVerdicts
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
            Scanner sint=new Scanner(System.in);
            System.out.print("Podaj ścieżkę do folderu JSON...");
            String path=s.nextLine();
            Path dir=Paths.get(path);
            System.out.print("Podaj ścieżkę do folderu HTML...");
            String htmlPath=s.nextLine();
            Path htmlDir=Paths.get(htmlPath);
            parser parser=new parser(dir,htmlDir);

            List<Path> x=parser.getHtmlDirectoryFiles();
            for(Path p:x)
            {
                System.out.println(p.getFileName());
            }

            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            LinkedHashMap<String, judge> judges=parser.getJudges();
            System.out.println();
            while(true)
            {
                System.out.print(">");
                String command=s.nextLine();
                switch (command)
                {
                    case "Quit": System.exit(0);
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
                        orderII o2=new orderII(verdicts);
                        rubrum r= o2.executeOII(command);
                        r.printRubrum();
                        break;
                    case "content":
                        System.out.println("Podaj sygnaturę orzeczenia...");
                        System.out.print(">");
                        command=s.nextLine();
                        orderIII o3=new orderIII(verdicts,command);
                        break;
                    case "judge":
                        System.out.println("Podaj imię i nazwisko sędziego...");
                        System.out.print(">");
                        command=s.nextLine();
                        orderIV o4=new orderIV(judges,command);
                        judge tmp=o4.getJudgeResult();
                        System.out.println(command+", liczba orzeczeń: "+tmp.getCasesNumber());
                        break;
                    case "judges":
                        orderV o5=new orderV(judges);
                        break;
                    case "months":
                        orderVI o6=new orderVI(verdicts);
                        int[] stats=o6.getStats();
                        for(int i=1;i<stats.length;i++)
                        {
                            switch (i)
                            {
                                case 1:System.out.print("Styczeń, ");
                                break;
                                case 2:System.out.print("Luty, ");
                                break;
                                case 3:System.out.print("Marzec, ");
                                break;
                                case 4:System.out.print("Kwiecień, ");
                                break;
                                case 5:System.out.print("Maj, ");
                                break;
                                case 6:System.out.print("Czerwiec, ");
                                break;
                                case 7:System.out.print("Lipiec, ");
                                break;
                                case 8:System.out.print("Sierpień, ");
                                break;
                                case 9:System.out.print("Wrzesień, ");
                                break;
                                case 10:System.out.print("Październik, ");
                                break;
                                case 11:System.out.print("Listopad, ");
                                break;
                                case 12:System.out.print("Grudzień, ");
                                break;
                            }
                            System.out.println("liczba orzeczeń: "+stats[i]);
                        }
                        break;
                    case "courts":
                        orderVII o7=new orderVII(verdicts);
                        LinkedHashMap<String,Integer>courtTypeStats=o7.getCourtTypeStats();
                        System.out.println("Sądy powszechne, liczba orzeczeń: "+courtTypeStats.get("COMMON"));
                        System.out.println("Sąd Najwyższy, liczba orzeczeń: "+courtTypeStats.get("SUPREME"));
                        System.out.println("Sądy administracyjne, liczba orzeczeń: "+courtTypeStats.get("ADMINISTRATIVE"));
                        System.out.println("Trybunał Konstytucyjny, liczba orzeczeń: "+courtTypeStats.get("CONSTITUTIONAL_TRIBUNAL"));
                        System.out.println("Krajowa Izba Odwoławcza, liczba orzeczeń: "+courtTypeStats.get("NATIONAL_APPEAL_CHAMBER"));
                        break;
                    case "regulations":
                        orderVIII o8=new orderVIII(verdicts);
                        break;
                    case "jury":
                        System.out.println("Podaj liczebność składu sędziowskiego...");
                        System.out.print(">");
                        int judgesNo=sint.nextInt();
                        LinkedList<String> jury=new LinkedList<>();
                        System.out.println("Podaj sędziów należących do składu...");
                        for(int i=0;i<judgesNo;i++)
                        {
                            s.reset();
                            System.out.print(">");
                            command=s.nextLine();
                            jury.add(command);
                        }
                        orderIX o9=new orderIX(jury,verdicts,judgesNo);
                        System.out.println("Liczba spraw zadanego składu sędziowskiego: "+o9.getTotal() );
                        break;

                }
            }
        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }

    }
}
