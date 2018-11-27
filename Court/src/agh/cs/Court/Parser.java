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
    private LinkedList<LinkedHashMap<String, Object>> verdicts;
    private LinkedHashMap<String, Judge> judges;
    private List<Path>directory;

    public Parser(Path dir)throws IOException,ParseException
    {
        parseDirectory(dir);
        parseFiles();
        recycle(this.verdicts);
    }

    public void parseDirectory(Path dir) throws DirectoryIteratorException,IOException
    {
        List<Path>result=new ArrayList<>();
        try(DirectoryStream<Path> stream=Files.newDirectoryStream(dir,"*.{json}"))
        {
            for(Path entry: stream)
            {
                result.add(entry.getFileName());
            }
        }catch (DirectoryIteratorException ex)
        {
            System.out.println(ex);
        }
        this.directory=result;
    }
    public void parseFiles() throws IOException,ParseException //wyjmuje orzeczenia z pojedynczego pliku i mieli każde po kolei parserem elementów
    {
        JSONParser parser = new JSONParser();
        try {
            for(Path filePath:this.directory)
            {
                JSONObject file = (JSONObject) parser.parse(new FileReader(filePath.toString()));
                JSONArray verdictArray = (JSONArray) file.get("items");
                for (Object iterator : verdictArray)
                {
                    verdicts.add(parseElements((JSONObject) iterator));
                }
            }


        } catch (IOException | ParseException e) {
            System.out.println(e);
        }

    }
    public LinkedHashMap<String, Object> parseElements(JSONObject item) //przechodzę po kluczach w orzeczeniu i wpisuję je do mapy
    {
        LinkedHashMap<String, Object> verdict = new LinkedHashMap<>();
        for (Object iterator : item.keySet()) {
            Object current = item.get(iterator);

            if (current instanceof String) {
                verdict.put(iterator.toString(), current);   //obecny element to string->wrzucam niezmieniony do mapy orzeczenia
            } else if (current instanceof JSONObject)            //obecny element to obiekt->przechodzę po podobiektach i każdy rekurencyjnie wkładam do mapy
            {
                LinkedHashMap<String, Object> subObjects;
                subObjects = parseElements((JSONObject) current);
                verdict.put(current.toString(), subObjects);
            } else {
                verdict.put(iterator.toString(), parseArray((JSONArray) current));            //pomyśleć o dodawaniu w tym miejscu do listy sędziów
            }
        }
        return verdict;
    }
    public Object[] parseArray(JSONArray array) {
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
                LinkedHashMap<String, Object> nextObject;
                nextObject = parseElements((JSONObject) current);
                arrayObjects[index++] = nextObject;
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
                throw new IllegalArgumentException(role + " is not a valid role");
        }
    }
    public LinkedList<LinkedHashMap<String,Object>> getVerdicts()
    {
        return this.verdicts;
    }
    private String currentID;
    private int currentIndex;
    private LinkedList<Judge> currentJudges;
    LinkedHashMap<String,LinkedList<Judge>> result=new LinkedHashMap<>();

    public void recycle(LinkedList<LinkedHashMap<String,Object>> verdicts)throws NullPointerException
    {
        LinkedHashMap<String,LinkedList<Judge>> result=new LinkedHashMap<>();
        for(LinkedHashMap<String,Object> verdict:verdicts)
        {
            this.currentIndex=0;
            for(Object i:verdict.keySet())
            {
                if(i.toString().equals("id"))
                {
                    this.currentID=i.toString();
                }
                if(i.toString().equals("judges"))
                {
                    this.currentJudges=new LinkedList<>();
                    Object[] judgeObject=parseArray((JSONArray)i);
                    for(Object j:judgeObject)
                    {
                        if(j.toString().equals("name"))
                        {
                            Judge currentJudge = new Judge(j.toString());
                            this.currentJudges.add(currentJudge);
                        }
                        if(j.toString().equals("roles"))
                        {
                            LinkedList<judgeRole>currentRoles=new LinkedList<>();
                            Object[] roleObject=parseArray((JSONArray) j);
                            for(Object k:roleObject)
                            {
                                currentRoles.add(objectToRole(k));
                            }
                            this.currentJudges.get(currentIndex).addRoles(this.currentID,currentRoles);
                            this.currentIndex++;
                        }
                    }

                }
            }
            result.put(this.currentID,this.currentJudges);
        }
        this.result=result;
    }
    public LinkedHashMap getJudges()
    {
        return this.result;
    }
}
