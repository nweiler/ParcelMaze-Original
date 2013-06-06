package com.nweiler.ParcelMaze;


import java.util.Scanner;
import java.io.InputStream;

/**
 * Utilities for accessing resources stored with the .class file in
 * the file system or in a jar file.
 */
public class ResourceUtil
{        
    /**
     * Return an input stream coming from a file located with this class
     * in the filesystem or in a jar file. 
     * @param fileName
     *   The name of a file located with this ResourceUtil class.
     * @return  
     *   A InputStream reading from the specified file, 
     *   or null if there is an error.
     */    
    public static InputStream openBundledFile(String fileName)
    {
   	    return ResourceUtil.class.getClassLoader().getResourceAsStream(fileName);
   	}
    
    /**
     * Create and return a Scanner coming from a text file located with this 
     * class. 
     * @param fileName
     *   The name of a text file accompanying this class.
     * @return  
     *   A Scanner reading from the specified file, 
     *   or null if there is an error.
     */    
    public static Scanner openFileScanner(String fileName)
    {  
        InputStream is = openBundledFile(fileName);
   	    if (is == null)
   	        return null;
        return new Scanner(is);
    }
}
