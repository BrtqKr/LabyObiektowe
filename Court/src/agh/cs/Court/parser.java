package agh.cs.Court;

import agh.cs.Court.enums.JudgeRole;
import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.regulation;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.probeContentType;

public class parser {
    private LinkedHashMap<String, verdict> verdicts;
    private LinkedHashMap<String, judge> judges;
    private List<Path>DirectoryFiles;
    private Path dir;

    private List<Path>HtmlDirectoryFiles;



    public parser(Path dir,Path htmlDir) throws IOException, ParseException
    {
        this.dir=dir;
        this.verdicts=new LinkedHashMap<>();
        this.judges=new LinkedHashMap<>();
        this.HtmlDirectoryFiles=new LinkedList<>();
        parseDirectory();
        parseFiles();
        parseHTMLDirectory(htmlDir);
        parseHTMLFiles();
    }

    public void parseDirectory() throws DirectoryIteratorException,IOException
    {
        List<Path> result=new ArrayList<>();
        try(DirectoryStream<Path> stream= Files.newDirectoryStream(this.dir,"*.{json}"))
        {
            for(Path entry: stream)
            {
                result.add(entry.getFileName());
            }
        }catch (DirectoryIteratorException|IOException ex)
        {
            System.out.println(ex);
        }
        this.DirectoryFiles=result;
    }
    public void parseFiles() throws IOException, ParseException
    {
        JSONParser parser = new JSONParser();
        try {
            for(Path filePath:this.DirectoryFiles)
            {
                JSONObject file = (JSONObject) parser.parse(new FileReader(this.dir.toString()+"/"+filePath.toString()));
                JSONArray verdictArray = (JSONArray) file.get("items");
                for (Object iterator : verdictArray)
                {
                    parseElements((JSONObject) iterator);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }

    }
    public void parseElements(JSONObject item)
    {
            String typeString = (String)item.get("courtType");
            String courtType;
            switch (typeString)
            {
                case "COMMON":courtType="Sąd Powszechny";
                break;
                case "SUPREME":courtType="Sąd Najwyższy";
                break;
                case "ADMINISTRATIVE":courtType="Sąd Administracyjny";
                break;
                case "CONSTITUTIONAL_TRIBUNAL":courtType="Trybunał Konstytucyjny";
                break;
                case "NATIONAL_APPEAL_CHAMBER":courtType="Krajowa Izba Odwoławcza";
                break;
                default:throw new IllegalArgumentException("Illegal court type");
            }

            JSONArray jCases = (JSONArray) item.get("courtCases");
            String caseNo;
            LinkedList<String> courtCases = new LinkedList<>();
            Iterator<JSONObject> casesIterator = jCases.iterator();
            while (casesIterator.hasNext()) {
                JSONObject caseNum = casesIterator.next();
                String caseNumString = (String)caseNum.get("caseNumber");
                courtCases.add(caseNumString);
            }
            caseNo=courtCases.get(0);
            JSONArray judges = (JSONArray) item.get("judges");
            Iterator<JSONObject> judgesIterator = judges.iterator();
            LinkedList<judge> currentJudges = new LinkedList<>();
            LinkedList<String> currentJury= new LinkedList<>();
            while (judgesIterator.hasNext()) {
                JSONObject judge = judgesIterator.next();
                String fullName = (String)judge.get("name");
                currentJury.add(fullName);
                LinkedList<String> currentRoles = new LinkedList<>();
                JSONArray roles = (JSONArray) judge.get("specialRoles");
                Iterator<String> rolesIterator = roles.iterator();
                while (rolesIterator.hasNext()) {
                    String roleString = rolesIterator.next();
                    String judgeRole;
                    switch (roleString)
                    {
                        case "PRESIDING_JUDGE":judgeRole="przewodniczący";
                        break;
                        case "REPORTING_JUDGE":judgeRole="sprawozdawca";
                        break;
                        case "REASONS_FOR_JUDGMENT_AUTHOR":judgeRole="autor uzasadnienia";
                        break;
                        default:throw new IllegalArgumentException ("Illegal role");
                    }
                    currentRoles.add(judgeRole);
                }
                if(this.judges.containsKey(fullName))
                {
                    agh.cs.Court.structures.judge tmp=this.judges.get(fullName);
                    tmp.addRole(caseNo,currentRoles);
                    tmp.addCase(caseNo);
                    this.judges.replace(fullName,tmp);
                    currentJudges.add(tmp);
                }
                else
                {
                    agh.cs.Court.structures.judge newJudge = new judge(fullName);
                    newJudge.addRole(courtCases.get(0), currentRoles);
                    newJudge.addCase(caseNo);
                    this.judges.put(fullName,newJudge);
                    currentJudges.add(newJudge);
                }

            }
            String textContentString=(String)item.get("textContent");
            JSONArray referencedRegulations = (JSONArray) item.get("referencedRegulations");
            Iterator<JSONObject> regulationsIterator = referencedRegulations.iterator();
            LinkedList<regulation> regulations = new LinkedList<>();
            while (regulationsIterator.hasNext()) {
                JSONObject regulation = regulationsIterator.next();
                String title = (String)regulation.get("journalTitle");
                agh.cs.Court.structures.regulation r = new regulation(title);//, journalNo, journalYear, journalEntry, text);
                regulations.add(r);
            }
            String judgmentDate = (String)item.get("judgmentDate");
            rubrum m = new rubrum(caseNo, judgmentDate, courtType, currentJudges,currentJury);
            verdict v = new verdict(m,/* courtCases, judgmentTypeString,*/ regulations,textContentString,caseNo);
            verdicts.put(caseNo,v);
    }

    public void parseHTMLDirectory(Path htmlDir)
    {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(htmlDir)) {   //dodać coś żeby parsowało tylko html
            for (Path entry : stream) {
                if(!Files.isDirectory(entry)&& FilenameUtils.getExtension(entry.toString()).equals("html"))
                {
                    this.HtmlDirectoryFiles.add(entry);
                }
                else
                {
                    parseHTMLDirectory(entry);
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void parseHTMLFiles() throws IOException
    {

            for (Path iter : this.HtmlDirectoryFiles) {
                Document doc = Jsoup.parse(new File(iter.toString()), "UTF-8");
                String[] tab=doc.title().split(" - ",2);
                String caseNo=tab[0];                                                           //numer sprawy
                String judgmentDate="";
                String courtType="";
                LinkedList<judge> currentJudges = new LinkedList<>();
                LinkedList<String> currentRoles = new LinkedList<>();
                LinkedList<String> currentJury= new LinkedList<>();
                LinkedList<regulation> regulations=new LinkedList<>();


                Elements table=doc.select("tr.niezaznaczona");                          //tabela
                for(Element x:table)
                {
                    String key= x.select("td.lista-label").text();
                    String value=x.select("td.info-list-value").text();


                    if(key.equals("Sąd"))                                                        //typ sądu
                    {
                        String[] courtParts=value.split(" ");
                        courtType=courtParts[0]+" "+courtParts[1]+" "+courtParts[2];

                    }
                    if(key.equals("Data orzeczenia"))                                               //data
                    {
                        String[] dateParts=value.split(" ");
                        judgmentDate=dateParts[0];
                    }
                    if(key.equals("Sędziowie"))                                                      //sędziowie
                    {

                        String judgeString = x.select("td.info-list-value").toString().split("<td class=\"info-list-value\"> ")[1];
                        for (String j : judgeString.split("<br>")) {
                            j = j.replaceAll(" </td>", "");
                            String[] judgeInfo = j.split("/");

                            String name = judgeInfo[0];                                                //imię
                            if(name.charAt((name.length()-1))==' ')name=name.substring(0,name.length()-1); //usunięcie ewentualnych spacji z końca
                            currentJury.add(name);
                            String functionString;
                            if(judgeInfo.length==2)
                            {
                                functionString=judgeInfo[1];
                                String functionsString=functionString.split("/")[0];                   //funkcje
                                String[] functions=functionsString.split(" ");

                                for(int i=0;i<functions.length;i++)
                                {
                                    currentRoles.add(functions[i]);
                                }
                            }

                            if(this.judges.containsKey(name))
                            {
                                agh.cs.Court.structures.judge tmp=this.judges.get(name);
                                tmp.addRole(caseNo,currentRoles);
                                tmp.addCase(caseNo);
                                this.judges.replace(name,tmp);
                                currentJudges.add(tmp);
                            }
                            else
                            {
                                agh.cs.Court.structures.judge newJudge = new judge(name);
                                newJudge.addRole(caseNo, currentRoles);
                                newJudge.addCase(caseNo);
                                this.judges.put(name,newJudge);
                                currentJudges.add(newJudge);
                            }
                        }
                    }
                    if(key.equals("Powołane przepisy"))
                    {

                        x.select("a").remove();
                         String[] regs=x.select("td.info-list-value").toString().split("<br>");
                         for(int i=0;i<regs.length;i++)
                         {
                             regs[i]=regs[i].replace("<span class=\"nakt\">","").replace("</span>","").replace("</td>","");
                             for(int j=0;j<regs[i].length()-1;j++)
                             {
                                 if(regs[i].charAt(j)!=' ')
                                 {
                                     regs[i]=regs[i].substring(j);
                                     break;
                                 }
                             }
                             if(i%2==1)
                             {
                                 regulation r=new regulation(regs[i]);
                                 regulations.add(r);
                             }
                         }
                    }
                }
                ;
                String textContent = doc.select("span.info-list-value-uzasadnienie").text();
                rubrum m = new rubrum(caseNo, judgmentDate, courtType, currentJudges,currentJury);
                verdict v = new verdict(m, /*courtCases, judgmentTypeString,*/ regulations,textContent,caseNo);
                verdicts.put(caseNo,v);

            }
}
    public LinkedHashMap<String, verdict> getVerdicts()
    {
        return this.verdicts;
    }
    public LinkedHashMap<String, judge> getJudges()
    {
        return this.judges;
    }
}


