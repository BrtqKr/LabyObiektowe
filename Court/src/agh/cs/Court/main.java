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
            System.out.println("NATIONAL_APPEAL_CHAMBER: "+statsPerType.get("NATIONAL_APPEAL_CHAMBER"));
            */
            consoleFilter test=new consoleFilter("rubrum \"nfeiusafa\"nfoasejfoa\"negfioase\"");
            System.out.println(test.getOrder()+"...");
            System.out.println(test.getArgs().size());

            for(int i=0;i<test.getArgs().size();i++)
            {
                System.out.println(test.getArgs().get(i));
            }


            Scanner s=new Scanner(System.in);
            System.out.print("Podaj ścieżkę do folderu JSON...");
            String path=s.nextLine();
            Path dir=Paths.get(path);
            System.out.print("Podaj ścieżkę do folderu HTML...");
            String htmlPath=s.nextLine();
            Path htmlDir=Paths.get(htmlPath);
            parser parser=new parser(dir,htmlDir);


            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            LinkedHashMap<String, judge> judges=parser.getJudges();
            System.out.println();
            while(true)
            {
                System.out.print(">");
                String command=s.nextLine();
                consoleFilter c=new consoleFilter(command); //working
                if(c.checkArgNo()) {
                    switch (c.getOrder()) {
                        case "Quit":
                            System.exit(0);
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
                            /*System.out.println("Podaj sygnaturę orzeczenia...");
                            System.out.print(">");
                            command = s.nextLine();
                            */
                            orderII o2 = new orderII(verdicts);
                            for(int i=0;i<c.getArgs().size();i++) {
                                rubrum r = o2.executeOII(c.getArgs().get(i));
                                r.printRubrum();
                                if(c.getArgs().size()>1)System.out.println();
                            }
                            break;
                        case "content":
                            /*System.out.println("Podaj sygnaturę orzeczenia...");
                            System.out.print(">");
                            command = s.nextLine();*/
                            orderIII o3 = new orderIII(verdicts, c.getArgs().get(0));
                            break;
                        case "judge":
                            /*System.out.println("Podaj imię i nazwisko sędziego...");
                            System.out.print(">");
                            command = s.nextLine();*/
                            orderIV o4 = new orderIV(judges, c.getArgs().get(0));
                            judge tmp = o4.getJudgeResult();
                            System.out.println(command + ", liczba orzeczeń: " + tmp.getCasesNumber());
                            break;
                        case "judges":
                            orderV o5 = new orderV(judges);
                            break;
                        case "months":
                            orderVI o6 = new orderVI(verdicts);
                            int[] stats = o6.getStats();
                            for (int i = 1; i < stats.length; i++) {
                                switch (i) {
                                    case 1:
                                        System.out.print("Styczeń, ");
                                        break;
                                    case 2:
                                        System.out.print("Luty, ");
                                        break;
                                    case 3:
                                        System.out.print("Marzec, ");
                                        break;
                                    case 4:
                                        System.out.print("Kwiecień, ");
                                        break;
                                    case 5:
                                        System.out.print("Maj, ");
                                        break;
                                    case 6:
                                        System.out.print("Czerwiec, ");
                                        break;
                                    case 7:
                                        System.out.print("Lipiec, ");
                                        break;
                                    case 8:
                                        System.out.print("Sierpień, ");
                                        break;
                                    case 9:
                                        System.out.print("Wrzesień, ");
                                        break;
                                    case 10:
                                        System.out.print("Październik, ");
                                        break;
                                    case 11:
                                        System.out.print("Listopad, ");
                                        break;
                                    case 12:
                                        System.out.print("Grudzień, ");
                                        break;
                                }
                                System.out.println("liczba orzeczeń: " + stats[i]);
                            }
                            break;
                        case "courts":
                            orderVII o7 = new orderVII(verdicts);
                            LinkedHashMap<String, Integer> courtTypeStats = o7.getCourtTypeStats();
                            System.out.println("Sądy powszechne, liczba orzeczeń: " + courtTypeStats.get("COMMON"));
                            System.out.println("Sąd Najwyższy, liczba orzeczeń: " + courtTypeStats.get("SUPREME"));
                            System.out.println("Sądy administracyjne, liczba orzeczeń: " + courtTypeStats.get("ADMINISTRATIVE"));
                            System.out.println("Trybunał Konstytucyjny, liczba orzeczeń: " + courtTypeStats.get("CONSTITUTIONAL_TRIBUNAL"));
                            System.out.println("Krajowa Izba Odwoławcza, liczba orzeczeń: " + courtTypeStats.get("NATIONAL_APPEAL_CHAMBER"));
                            break;
                        case "regulations":
                            orderVIII o8 = new orderVIII(verdicts);
                            break;
                        case "jury":

                            orderIX o9 = new orderIX(verdicts);
                            int[] res = o9.getTotal();
                            for (int i = 1; i < 16; i++) {
                                if (i == 1) System.out.println(i + " sędzia: " + res[i]);
                                else System.out.println(i + " sędziów: " + res[i]);
                            }
                            break;

                    }
                }
                else
                {
                    System.out.println("Podano złą liczbę argumentów lub złe polecenie");
                }
            }
        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }

    }
}
