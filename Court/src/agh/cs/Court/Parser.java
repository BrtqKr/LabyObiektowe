package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class Parser
{
    private LinkedHashMap<String,Verdict> verdicts;
    private LinkedHashMap<String, Judge> judges;
    private String currentID;
    private LinkedList<Judge> currentJudges;
    private List<Path>DirectoryFiles;
    private Path dir;

    public Parser(Path dir) throws IOException
    {
        this.dir=dir;
    }

    public void parseDirectory() throws DirectoryIteratorException,IOException
    {
        List<Path>result=new ArrayList<>();
        try(DirectoryStream<Path> stream=Files.newDirectoryStream(this.dir,"*.{json}"))
        {
            for(Path entry: stream)
            {
                result.add(entry.getFileName());
            }
        }catch (DirectoryIteratorException ex)
        {
            System.out.println(ex);
        }
        this.DirectoryFiles=result;
    }
    public void parseFiles() throws IOException,ParseException //wyjmuje orzeczenia z pojedynczego pliku i mieli każde po kolei parserem elementów
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
    public void parseElements(JSONObject item) //przechodzę po kluczach w orzeczeniu i wpisuję je do mapy
    {
        LinkedHashMap<String, Object> verdict = new LinkedHashMap<>();
        for (Object iterator : item.keySet()) {
            Object current = item.get(iterator);

            if (current instanceof String) {
                if(iterator.toString().equals("id"))
                {
                    this.currentID=current.toString();
                    Verdict v=new Verdict();
                    v.updateID(currentID);
                    verdicts.put(currentID,v);
                }
                else if(iterator.toString().equals("judgmentDate"))
                {
                    verdicts.get(currentID).updateDate(current.toString());
                }
                else if(iterator.toString().equals("courtType"))
                {
                    verdicts.get(currentID).updateCourtType(objectToType(current));
                }
            } else if (current instanceof JSONObject)            //obecny element to obiekt->przechodzę po podobiektach i każdy rekurencyjnie wkładam do mapy
            {
                if(current.toString().equals("judges"))
                {
                    LinkedList<Judge>judges=new LinkedList<>();
                }
                for(Object i:((JSONObject) current).keySet())
                {
                    parseElements((JSONObject)i);
                }
            } else {
                Object[] parsedArray=parseArray((JSONArray) current);
            }
        }

    }
    public Object[] parseArray(JSONArray array) {                   //dokończyć i poprawić parsowanie listy i obiektu
        Object[] arrayObjects = new Object[(array.size())];
        Iterator iter = array.iterator();
        int index = 0;
        for (Object object : array) {
            Object current = array.get(index);
            if (current instanceof String) {
                arrayObjects[index++] = current;
            } else if (object instanceof JSONArray) {
                Object[] nextArray = parseArray((JSONArray) current);
                arrayObjects[index++] = nextArray;
            } else {
                parseElements((JSONObject) current);
            }
        }
        return arrayObjects;
    }
    public judgeRole objectToRole(Object role) {
        switch (role.toString()) {
            case "PRESIDING_JUDGE":
                return judgeRole.PRESIDING_JUDGE;
            case "REPORTING_JUDGE":
                return judgeRole.REPORTING_JUDGE;
            case "REASONS_FOR_JUDGMENT_AUTHOR":
                return judgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
            default:
                throw new IllegalArgumentException(role.toString() + " is not a valid role");
        }
    }
    public courtType objectToType(Object type)
    {
        switch (type.toString())
        {
            case "COMMON": return courtType.COMMON;
            case "SUPREME": return courtType.SUPREME;
            case "ADMINISTRATIVE": return courtType.ADMINISTRATIVE;
            case "CONSTITUTIONAL_TRIBUNAL":return courtType.CONSTITUTIONAL_TRIBUNAL;
            case "NATIONAL_APPEAL_CHAMBER":return courtType.NATIONAL_APPEAL_CHAMBER;
            default:throw new IllegalArgumentException(type.toString()+"is not a valid court type");
        }
    }
    public  LinkedHashMap<String,Verdict>getVerdicts()
    {
        return this.verdicts;
    }

}
