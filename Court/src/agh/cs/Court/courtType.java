package agh.cs.Court;

public enum courtType
{
    CONSTITUTIONAL_TRIBUNAL,SUPREME,NATIONAL_APPEAL_CHAMBER,COMMON,ADMINISTRATIVE;


    public String toString(courtType type)
    {
        switch (type)
        {
            case COMMON:return "COMMON";
            case SUPREME:return "SUPREME";
            case ADMINISTRATIVE:return "ADMINISTRATIVE";
            case CONSTITUTIONAL_TRIBUNAL:return "CONSTITUTIONAL_TRIBUNAL";
            case NATIONAL_APPEAL_CHAMBER:return "NATIONAL_APPEAL_CHAMBER";
            default:throw new IllegalArgumentException("Illegal court type");
        }
    }
}
