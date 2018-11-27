package agh.cs.Court;
import java.util.*;

public class Judge
{
    private Set<String>sentencesID=new HashSet<>();
    private String judgeFullName;
    private String function;
    private HashMap<String,LinkedList<judgeRole>>roles;
    public void addFullName(String fullName)
    {
        this.judgeFullName =fullName;
    }
    public String getName()
    {
        return this.judgeFullName;
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
    public void addFunction(String function)
    {
        this.function=function;
    }
}
