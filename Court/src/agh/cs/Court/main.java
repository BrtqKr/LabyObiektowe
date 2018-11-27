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
    public static void main(String[] args)
    {
        try
        {
            Path dir=Paths.get(URI.create("C:/Users/barte/OneDrive/Pulpit/json"));
            Parser parse=new Parser(dir);
        }
        catch (IOException| ParseException ex)
        {
            System.out.println(ex);
        }

    }
}
//git test comment