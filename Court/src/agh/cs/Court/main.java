package agh.cs.Court;

import agh.cs.Court.orders.*;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;
/*import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;*/

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
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
            o2.execute("II AKa 105/11");
*/

            Terminal terminal = TerminalBuilder
                    .builder()
                    .encoding("UTF-8")
                    .system(true)
                    .build();

            LineReader linereader = LineReaderBuilder.builder().terminal(terminal).build();
            String path=linereader.readLine("Podaj ścieżkę do folderu JSON...");
            Path dir=Paths.get(path);
            String htmlPath=linereader.readLine("Podaj ścieżkę do folderu HTML...");
            Path htmlDir=Paths.get(htmlPath);
            parser parser=new parser(dir,htmlDir);


            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            LinkedHashMap<String, judge> judges=parser.getJudges();
            System.out.println();
            while(true) {
                String command=linereader.readLine(">");
                if (!command.isEmpty()) {
                    consoleFilter c = new consoleFilter(command);
                    if (!c.getOrder().equals("")) {
                        if (c.checkArgNo()) {
                            switch (c.getOrder()) {
                                case "Quit":
                                    terminal.close();
                                case "help":
                                    System.out.println("Dostępne komendy: ");
                                    System.out.println("Quit - zakończenie pracy programu");
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
                                    orderII o2 = new orderII(verdicts);
                                    for (int i = 0; i < c.getArgs().size(); i++) {
                                        rubrum r = o2.executeOII(c.getArgs().get(i));
                                        r.printRubrum();
                                        if (c.getArgs().size() > 1) System.out.println();
                                    }
                                    break;
                                case "content":
                                    orderIII o3 = new orderIII(verdicts, c.getArgs().get(0));
                                    break;
                                case "judge":
                                    orderIV o4 = new orderIV(judges, c.getArgs().get(0));
                                    judge tmp = o4.getJudgeResult();
                                    System.out.println(c.getArgs().get(0) + ", liczba orzeczeń: " + tmp.getCasesNumber());
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
                                    System.out.println("Sądy Powszechne, liczba orzeczeń: " + courtTypeStats.get("COMMON"));
                                    System.out.println("Sąd Najwyższy, liczba orzeczeń: " + courtTypeStats.get("SUPREME"));
                                    int admTotal=courtTypeStats.get("Wojewódzki Sąd Administracyjny")+courtTypeStats.get("Naczelny Sąd Administracyjny");
                                    System.out.println("Sądy Administracyjne, liczba orzeczeń: " + admTotal+" ,w tym...");
                                    System.out.println("    Wojewódzkie Sądy Administracyjne, liczba orzeczeń: "+courtTypeStats.get("Wojewódzki Sąd Administracyjny"));
                                    System.out.println("    Naczelne Sądy Administracyjne, liczba orzeczeń: "+courtTypeStats.get("Naczelny Sąd Administracyjny"));
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
                        } else {
                            System.out.println("Podano złą liczbę argumentów lub złe polecenie");
                        }
                        System.out.println();
                    }
                }
            }
        }
        catch (ParseException|IOException  ex)
        {
            System.out.println(ex);
        }

    }
}
