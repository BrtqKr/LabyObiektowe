package agh.cs.Court;

import agh.cs.Court.structures.judge;
import agh.cs.Court.structures.regulation;
import agh.cs.Court.structures.rubrum;
import agh.cs.Court.structures.verdict;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        //parseHTMLDirectory(htmlDir);
        //parseHTMLFiles();
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
            Long idLong = (Long)item.get("id");
            String id=idLong.toString();
            String typeString = (String)item.get("courtType");
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
            String judgmentTypeString = (String)item.get("JudgmentType");
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
                    currentRoles.add(roleString);
                }
                if(this.judges.containsKey(fullName))
                {
                    agh.cs.Court.structures.judge tmp=this.judges.get(fullName);
                    tmp.addRole(courtCases.get(0),currentRoles);
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

            JSONObject sourceObject = (JSONObject) item.get("source");
            Object codeObject = sourceObject.get("code");
            String codeString =(String) codeObject;
            Object judgmentUrlObject=sourceObject.get("judgmentUrl");
            String judgementUrl =(String)judgmentUrlObject;
            Object judgmentIdObject=sourceObject.get("judgmentId");
            String judgmentId =(String)judgmentIdObject;
            Object publisherObject=sourceObject.get("publisher");
            String publisher = (String) publisherObject;
            Object reviserObject=sourceObject.get("reviser");
            String reviser = (String) reviserObject;
            Object publicationDateObject=sourceObject.get("publicationDate");
            String publicationDate = (String)publicationDateObject;
            JSONArray courtReportersArray = (JSONArray) item.get("courtReporters");
            Iterator<Object> reportersIterator = courtReportersArray.iterator();
            LinkedList<String>reporters=new LinkedList<>();
            while (reportersIterator.hasNext()) {
                String reporter = (String)reportersIterator.next();
                reporters.add(reporter);
            }
            String textContentString=(String)item.get("textContent");
            JSONArray referencedRegulations = (JSONArray) item.get("referencedRegulations");
            Iterator<JSONObject> regulationsIterator = referencedRegulations.iterator();
            LinkedList<regulation> regulations = new LinkedList<>();
            while (regulationsIterator.hasNext()) {
                JSONObject regulation = regulationsIterator.next();
                String title = (String)regulation.get("journalTitle");
                Long journalNoLong = (Long)regulation.get("journalNo");
                String journalNo=journalNoLong.toString();
                Long journalYearLong = (Long)regulation.get("journalYear");
                String journalYear=journalYearLong.toString();
                Long journalEntryLong = (Long)regulation.get("journalEntry");
                String journalEntry=journalEntryLong.toString();
                String text =(String) regulation.get("text");
                agh.cs.Court.structures.regulation r = new regulation(title, journalNo, journalYear, journalEntry, text);
                regulations.add(r);
            }
            String judgmentDate = (String)item.get("judgmentDate");
            rubrum m = new rubrum(caseNo, judgmentDate, typeString, currentJudges,currentJury);
            verdict v = new verdict(m, courtCases, judgmentTypeString, regulations,textContentString,id,caseNo);
            verdicts.put(caseNo,v);
    }
/*
    public void parseHTMLDirectory(Path htmlDir)
    {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(htmlDir)) {   //dodać coś żeby parsowało tylko html
            for (Path entry : stream) {
                if(!Files.isDirectory(entry))
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
                 System.out.println(doc.title());
                String caseNum=(doc.select("war_header").text());
                //String[]tab=caseNum.split("-",2);
                //System.out.println(tab[0]);
                System.out.println(caseNum);
            }
}
*/

    public LinkedHashMap<String, verdict> getVerdicts()
    {
        return this.verdicts;
    }
    public LinkedHashMap<String, judge> getJudges()
    {
        return this.judges;
    }

    public List<Path> getHtmlDirectoryFiles() {
        return HtmlDirectoryFiles;
    }
}


