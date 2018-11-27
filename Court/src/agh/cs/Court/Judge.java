package agh.cs.Court;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.lang.*;
public class Judge
{
    private String name;
    private LinkedHashMap<String, LinkedList<judgeRole>> roles;                 //<ID,Role w sprawie o ID>

    public Judge(String name)
    {
        this.name=name;
    }
    public void addRoles(String id,LinkedList<judgeRole>newRoleList)
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
}
