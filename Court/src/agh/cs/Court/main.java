package agh.cs.Court;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

public class main
{
    public static void main(String[] args)throws ParseException,IllegalArgumentException
    {
        try
        {
            Path dir=Paths.get("C:/Users/barte/OneDrive/Pulpit/json");
            parser parser=new parser(dir);
            LinkedHashMap<String, verdict> verdicts=parser.getVerdicts();
            orderII o2=new orderII(verdicts);
            o2.execute("II AKa 105/11");
            orderIII o3=new orderIII(verdicts,"II AKa 105/11");
        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }

    }
}
