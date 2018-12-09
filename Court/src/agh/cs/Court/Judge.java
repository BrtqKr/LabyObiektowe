package agh.cs.Court;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.lang.*;
public class Judge
{
    private String name;
    private LinkedHashMap<String, LinkedList<judgeRole>> roles;                 //<ID,Role w sprawie o ID>
    private LinkedList<String> cases;
    public Judge(String name)
    {
        this.name=name;
        this.roles=new LinkedHashMap<>();
        this.cases=new LinkedList<>();
    }
    public void addRole(String id,LinkedList<judgeRole>newRoleList)
    {
        this.roles.put(id,newRoleList);
    }
    public String getName()
    {
        return this.name;
    }
    public String getRoles(String id)
    {
        LinkedList<judgeRole>currentRoles=this.roles.get(id);
        StringBuilder result=new StringBuilder();
        for(judgeRole iterRole:currentRoles)
        {
            result.append(iterRole.toString());
        }
        return result.toString();
    }
    public int getCasesNumber()
    {
        return this.cases.size();
    }
}
