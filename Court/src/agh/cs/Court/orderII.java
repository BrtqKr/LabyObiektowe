package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.jar.JarEntry;

public class orderII extends fromString
{
    public orderII(LinkedHashMap<String,Verdict> verdicts, String id)
    {
        Verdict tmp=verdicts.get(id);
        tmp.getMetryka().printMetryka();
    }

}
