package agh.cs.Court;

import java.util.ArrayList;
import java.util.List;

public class consoleFilter {
    private String commandLine;
    private String order;
    private List<String> args;

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
                if(!checkArgNo())filterArgs(i);
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

    private void filterArgs(int index)    //argumenty muszą być podawane w cudzysłowie ptoblrm z nullpointerem
    {
        boolean quoteFlag = false;
        boolean wordFlag = false;
        StringBuilder s = new StringBuilder();
        this.args = new ArrayList<>();
        for (int i = index; i < commandLine.length(); i++) {
            if (commandLine.charAt(index) == '"' && !quoteFlag) {
                quoteFlag = true;
                wordFlag = true;
            } else if (wordFlag && quoteFlag && commandLine.charAt(index + 1) != '"' && commandLine.charAt(index) != '"') {
                s.append(commandLine.charAt(index));
            } else if (commandLine.charAt(index + 1) == '"' && quoteFlag && wordFlag) {
                s.append(commandLine.charAt(index));
                this.args.add(s.toString());
                s = new StringBuilder();
                wordFlag = false;

            } else if (quoteFlag && !wordFlag && commandLine.charAt(index) == '"') {
                quoteFlag = false;
            }
            index++;
        }


    }

    public boolean checkArgNo()
    {
            switch (this.order)
            {
                case "rubrum":
                        return true;
                case "content":
                    if (this.args.size() == 1)
                        return true;
                case "judge":
                    if (this.args.size() == 1)
                        return true;
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
