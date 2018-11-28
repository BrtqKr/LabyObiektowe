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

public class ParserV2
{
    private LinkedHashMap<String,Verdict> verdicts;
    private LinkedHashMap<String, Judge> judges;
    private List<Path>DirectoryFiles;
    private Path dir;


    public ParserV2(Path dir) throws IOException
    {
        this.dir=dir;
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
            switch (typeString) {
                case "CONSTITUTIONAL_TRIBUNAL":
                    type = courtType.CONSTITUTIONAL_TRIBUNAL;
                    break;
                case "SUPREME":
                    type = courtType.SUPREME;
                    break;
                case "NATIONAL_APPEAL_CHAMBER":
                    type = courtType.NATIONAL_APPEAL_CHAMBER;
                    break;
                case "COMMON":
                    type = courtType.COMMON;
                    break;
                case "ADMINISTRATIVE":
                    type = courtType.ADMINISTRATIVE;
                    break;
                default:
                    throw new IllegalArgumentException(typeString + " is not a legal court type");
            }
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
            switch (judgmentTypeString) {
                case "DECISION":
                    judgmentTypeEnum = judgmentType.DECISION;
                    break;
                case "RESOLUTION":
                    judgmentTypeEnum = judgmentType.RESOLUTION;
                    break;
                case "SENTENCE":
                    judgmentTypeEnum = judgmentType.SENTENCE;
                    break;
                case "REASONS":
                    judgmentTypeEnum = judgmentType.REASONS;
                    break;
                default:
                    throw new IllegalArgumentException(judgmentTypeString + " is not a legal judgment type");


            }
            JSONArray judges = (JSONArray) item.get("judges");
            Iterator<JSONObject> judgesIterator = judges.iterator();
            LinkedList<Judge> currentJudges = new LinkedList<>();
            while (judgesIterator.hasNext()) {
                JSONObject judge = judgesIterator.next();
                String fullName = judge.get("name").toString();
                Judge newJudge = new Judge(fullName);
                currentJudges.add(newJudge);

                LinkedList<judgeRole> currentRoles = new LinkedList<>();
                JSONArray roles = (JSONArray) judge.get("specialRoles");
                Iterator<JSONObject> rolesIterator = roles.iterator();
                while (rolesIterator.hasNext()) {
                    JSONObject roleObject = rolesIterator.next();
                    String roleString = roleObject.get("specialRoles").toString();
                    judgeRole role;                                                                                   //sprawdzić czy puste przy wyświetlaniu
                    switch (roleString) {
                        case "PRESIDING_JUDGE":
                            role = judgeRole.PRESIDING_JUDGE;
                            break;
                        case "REPORTING_JUDGE":
                            role = judgeRole.REPORTING_JUDGE;
                            break;
                        case "REASONS_FOR_JUDGMENT_AUTHOR":
                            role = judgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
                            break;
                        default:
                            role = null;
                    }
                    currentRoles.add(role);
                }
                newJudge.addRole(id, currentRoles);
            }
            JSONObject sourceObject = (JSONObject) item.get("source");                                                         //źródło orzeczenia
            JSONObject codeObject = (JSONObject) sourceObject.get("code");
            String codeString = codeObject.toString();
            Code code;
            switch (codeString) {
                case "COMMON_COURT":
                    code = Code.COMMON_COURT;
                    break;
                case "SUPREME_COURT":
                    code = Code.SUPREME_COURT;
                    break;
                case "CONSTITUTIONAL_TRIBUNAL":
                    code = Code.CONSTITUTIONAL_TRIBUNAL;
                    break;
                case "NATIONAL_APPEAL_CHAMBER":
                    code = Code.NATIONAL_APPEAL_CHAMBER;
                    break;
            }
            String judgementUrl = sourceObject.get("judgmentUrl").toString();
            String judgmentId = sourceObject.get("judgmentId").toString();
            String publisher = sourceObject.get("publisher").toString();
            String reviser = sourceObject.get("reviser").toString();
            String publicationDate = sourceObject.get("publicationDate").toString();

            JSONArray courtReportersArray = (JSONArray) item.get("courtReporters");                                        //protokolanci
            Iterator<JSONObject> reportersIterator = courtReportersArray.iterator();
            while (reportersIterator.hasNext()) {
                String reporter = reportersIterator.next().toString();
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



    }
}


