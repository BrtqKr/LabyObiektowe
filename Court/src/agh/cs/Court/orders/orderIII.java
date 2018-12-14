package agh.cs.Court;

import java.util.LinkedHashMap;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class orderIII
{
    public orderIII(LinkedHashMap<String, verdict> verdicts, String signature)  //wyświetla uzasadnienie
    {
        verdict tmp=verdicts.get(signature);
        String html = tmp.getTextContent();
        parseTextContent(html);
    }
    private void parseTextContent(String file)
    {
        Document doc=Jsoup.parse(file);
        Elements elements=doc.body().select("*");
        for (Element iter: elements)
        {
            System.out.println(iter.ownText());
        }
    }
}
