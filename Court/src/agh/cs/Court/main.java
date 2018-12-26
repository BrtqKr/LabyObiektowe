package agh.cs.Court;

import agh.cs.Court.orders.*;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;
/*import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;*/

import org.apache.commons.io.FileUtils;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
            String pathString=linereader.readLine("Podaj ścieżkę do folderu z plikami i opcjonalnie ścieżkę do pliku zapisu historii");
            String[] paths=pathString.split(" ");
            boolean optionalFlag=false;
            Path dir=Paths.get(paths[0]);
            Path history=Paths.get(paths[0]);
            if(paths.length>=2)
            {
                optionalFlag=true;
                history=Paths.get(paths[1]);
            }
            parser parser=new parser(dir,dir);
            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            LinkedHashMap<String, judge> judges=parser.getJudges();
            System.out.println();
            while(true) {
                String command=linereader.readLine(">");
                if(optionalFlag)Files.write(history,(">"+command+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                if (!command.isEmpty()) {
                    consoleFilter c = new consoleFilter(command);
                    if (!c.getOrder().equals(""))
                    {
                        String result="";
                        if (c.checkArgNo()) {
                            switch (c.getOrder()) {
                                case "Quit":
                                    terminal.close();
                                case "help":
                                    orderI o1=new orderI();
                                    result=(o1.getHelpString());
                                    break;
                                case "rubrum":
                                    orderII o2 = new orderII(verdicts);
                                    StringBuilder s=new StringBuilder();
                                    for (int i = 0; i < c.getArgs().size(); i++) {
                                        rubrum r = o2.executeOII(c.getArgs().get(i));
                                        s.append(r.getRubrum());
                                        if (c.getArgs().size() > 1) s.append(System.lineSeparator());
                                    }
                                    result=s.toString();
                                    break;
                                case "content":
                                    orderIII o3 = new orderIII(verdicts, c.getArgs().get(0));
                                    result=(o3.getTextContent());
                                    break;
                                case "judge":
                                    orderIV o4 = new orderIV(judges, c.getArgs().get(0));
                                    judge tmp = o4.getJudgeResult();
                                    String j=(c.getArgs().get(0) + ", liczba orzeczeń: " + tmp.getCasesNumber()+System.lineSeparator());
                                    result=(j);
                                    break;
                                case "judges":
                                    orderV o5 = new orderV(judges);
                                    result=(o5.getRanking());
                                    break;
                                case "months":
                                    orderVI o6 = new orderVI(verdicts);
                                    result=(o6.getStats());
                                    break;
                                case "courts":
                                    orderVII o7 = new orderVII(verdicts);
                                    result=(o7.getCourtTypeStats());
                                    break;
                                case "regulations":
                                    orderVIII o8 = new orderVIII(verdicts);
                                    result=(o8.getRegs());
                                    break;
                                case "jury":

                                    orderIX o9 = new orderIX(verdicts);
                                    result=(o9.getRanking());
                                    break;
                            }
                        } else {
                            result="Podano złą liczbę argumentów lub złe polecenie";
                        }
                        if(optionalFlag)
                        {
                            System.out.println(result);
                            Files.write(history,(result+System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                        }
                        else System.out.println(result);
                    }
                }
            }
        }
        catch (ParseException|IOException ex)
        {
            System.out.println(ex);
        }

    }
}
