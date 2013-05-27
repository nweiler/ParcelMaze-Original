package com.nweiler.parcelmaze;


import java.util.Random;

/**
 * The class representing the player. The user's attack strength is constant throughout the game.
 * Health starts at 100 and may decrease as the player encounters monsters.
 */

public class User
{
    private static int userHealth;
    private static int bonus;
    private static Random rand = new Random();
    private static String name;
    
    /**
     * Create an instance of the User object
     */
    public User()
    {
        userHealth = 100;
        //userAttack = rand.nextInt(25 + 1);
    }
    
    /**
     * Overloaded constructor allows for possible future addition on name attribute for the User.
     * @param name The player's name entered at the start of the game.
     */
    public User(String name)
    {
        userHealth = 100;
        //userAttack = rand.nextInt(25 + 1);
        this.name = name;
    }
    
   /**
    * Returns user name
    * @return The user name
    */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns user health
     * @return User's health
     */
    public int checkHealth()
    {
        return userHealth;
    }
    
    /**
     * Checks the user attack strength
     * @return User's attack strength
     */
    public int checkAttack()
    {
        return rand.nextInt(25 + 1);
    }
    
    /**
     * Used for testing while creating player death sequence.
     */
    public void die()
    {
        Maze maze = new Maze();
        maze.play();
    }
    
    /**
     * The method to be used by the parcels to add user health.
     * This will happen when the user encounters parcels throughout the castle and picks them up.
     */
    public void addHealth()
    {
        userHealth += 10;
    }
    
    /**
     * Alternative to addHealth method. 
     * @param Damage The adjustment made to the user health
     */
    public void changeHealth(int damage)
    {
        userHealth -= damage;
    }
}
