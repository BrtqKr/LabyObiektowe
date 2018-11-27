package agh.cs.Court;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class jsonParseSystem
{

    public static void main(String[] args){
        JSONParser parser=new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\barte\\IdeaProjects\\Court\\judgments-348.json"));
            JSONObject jsonObject = (JSONObject) obj;
            LinkedHashMap<String,Key> keys=new LinkedHashMap<>();
            keys.put("items",Key.OBJECT);
            keys.put("courtType",Key.STRING);
            keys.put("courtCases",Key.ARRAY);
            keys.put("caseNumber",Key.STRING);
            keys.put("judgmentType",Key.STRING);
            keys.put("judges",Key.ARRAY);
            keys.put("name",Key.STRING);
            keys.put("function",Key.STRING);
            keys.put("specialRoles",Key.ARRAY);
          /*  keys.put("source",Key.OBJECT);
            keys.put("code",Key.STRING);
            keys.put("judgmentUrl",Key.STRING);
            keys.put("judgmentId",Key.STRING);
            keys.put("publisher",Key.STRING);
            keys.put("reviser",Key.STRING);
            keys.put("publicationDate",Key.STRING);
            keys.put("courtReporters",Key.ARRAY);
            keys.put("decision",Key.STRING);
            keys.put("summary",Key.STRING);
            keys.put("textContent",Key.STRING);
            keys.put("legalBases",Key.ARRAY);
            keys.put("referencedRegulations",Key.ARRAY);
            keys.put("journalTitle",Key.STRING);
            keys.put("journalNo",Key.STRING);
            keys.put("journalYear",Key.STRING);
            keys.put("journalEntry",Key.STRING);
            keys.put("text",Key.STRING);
            keys.put("keywords",Key.ARRAY);
            keys.put("referencedCourtCases",Key.ARRAY);
            keys.put("receiptDate",Key.STRING);
            keys.put("meansOfAppeal",Key.STRING);
            keys.put("judgmentResult",Key.STRING);
            keys.put("lowerCourtJudgments",Key.ARRAY);
            keys.put("personnelType",Key.STRING);
            keys.put("judgmentForm",Key.STRING );
            keys.put("division",Key.OBJECT);
            keys.put("id",Key.STRING);
            keys.put("dissentingOpinions",Key.ARRAY);
            keys.put("textContent",Key.STRING);
            keys.put("authors",Key.STRING);*/
            keys.put("judgmentDate",Key.STRING);
            Parser.parse(keys,jsonObject);

        }
        catch (IOException|ParseException|IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }

}
