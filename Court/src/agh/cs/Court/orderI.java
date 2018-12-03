package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.jar.JarEntry;

public class orderI extends fromString
{
    public void orderI(LinkedList<LinkedHashMap<String,Object>> verdicts, String id,LinkedHashMap<String,LinkedList<Judge>>judgeMap)
    {
        for(int i=0;i<verdicts.size();i++)
        {
            LinkedHashMap<String,Object> verdict=verdicts.get(i);
            if(verdict.get("id").equals(id))
            {
                String dateString=verdict.get("judgmentDate").toString();
                String typeString=verdict.get("courtType").toString();
                courtType type=toType(typeString);
                Metryka result=new Metryka(id,dateString,type,judgeMap.get(id));   //zakładam że po przeparsowaniu mam wszystkich sędziów w sprawie o danym id w judgemap
                result.printMetryka();
            }
        }

    }

}
