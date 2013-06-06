package com.nweiler.ParcelMaze;


//Code for rooms in the Maze. Borrows code from Zuul 
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/**
* Code for rooms in the Maze. The room has a description, an associated image, and a list of exits.
* It also may or may not contain a parcel and/or a monster.
*/

public class Room
{
 private String imageFilePath;
 private String description;
 private HashMap<String, Room> exits;
 private Monster myMonster;
 private boolean containsParcel;
 public boolean hasAMonster;
 private static Random rand = new Random();
 private static User myUser;
 private Maze maze;
 
 /**
  * Creates a room and initializes the description, image file and booleans for monster and parcel.
  * @param description A description of the room to be displayed along with the image.
  * @param imageFilePath The path and filename for the image.
  * @param myUser The instance of the user class created at the beginning of the maze.
  */
 public Room(String description, String imageFilePath, User myUser, Maze maze)
 {
     this.myUser = myUser;
     this.description = description;
     this.imageFilePath = imageFilePath;
     this.maze = maze;
     exits = new HashMap<String, Room>();

     int x = rand.nextInt(2); // randomly generate parcel (or not)
     if(x==0 || imageFilePath.equals("castle.jpg")){ //prevents parcel in first room
         containsParcel = false;
     }
     else{
         containsParcel = true;
     }
     x = rand.nextInt(2);
     
     if(x==0 || imageFilePath.equals("castle.jpg")){ //prevents monster in first room
         hasAMonster = false;
     }
     else if(x==1){
         hasAMonster = true;
         Maze.increaseMonsterCount();
     }
 }
 
 /**
  * Method to start the fight.
  * The actual monster object for the fight is created here.
  */
 public void startAFight(){
     Monster myMonster = new Monster();
     Fight myFight = new Fight();
     hasAMonster = myFight.fight(myUser, myMonster, maze);
     if(hasAMonster == false)
     {
         System.out.println(getExitString());
     }
     StdDraw.clear();
     displayImage();
     displayDesc();
 }
 
 /**
  * Method to create the rooms from the roomdata file and insert into a hashmap
  * @param fileName The file roomData.txt with the information for room setup
  * @param myUser The user playing the game
  * @param maze The instance of the maze being played
  */
 public static HashMap<String, Room> createRooms(String fileName, User myUser, Maze maze)
 {
     Scanner in = ResourceUtil.openFileScanner(fileName);
     if (in == null) {
         System.out.println("File not found: " + fileName);
         return null;
     }
     
     HashMap<String, Room> rooms = new HashMap<String, Room>();
     
     HashMap<String, String> exitStrings = new HashMap<String, String>();
     while (in.hasNext()) {
         String name = in.nextLine();
         String imageFilePath = in.nextLine();
         String exitPairs = in.nextLine();
         String description = FileUtil.readParagraph(in);
         rooms.put(name, new Room(description, imageFilePath, myUser, maze));
         exitStrings.put(name, exitPairs);
      
     }
     in.close();
     
     for(String name : rooms.keySet()) {
         Room room = rooms.get(name);
         Scanner lineIn = new Scanner(exitStrings.get(name));
         
         while (lineIn.hasNext()) {
             String direction = lineIn.next();
             String neighbor = lineIn.next();
             room.setExit(direction, rooms.get(neighbor));
         }
     }
     return rooms;
 }
 
 /**
  * Returns whether or not room has a monster.
  * @return True if room has a monster, false if it does not
  */
 public boolean hasAMonster(Room room){
     return hasAMonster;
 }
 
 /**
  * Returns whether or not room has a parcel.
  * @return True if room has a parcel, false if it does not
  */
 public boolean containsParcel(){
     return containsParcel;
 }
 
 /**
  * Removes the parcel from the room
  */
 public void removeParcel(){
     containsParcel = false;
 }

 /**
  * 
  */
 public void setExit(String direction, Room neighbor)
 {
     exits.put(direction, neighbor);
 }
 
 /**
  * Returns a string with a short room description
  */
 public String getShortDescription()
 {
     return description;
 }
 
 /**
  * Returns a string with a longer room description
  */
 public String getLongDescription()
 {
     return "You are" + description + ".\n" + getExitString();
 }
 
 /**
  * Returns a string with the exits for the room
  */
 private String getExitString()
 {
     String returnString = "Exits:";
     Set<String> keys = exits.keySet();
     for (String exit : keys) {
         returnString += " " + exit;
     }
     return returnString;
 }
 
 /**
  * 
  */
 public Room getExit(String direction)
 {
     return exits.get(direction);
 }

 /**
  * Displays the room image
  * If the room contains a monster, the monster image is also displayed.
  */
 public void displayImage()
 {
     StdDraw.picture(.5, .5, imageFilePath, 1.1, 1);
     if(hasAMonster!=false){
         StdDraw.picture(.5, .5, "resources/monster.png", 1.05, .9);
     }
 }
 
 /**
  * Prints a message displaying the name of the current room.
  * Also displays messages if the room has a parcel and/or a monster.
  */
 public void displayDesc()
 {
     StdDraw.text(.5, .2, "You are" + description);
     if(containsParcel){
         myUser.addHealth();
         StdDraw.text(.5, .1, "This room contains a parcel!");
         StdDraw.text(.5, .05,"You picked up the parcel and got +10 health!");
     }
     if(hasAMonster){
         StdDraw.text(.5, .15, "There is a monster! Ahh!");
         startAFight();
     }
 }
 
 /**
  * A method used to increase user health with parcels.
  */
 public void parcelHealth()
 {
     if(containsParcel){
         myUser.addHealth();
     }
 }
 
 /**
  * Returns a string with the Room's image file path.
  */
 public String getImageFilePath()
 {
     return imageFilePath;
 }
}