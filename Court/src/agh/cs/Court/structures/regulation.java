package agh.cs.Court.structures;

public class regulation
{
    private String journalTitle;
    private String journalNo;
    private String journalYear;
    private String journalEntry;
    private String text;

    public regulation(String title, String num, String year, String entry, String text)
    {
        this.journalTitle=title;
        this.journalNo=num;
        this.journalYear=year;
        this.journalEntry=entry;
        this.text=text;
    }
}
