package agh.cs.Court;

public enum JudgmentType
{
    DECISION,RESOLUTION,SENTENCE,REASONS;

    public JudgmentType toJudgmentType(String judgmentTypeString)
    {
        switch (judgmentTypeString) {
            case "DECISION":
                return JudgmentType.DECISION;
            case "RESOLUTION":
                return JudgmentType.RESOLUTION;
            case "SENTENCE":
                return JudgmentType.SENTENCE;
            case "REASONS":
                return JudgmentType.REASONS;
            default:
                throw new IllegalArgumentException(judgmentTypeString + "is not a legal judgment type");
        }
    }
}
