package com.nweiler.parcelmaze;


import java.util.Scanner;

public class Parser 
{

    /**
     * Parse the next user command.
     * @return The user's command.
     */
    public static Command getCommand() 
    {
        String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        String line = UI.readLine("> ");  // AH change
        Scanner scan = new Scanner(line); // String source for the Scanner
        
        if(scan.hasNext())
            word1 = scan.next();      // get first word
        else
            word1 = null;
        if(scan.hasNext())
            word2 = scan.next();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

       // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(CommandWords.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2); 
        }
    }

    /**
     * Print out a list of valid command words.
     */
    public static void showCommands()
    {
        System.out.println(CommandWords.allCommands());
    }
}
