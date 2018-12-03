package agh.cs.Court;

public abstract class fromString
{
    public courtType toType(String typeString)
    {
        switch (typeString) {
            case "CONSTITUTIONAL_TRIBUNAL":
                return courtType.CONSTITUTIONAL_TRIBUNAL;
            case "SUPREME":
                return courtType.SUPREME;
            case "NATIONAL_APPEAL_CHAMBER":
                return courtType.NATIONAL_APPEAL_CHAMBER;
            case "COMMON":
                return courtType.COMMON;
            case "ADMINISTRATIVE":
                return courtType.ADMINISTRATIVE;
            default:
                throw new IllegalArgumentException(typeString + " is not a legal court type");
        }
    }
    public judgeRole toRole(String roleString)
    {
        switch (roleString) {
            case "PRESIDING_JUDGE":
                return judgeRole.PRESIDING_JUDGE;
            case "REPORTING_JUDGE":
                return judgeRole.REPORTING_JUDGE;
            case "REASONS_FOR_JUDGMENT_AUTHOR":
                return judgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
            default:
                return null;
        }
    }
    public judgmentType toJudgmentType(String judgmentTypeString)
    {
        switch (judgmentTypeString) {
            case "DECISION":
                return judgmentType.DECISION;
            case "RESOLUTION":
                return judgmentType.RESOLUTION;
            case "SENTENCE":
                return judgmentType.SENTENCE;
            case "REASONS":
                return judgmentType.REASONS;
            default:
                throw new IllegalArgumentException(judgmentTypeString + " is not a legal judgment type");
        }
    }
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
