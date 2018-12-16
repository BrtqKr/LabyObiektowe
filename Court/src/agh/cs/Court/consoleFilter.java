package agh.cs.Court;

import java.util.ArrayList;
import java.util.List;

public class consoleFilter
{
    private String commandLine;
    private String order;
    private List<String> args;

    public consoleFilter(String commandLine)
    {
        this.commandLine=commandLine;
        filterOrder();
    }
    private void filterOrder()
    {
        boolean flag=false;

        StringBuilder s=new StringBuilder();
        for(int i=0;i<this.commandLine.length();i++)
        {
            if(this.commandLine.charAt(i)!=' ')
            {
                flag=true;
                s.append(this.commandLine.charAt(i));
            }
            else if(this.commandLine.charAt(i)==' ' && flag)
            {
                this.order=s.toString();
                filterArgs(i);
                break;
            }
        }

    }
    private void filterArgs(int index)    //argumenty muszą być podawane w cudzysłowiu
    {
        List<String>args=new ArrayList<>();
        boolean quoteFlag=false;
        boolean wordFlag=false;
        StringBuilder s=new StringBuilder();
        while(index<commandLine.length()-1)
        {
           if(commandLine.charAt(index)=='"'&&!quoteFlag)
           {
               quoteFlag=true;
               wordFlag=true;
           }
           else if(wordFlag&&quoteFlag&&commandLine.charAt(index+1)!='"'&&commandLine.charAt(index)!='"')
           {
               s.append(commandLine.charAt(index));
           }
           else if(commandLine.charAt(index+1)=='"'&&quoteFlag&&wordFlag)
           {
               s.append(commandLine.charAt(index));
               args.add(s.toString());
               s=new StringBuilder();
               wordFlag=false;

           }
           else if(quoteFlag &&!wordFlag&&commandLine.charAt(index)=='"')
           {
               quoteFlag=false;
           }



            index++;
        }
        this.args=args;
    }
    /*private boolean checkArgNo()
    {
     switch (this.order)
     {
         case "help":
             if(this.args.size()==0)
                 return true;
         case "rubrum":
             if(this.args.size()==1)
                 return true;
         case "content":
             if(this.args.size()==1)
                 return true;
         case "judge":
             if(this.args.size()==1)
                 return true;

     }
    }*/
    public String getOrder(){return this.order;}

    public List<String> getArgs() {
        return args;
    }
}
