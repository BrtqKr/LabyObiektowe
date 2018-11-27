package agh.cs.CourtProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class jsonParser
{

    public static void main(String[] args){
        JSONParser parser=new JSONParser();
        try {
            Set<Judge> judgeSet = new HashSet<>();
            LinkedHashMap<String, metryka> hashMetryka;

            Object obj = parser.parse(new FileReader("C:\\Users\\barte\\IdeaProjects\\CourtProject\\judgments-348.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray items = (JSONArray) jsonObject.get("items");                                                          //iteruj po całych obiektach
            Iterator<JSONObject>itemsIterator = items.iterator();
            while (itemsIterator.hasNext())
            {
                JSONObject item=itemsIterator.next();
                String id = item.get("id").toString();                                                                      //od razu ID

                String typeString=item.get("courtType").toString();                                                         //od razu typ sądu i konwersja
                courtType type;
                switch (typeString)
                {
                    case "CONSTITUTIONAL_TRIBUNAL":
                        type=courtType.CONSTITUTIONAL_TRIBUNAL;
                    case "SUPREME":
                        type=courtType.SUPREME;
                    case "NATIONAL_APPEAL_CHAMBER":
                        type=courtType.NATIONAL_APPEAL_CHAMBER;
                    case "COMMON":
                        type=courtType.COMMON;
                    case "ADMINISTRATIVE":
                        type=courtType.ADMINISTRATIVE;
                }

                LinkedList<String> cases=new LinkedList<>();                                                                 //iteruj po srawacj
                JSONArray jCase=(JSONArray)item.get("courtCases");
                Iterator<JSONObject>jCasesIterator=jCase.iterator();
                while (jCasesIterator.hasNext())
                {
                    JSONObject itemCase=jCasesIterator.next();
                    String caseNum=itemCase.get("caseNumber").toString();
                    cases.add(caseNum);                                                                                      //rozprawy do kolekcji
                }

                    String judgmentType = item.get("judgmentType").toString();                                                     //typ wyroku

                    JSONArray judges = (JSONArray) item.get("judges");
                    Iterator<JSONObject> judgesIterator = judges.iterator();                                                       //iteruj po sędziach
                    while (judgesIterator.hasNext())
                    {
                        JSONObject judge = judgesIterator.next();
                        String fullName = judge.get("name").toString();                                                            //dodaj sędziego
                        Judge newJudge = new Judge(fullName);
                        newJudge.addCase(id);
                        judgeSet.add(newJudge);

                        JSONArray roles = (JSONArray) judge.get("specialRoles");                                                     //role sędziego
                        Iterator<JSONObject> rolesIterator = roles.iterator();
                        while (rolesIterator.hasNext())
                        {
                            JSONObject roleObject=rolesIterator.next();
                            String roleString = roleObject.get("specialRoles").toString();
                            judgeRole role =null;                                                                                   //sprawdzić czy puste przy wyświetlaniu
                            switch (roleString) {
                                case "PRESIDING_JUDGE": role = judgeRole.PRESIDING_JUDGE;
                                case "REPORTING_JUDGE": role = judgeRole.REPORTING_JUDGE;
                                case "REASONS_FOR_JUDGMENT_AUTHOR": role = judgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
                            }
                            newJudge.addRole(id,role);
                        }
                    }
                    JSONObject sourceObject=(JSONObject)item.get("source");                                                         //źródło orzeczenia
                    JSONObject codeObject=(JSONObject)sourceObject.get("code");
                    String codeString=codeObject.toString();
                    Code code;
                    switch (codeString)
                    {
                        case"COMMON_COURT":code=Code.COMMON_COURT;
                        case"SUPREME_COURT":code= Code.SUPREME_COURT;
                        case"CONSTITUTIONAL_TRIBUNAL":code=Code.CONSTITUTIONAL_TRIBUNAL;
                        case"NATIONAL_APPEAL_CHAMBER":code=Code.NATIONAL_APPEAL_CHAMBER;
                    }
                    String judgementUrl=sourceObject.get("judgmentUrl").toString();
                    String judgmentId=sourceObject.get("judgmentId").toString();
                    String publisher=sourceObject.get("publisher").toString();
                    String reviser=sourceObject.get("reviser").toString();
                    String publicationDate=sourceObject.get("publicationDate").toString();

                    JSONArray courtReportersArray=(JSONArray)item.get("courtReporters");                                        //protokolanci
                    Iterator<JSONObject> reportersIterator=courtReportersArray.iterator();
                    while(reportersIterator.hasNext())
                    {
                        String reporter=reportersIterator.next().toString();
                    }
                    String decision=item.get("decision").toString();
                    String summary=item.get("summary").toString();
                    String textContent=item.get("textContent").toString();
                    JSONArray legalBases=(JSONArray)item.get("legalBases");
                    Iterator<JSONObject>basesIterator=legalBases.iterator();
                    while(basesIterator.hasNext())
                    {
                        String legalBase=basesIterator.toString();
                    }

            }

        }
        catch (IOException|ParseException|IllegalArgumentException ex)
        {
            System.out.println(ex);
        }
    }
}
