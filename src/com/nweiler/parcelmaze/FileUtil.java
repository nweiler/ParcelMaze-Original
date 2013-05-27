package com.nweiler.parcelmaze;


import java.util.Scanner;

/**
 * File utilities for reading from a file with a Scanner.
 */
public class FileUtil
{    
    /**
     * Read a paragraph consisting of one or more nonempty lines terminated 
     * by an empty line, and return a single String, preserving the newlines.
     * The Scanner stops reading after the first empty line.
     * @param in
     *   The source of the paragraph text.
     * @return  The whole paragraph, with the newlines after each line, 
     *   ending with the newline after the last nonempty line. 
     */    
    public static String readParagraph(Scanner in)
    {  
        String para = "";
        String line = in.nextLine();
        while (line != null && line.trim().length() > 0) {
            para += line + "\n";
            line = in.nextLine();
        }
        return para;
    }
  
}
