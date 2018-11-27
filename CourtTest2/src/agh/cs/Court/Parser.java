package agh.cs.Court;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Parser extends typeParser
{
    public static void parse(LinkedHashMap<String,Key> keys, JSONObject jsonObject)
    {
        JSONArray items = (JSONArray) jsonObject.get("items");
        Iterator<JSONObject> itemsIterator = items.iterator();
        while (itemsIterator.hasNext())
        {
            JSONObject object=itemsIterator.next();
            for(int i=0;i<keys.size();i++)
            {
                switch ()
                {

                }
            }
            System.out.println(itemsIterator.toString());
        }
    }
}
