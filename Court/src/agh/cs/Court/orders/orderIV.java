package agh.cs.Court.orders;

import agh.cs.Court.structures.judge;

import java.util.LinkedHashMap;

public class orderIV//liczba orzeczeń wybranego sędziego
{
    private judge judgeResult;
    public orderIV(LinkedHashMap<String, judge > judges, String name)
    {
            judge tmp=judges.get(name);
            this.judgeResult=tmp;
    }
    public judge getJudgeResult(){return this.judgeResult;}
}
