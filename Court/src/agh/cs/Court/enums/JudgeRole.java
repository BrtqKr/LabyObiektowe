package agh.cs.Court.enums;

public enum JudgeRole
{
    PRESIDING_JUDGE,REPORTING_JUDGE,REASONS_FOR_JUDGMENT_AUTHOR;

    public String toString(JudgeRole role)
    {
        switch (role)
        {
            case PRESIDING_JUDGE:return "przewodniczący";
            case REPORTING_JUDGE:return "sprawozdawca";
            case REASONS_FOR_JUDGMENT_AUTHOR:return "autor uzasadnienia";
            default:throw new IllegalArgumentException ("Illegal role");
        }
    }
    public JudgeRole toRole(String roleString)
    {
        switch (roleString) {
            case "PRESIDING_JUDGE":
                return JudgeRole.PRESIDING_JUDGE;
            case "REPORTING_JUDGE":
                return JudgeRole.REPORTING_JUDGE;
            case "REASONS_FOR_JUDGMENT_AUTHOR":
                return JudgeRole.REASONS_FOR_JUDGMENT_AUTHOR;
            default:
                return null;
        }
    }
}
