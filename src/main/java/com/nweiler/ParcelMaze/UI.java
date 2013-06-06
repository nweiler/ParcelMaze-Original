package com.nweiler.ParcelMaze;


import java.util.Scanner;

/**
 * Aid user keyboard input with prompts and error catching.
 * In class derived example and future utility class.
 *  
 * @version 2006.10.04
 */
public class UI 
{
    /* Static variable is available to all methods, static or instance. */
    private static Scanner in = new Scanner(System.in);
    
    /** Return the Scanner reading the keyboard.
     *  There should be ONLY ONE in a program.
     */ 
    public static Scanner getKeyboardScanner() 
    {
        return in;
    }

   
    /** Prompt the user for a line and return the line entered. 
     *  @param prompt
     *      The prompt for the user to enter the input line.
     *  @return
     *      The line entered by the user.
     */
    public static String readLine(String prompt) {
        System.out.print(prompt);
        return in.nextLine();
    }
    

    /** Prompt for a character.
     *  @param prompt
     *      The prompt for the user to enter a character
     *  @return
     *     The first character of the line entered or a blank if the line
     *     is empty.
     */
    public static char readChar(String prompt)
    {
        String line = readLine(prompt);
        if (line.length() == 0)
            return ' ';
        return line.charAt(0);
    }

    /** Print a question and return a response. 
     *  Repeat until a valid answer is given.
     * @param question 
     *    The yes/no question for the user to answer.
     * @return 
     *    True if the answer is yes, or False if it is no.
     */
    public static boolean agree(String question)
    {
        String yesStr = "yYtT", noStr = "nNfF", legalStr = yesStr + noStr;
        
        char ans = readChar(question);
        while (legalStr.indexOf(ans) == -1) 
        {
            System.out.println("Respond 'y or 'n':");
            ans = readChar(question);
            
        }
        return yesStr.indexOf(ans) >= 0;
    }
    
    /** Prompt and read an int.
     * Repeat until there is a legal value to return.
     *  @param prompt
     *      The prompt for the user to enter a value.
     *  @return
     *      The value entered by the user.
     */  
    public static int readInt(String prompt)
    {
        System.out.print(prompt);
        while (! in.hasNextInt()) 
        {
            in.next();  // dump the bad token
            in.nextLine(); // dump through the newline
            System.out.println("!! Bad int format!!");
            System.out.print(prompt);
        }
        int val = in.nextInt();
        in.nextLine(); // clear out through the newline!
        return val;
    }
    
    /** Prompt and read a double.
     * Repeat until there is a legal value to return.
     *  @param prompt
     *      The prompt for the user to enter a value.
     *  @return
     *      The value entered by the user.
     */  
    public static double readDouble(String prompt)
    {
        System.out.print(prompt);
        while (! in.hasNextDouble()) 
        {
            in.next();  // dump the bad token
            in.nextLine(); // dump through the newline
            System.out.println("!! Bad int format!!");
            System.out.print(prompt);
        }
        double val = in.nextDouble();
        in.nextLine(); // clear out through the newline!
        return val;
    }
}
