package com.wizard_quest;

import java.util.HashMap;

public class CommandWords
{

    private HashMap<String, CommandWord> validCommands;

    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        validCommands.put("идти", CommandWord.ИДТИ);
        validCommands.put("помощь", CommandWord.ПОМОЩЬ);
        validCommands.put("выход", CommandWord.ВЫХОД);
    }

    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    

    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
