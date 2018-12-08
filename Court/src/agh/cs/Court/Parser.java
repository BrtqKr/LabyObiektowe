package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser extends fromString
{
    private LinkedHashMap<String,Verdict> verdicts;
    private LinkedHashMap<String, Judge> judges;
    private List<Path>DirectoryFiles;
    private Path dir;


    public Parser(Path dir) throws IOException, ParseException
    {
        this.dir=dir;
        this.verdicts=new LinkedHashMap<>();
        this.judges=new LinkedHashMap<>();
        parseDirectory();
        parseFiles();
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
    public void parseFiles() throws IOException, ParseException //wyjmuje orzeczenia z pojedynczego pliku i mieli każde po kolei parserem elementów
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


            String id = item.get("id").toString();
            String typeString = item.get("courtType").toString();
            courtType type;
            type=toType(typeString);
            JSONArray jCases = (JSONArray) item.get("courtCases");
            LinkedList<String> courtCases = new LinkedList<>();
            Iterator<JSONObject> casesIterator = jCases.iterator();
            while (casesIterator.hasNext()) {
                JSONObject caseNum = casesIterator.next();
                String caseNumString = caseNum.get("caseNumber").toString();
                courtCases.add(caseNumString);
            }
            String judgmentTypeString = item.get("judgmentType").toString();
            judgmentType judgmentTypeEnum;
            judgmentTypeEnum=toJudgmentType(judgmentTypeString);
            JSONArray judges = (JSONArray) item.get("judges");
            Iterator<JSONObject> judgesIterator = judges.iterator();
            LinkedList<Judge> currentJudges = new LinkedList<>();
            while (judgesIterator.hasNext()) {
                JSONObject judge = judgesIterator.next();
                String fullName = judge.get("name").toString();



                LinkedList<judgeRole> currentRoles = new LinkedList<>();
                JSONArray roles = (JSONArray) judge.get("specialRoles");
                Iterator<String> rolesIterator = roles.iterator();
                while (rolesIterator.hasNext()) {
                    String roleString = rolesIterator.next();
                    judgeRole role;
                    role=toRole(roleString);
                    currentRoles.add(role);
                }
                if(this.judges.containsKey(fullName))                           //zastanowić się czy każdy sędzia w currentJudges otrzyma
                {                                                               // te role które będą później??
                    Judge tmp=this.judges.get(fullName);
                    tmp.addRole(id,currentRoles);
                    this.judges.put(fullName,tmp);
                    currentJudges.add(tmp);
                }
                else
                {
                    Judge newJudge = new Judge(fullName);
                    newJudge.addRole(id, currentRoles);
                    this.judges.put(fullName,newJudge);
                    currentJudges.add(newJudge);
                }

            }
            JSONObject sourceObject = (JSONObject) item.get("source");
            Object codeObject = sourceObject.get("code");
            String codeString = codeObject.toString();
            Code code;
            code=toCode(codeString);
            Object judgmentUrlObject=sourceObject.get("judgmentUrl");
            String judgementUrl =judgmentUrlObject.toString();
            Object judgmentIdObject=sourceObject.get("judgmentId");
            String judgmentId =judgmentIdObject.toString();
            Object publisherObject=sourceObject.get("publisher");
            String publisher = publisherObject.toString();                              //wywala nullpointer
            Object reviserObject=sourceObject.get("reviser");
            String reviser = reviserObject.toString();
            Object publicationDateObject=sourceObject.get("publicationDate");
            String publicationDate = publicationDateObject.toString();

            JSONArray courtReportersArray = (JSONArray) item.get("courtReporters");
            Iterator<Object> reportersIterator = courtReportersArray.iterator();
            LinkedList<String>reporters=new LinkedList<>();
            while (reportersIterator.hasNext()) {
                String reporter = reportersIterator.next().toString();
                reporters.add(reporter);
            }
            JSONArray referencedRegulations = (JSONArray) item.get("referencedRegulations");
            Iterator<JSONObject> regulationsIterator = referencedRegulations.iterator();
            LinkedList<Regulation> regulations = new LinkedList<>();
            while (regulationsIterator.hasNext()) {
                JSONObject regulation = regulationsIterator.next();
                String title = regulation.get("journalTitle").toString();
                String journalNo = regulation.get("journalNo").toString();
                String journalYear = regulation.get("journalYear").toString();
                String journalEntry = regulation.get("journalEntry").toString();
                String text = regulation.get("text").toString();
                Regulation r = new Regulation(title, journalNo, journalYear, journalEntry, text);
                regulations.add(r);
            }
            String judgmentDate = item.get("judgmentDate").toString();
            Metryka m = new Metryka(id, judgmentDate, type, currentJudges);
            Verdict v = new Verdict(m, courtCases, judgmentTypeEnum, regulations);
            verdicts.put(id,v);


    }
}


