package com.nweiler.ParcelMaze;

import java.util.HashMap;
import java.util.Scanner;

/**
 * For our game "The Parcel Maze of Doom!" navigation through the maze is strictly text based.
 * Meaning, that when you want to go from one room to another it is based on the players
 * input of direction. Similar to Zuul when a player wants to move they have the option 
 * of direction. (North, South, East, West)
 * 
 * Unlike Zuul, however, our game will have unique images displayed for each room 
 * In addition to that the player could wander into a room that has a monster or a parcel. 
 * Encountering a monster initiates a fight sequence.
 * A parcel increases the player's health.
 */

public class Maze 
{
    private Room currentRoom;
    private HashMap<String, Room> rooms; //allows all rooms to be found by name
    public User myUser = new User();
    private static int monsterCount = 0;
    private Maze maze;
    
    /** 
     * Make a Game playable from the command line. 
     */
    public static void main(String[] args)
    {
        Maze maze = new Maze();
        maze.play();
    }
        
    /**
     * Create the game and initialise its internal map.
     */
    public Maze() 
    {
        rooms = Room.createRooms("roomData.txt", myUser, maze);
        currentRoom = rooms.get("outside");
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        StdDraw.clear();
        printWelcome();
        currentRoom.displayImage();
        currentRoom.displayDesc();
        Command command;
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
  
        boolean finished = false;       
        while (!finished) {
            
            while(monsterCount != 0 && !finished)
            {
                command = Parser.getCommand();
                finished = processCommand(command);
            }
            
            if(currentRoom.getImageFilePath().equals("castle.jpg"))
            {
                finished = true;
            }
            
            else
            {
                while(!finished){
                    System.out.println("You have defeated all of the monters but you must find your way back to the entrance!");
                    command = Parser.getCommand();
                    finished = processCommand(command);
                    if(currentRoom.getImageFilePath().equals("castle.jpg")){
                        finished = true;
                    }
                }
            }
        }
        
        if(monsterCount == 0){
            System.out.println("You have defeated all of the monters and escaped the castle. YOU WIN!!!!!!");   
            System.out.println("Thank you for playing.  Goodbye."); 
            StdDraw.text(.5, .5, "YOU WIN!!!!");
        }
        else{
            System.out.println("Play again soon!");
        }
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("You are standing outside a large castle.");
        System.out.println("You must defeat the monsters inside and return from whence you came...");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param Command 
     *      The command to be processed.
     * @return True if the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
             
        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("health")) {
            System.out.println("Your current health is " + myUser.checkHealth());

        }
        else if (commandWord.equals("monsters")){
            System.out.println("There are " + monsterCount + " monsters left in the castle!");
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are trapped within the castle.");
        System.out.println("You must find the parcels and defeat the monsters.");
        System.out.println();
        System.out.println("Your command words are:");
        Parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param Command, uses
     *      The command used to determine exit command
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            StdDraw.clear();
            currentRoom.displayImage();
            currentRoom.displayDesc();
            if(currentRoom.containsParcel()!=false){
                //myUser.addHealth();
                currentRoom.removeParcel();;
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @param Command, uses command to determine exit command.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Used at maze instantiation to count the total
     * number of monsters in the maze.
     */
    public static void increaseMonsterCount()
    {
        monsterCount ++;
    }
    
    /**
     * This is reduced each time a monster is defeated.
     * This gives the user a message when all the monsters
     * have been defeated.
     */
    public static void reduceMonsterCount()
    {
        monsterCount --;
    }
    
    /**
     * Prints a message showing the number of monsters remaining.
     */
    public static void printMonsterCount()
    {
        System.out.println("There are " + monsterCount + " monsters left in the castle!");
    }
}
