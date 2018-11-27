package agh.cs.Court;

import java.util.LinkedList;
import java.util.List;

public class Verdict
{
    Metryka metryka; //sygnatura, data, rodzaj sądu, skład sędziów
    LinkedList<referencedRegulation>regulations;
    void updateID(String id)
    {
        this.metryka.setID(id);
    }
    void updateDate(String date)
    {
        this.metryka.setDate(date);
    }
    void updateCourtType(courtType type)
    {
        this.metryka.setType(type);
    }
}
