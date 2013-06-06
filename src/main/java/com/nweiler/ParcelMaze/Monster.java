package com.nweiler.ParcelMaze;


import java.util.Random;

/**
 * Class for the monster object that the user will fight.
 * Monsters always start with 100 health.
 * The attack strength is a random integer between 0 and 50
 * that changes with each call to checkAttack().
 */

public class Monster
{
	private int health;
	private static Random rand = new Random();

	/**
	 * Constructer for the monster class.
	 * Sets monsters health to 100.
	 */
	public Monster()
	{
    	health = 100;
    	//attack = rand.nextInt(10);
	}

	/**
	 * Returns the monster's current health.
	 * @return Monster's health
	 */
	public int checkHealth()
	{
    	return health;
	}
    
	/**
	 * Checks the strength of the monster's attack for the fight.
	 * @return Monster attack strength
	 */
	public int checkAttack()
	{
    	return rand.nextInt(10);
	}
}