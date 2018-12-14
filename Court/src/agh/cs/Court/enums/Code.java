package agh.cs.Court.enums;

public enum Code {
    COMMON_COURT,SUPREME_COURT,CONSTITUTIONAL_TRIBUNAL,NATIONAL_APPEAL_CHAMBER;
    public Code toCode(String codeString)
    {
        switch (codeString) {
            case "COMMON_COURT":
                return Code.COMMON_COURT;
            case "SUPREME_COURT":
                return Code.SUPREME_COURT;
            case "CONSTITUTIONAL_TRIBUNAL":
                return Code.CONSTITUTIONAL_TRIBUNAL;
            case "NATIONAL_APPEAL_CHAMBER":
                return Code.NATIONAL_APPEAL_CHAMBER;
            default:throw new IllegalArgumentException(codeString+" is not a legal code");
        }
    }
}
