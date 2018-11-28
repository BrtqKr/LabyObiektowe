package agh.cs.Court;

public class Regulation
{
    private String journalTitle;
    private String journalNo;
    private String journalYear;
    private String journalEntry;
    private String text;

    public Regulation(String title,String num,String year,String entry,String text)
    {
        this.journalTitle=title;
        this.journalNo=num;
        this.journalYear=year;
        this.journalEntry=entry;
        this.text=text;
    }
}
