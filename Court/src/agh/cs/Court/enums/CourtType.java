package agh.cs.Court.enums;

public enum CourtType
{
    CONSTITUTIONAL_TRIBUNAL,SUPREME,NATIONAL_APPEAL_CHAMBER,COMMON,ADMINISTRATIVE;


    public String toString(CourtType type)
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
    public CourtType toType(String typeString)
    {
        switch (typeString) {
            case "CONSTITUTIONAL_TRIBUNAL":
                return CourtType.CONSTITUTIONAL_TRIBUNAL;
            case "SUPREME":
                return CourtType.SUPREME;
            case "NATIONAL_APPEAL_CHAMBER":
                return CourtType.NATIONAL_APPEAL_CHAMBER;
            case "COMMON":
                return CourtType.COMMON;
            case "ADMINISTRATIVE":
                return CourtType.ADMINISTRATIVE;
            default:
                throw new IllegalArgumentException(typeString + " is not a legal court type");
        }
    }
}
