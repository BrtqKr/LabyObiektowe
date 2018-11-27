package agh.cs.Court;

public enum judgeRole
{
    PRESIDING_JUDGE,REPORTING_JUDGE,REASONS_FOR_JUDGMENT_AUTHOR;

    public String toString(judgeRole role)
    {
        switch (role)
        {
            case PRESIDING_JUDGE:return "PRESIDING_JUDGE";
            case REPORTING_JUDGE:return "REPORTING_JUDGE";
            case REASONS_FOR_JUDGMENT_AUTHOR:return "REASONS_FOR_JUDGMENT_AUTHOR";
            default:throw new IllegalArgumentException ("Illegal role");
        }
    }
}
