package agh.cs.CourtProject;
import java.util.*;

public class Judge
{
    private Set<String>sentencesID=new HashSet<>();
    private String JudgeFullName;
    private HashMap<String,LinkedList<judgeRole>>roles;
    public Judge(String fullName)
    {
        this.JudgeFullName=fullName;
    }
    public String getName()
    {
        return this.JudgeFullName;
    }
    public void addCase(String sentenceID)
    {
        this.sentencesID.add(sentenceID);
    }
    public void addRole(String sentence,judgeRole role)
    {
        LinkedList<judgeRole> updateRoles=this.roles.get(sentence);
        updateRoles.add(role);
        this.roles.put(sentence,updateRoles);
    }
}
