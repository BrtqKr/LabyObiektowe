package agh.cs.Court;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class main
{
    public static void main(String[] args)throws ParseException,IllegalArgumentException
    {
        try
        {
            Path dir=Paths.get("C:/Users/barte/OneDrive/Pulpit/json");
            Parser parser=new Parser(dir);
            LinkedHashMap<String,Verdict> verdicts=parser.getVerdicts();
            orderII o=new orderII(verdicts);
            o.execute("II AKa 105/11");
        }
        catch (IOException | IllegalArgumentException ex)
        {
            System.out.println(ex);
        }

    }
}
