package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public abstract class typeParser {
    protected LinkedHashMap<String,metryka>metryki=new LinkedHashMap<>();
    private String lastID;
    protected LinkedHashMap<String,ArrayList<Judge>> judges=new LinkedHashMap<>();
    protected ArrayList<Judge> currentJudgeList;
    public Judge currentJudge;


    public void updateLastID(String id)
    {
        this.lastID=id;
    }
    public String getLastID()
    {
        return this.lastID;
    }
    public void updateJudges(String ID,Judge j)
    {
        judges.put(ID,this.currentJudgeList);
    }
    public void parseString(String key, JSONObject upper)
    {   if(key.equals("id")&& upper.toString().equals("items"))
        {
            metryka m=new metryka();
            metryki.put(upper.get(key).toString(),m);
            updateLastID(upper.get(key).toString());
        }
        else if(key.equals("judgmentDate"))
        {
            metryka m=metryki.get(getLastID());
            m.addDate(upper.get(key).toString());
            metryki.put(getLastID(),m);
        }
        else if(key.equals("courtType"))
        {
            String type=upper.get(key).toString();
            metryka m=metryki.get(type);
            m.addCourtType(typeConvert(type));
        }
        else if(key.equals("name"))
        {
            this.currentJudge=new Judge();
            String name=upper.get(key).toString();
            currentJudge.addFullName(name);
        }
        else if(key.equals("function"))
        {
            String function=upper.get(key).toString();
            currentJudge.addFunction(function);
        }
    }
    public void parseArray(String key,JSONObject upper)
    {
        JSONArray newArray=(JSONArray) upper.get(key);
        Iterator<JSONObject> newIter=newArray.iterator();
        if(key.equals("judges"))
        {
            while (newIter.hasNext())
            {
                JSONObject judge=newIter.next();
                parseObject("judges",judge);
            }
        }
        if(key.equals("specialRoles"))
        {
            while(newIter.hasNext())
            {
                JSONObject role=newIter.next();
                String roleString= role.get(key).toString();
                currentJudge.addRole(getLastID(),roleConvert(roleString));
            }
            updateJudges(getLastID(),currentJudge);
        }

    }
    public void parseObject(String key,JSONObject upper)
    {
        JSONObject newObject=(JSONObject)upper.get(key);
        String newString=newObject.toString();
        if(newString.equals("judges"))
        {
            this.currentJudgeList=new ArrayList<>();
        }
    }
    public courtType typeConvert(String element)
    {

        switch (element)
        {
            case "CONSTITUTIONAL_TRIBUNAL":
                return courtType.CONSTITUTIONAL_TRIBUNAL;
            case "SUPREME":
                return courtType.SUPREME;
            case "NATIONAL_APPEAL_CHAMBER":
                return courtType.NATIONAL_APPEAL_CHAMBER;
            case "COMMON":
                return courtType.COMMON;
            case "ADMINISTRATIVE":
                return courtType.ADMINISTRATIVE;
            default:throw new IllegalArgumentException(element+" is not a legal argument");
        }
    }
    public judgeRole roleConvert(String element)
    {
        switch (element)
        {
            case "PRESIDING_JUDGE": return judgeRole.PRESIDING_JUDGE;
            case "REPORTING_JUDGE": return judgeRole.REPORTING_JUDGE;
            case "REASONS_FOR_JUDGMENT_AUTHOR": return judgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
            default: throw new IllegalArgumentException(element+" is not a legal argument");
        }
    }
}
