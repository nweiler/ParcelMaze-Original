package com.nweiler.parcelmaze;


/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * Bad example -- there is no need for this class to have an instance.
 * It has been easily redone with all static methods!
 */

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
