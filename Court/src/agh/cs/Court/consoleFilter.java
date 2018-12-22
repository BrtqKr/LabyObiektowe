package agh.cs.Court;

import java.util.ArrayList;
import java.util.List;

public class consoleFilter {
    private String commandLine;
    private String order;
    private List<String> args=new ArrayList<>();
    private String[] argsTab;

    public consoleFilter(String commandLine) {
        this.commandLine = commandLine;
        filterOrder();
    }

    private void filterOrder() {
        boolean flag = false;

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.commandLine.length(); i++) {
            if(this.commandLine.charAt(i)==' '&&flag)
            {
                this.order=s.toString();
                if(!checkArgNo())filterArgs(i+1);
                break;
            }
            else if((i==commandLine.length()-1)&&flag)
            {
                s.append(commandLine.charAt(i));
                this.order=s.toString();
                break;
            }
            else if(this.commandLine.charAt(i)!=' ' && !flag )
            {
                flag=true;
                s.append(commandLine.charAt(i));
            }
            else if(this.commandLine.charAt(i)!=' '&& flag)
            {
                s.append(commandLine.charAt(i));
            }

        }

    }

    private void filterArgs(int index)    //argumenty muszą być podawane w cudzysłowie, nullpointer ogarnięty ale nie wykrywa argumentów
    {


        boolean quoteFlag = false;
        StringBuilder s = new StringBuilder();
        List<String>tmp=new ArrayList<>();

        for (int i = index; i < this.commandLine.length(); i++) {
            if (commandLine.charAt(i) == '"' && !quoteFlag) {
                quoteFlag = true;

            } else if (quoteFlag && commandLine.charAt(i) != '"' ) {
                s.append(commandLine.charAt(i));
            } else if (commandLine.charAt(i) == '"' && quoteFlag  ) {
                tmp.add(s.toString());
                s = new StringBuilder();
                quoteFlag=false;

            }
        }
        this.args=tmp;
    }


    public boolean checkArgNo()
    {
            switch (this.order)
            {
                case "rubrum":
                    if(this.args.size()>=1)
                        return true;
                    else return false;
                case "content":
                    if (this.args.size() == 1)
                        return true;
                    else return false;
                case "judge":
                    if (this.args.size() == 1)
                        return true;
                    else return false;
                case "jury":
                    return true;
                case "regulations":
                    return true;
                case "months":
                    return true;
                case "judges":
                    return true;
                case "help":
                    return true;
                    default: return false;
            }
    }

    public String getOrder(){return this.order;}

    public List<String> getArgs() {
        return this.args;
    }
}
