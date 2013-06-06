package com.nweiler.ParcelMaze;


public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {"go", "quit", "help", "health","monsters"};

    /**
     * Check whether a given String is a valid command word. 
     * Return true if it is, false if it isn't.
     */
    public static boolean isCommand(String aString)
    {
        for(String cmd : validCommands) 
            if(cmd.equals(aString))
                return true;
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public static String allCommands() 
    {
        String all = "";
        for(String cmd : validCommands) 
            all += cmd + "  ";
        return all;
    }
}
