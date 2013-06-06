package com.nweiler.ParcelMaze;


import java.util.Random;
import static java.lang.Math.*;

/**
 * A fight object is created when the player encounters a monster.
 * The user is given a choice between punch, low damage/high probability of a hit
 * and kick, high damage/low probability of hit. The player and monster trade hits
 * until one of the two is defeated.
 */

public class Fight
{
    private int bonus;
    private static Random rand = new Random();
    private Maze maze;
  
    /**
     * Empty constructor for fight class.
     * We may add something here...
     */
    public Fight()
    {   
       
    }
   
    /**
     * Main class for fight. The player and monster trade hits until one of them dies.
     * @param player The player to be included in the fight
     * @param monster The monster to be included in the fight
     * @return Outcome of the fight. True if player dies, false if monster dies.
     */
    public boolean fight(User player, Monster cthulu, Maze maze)
    {
            int userAttack = player.checkAttack();
            int monsterHealth = 100;
            int monsterAttack = cthulu.checkAttack();
            int [] punch = {1, 1, 1, 1, 1, 4};
            int [] kick = {0, 0, 0, 4, 4, 4};
            int bonus = 0;
           
            int input = UI.readInt("There is a monster! Ahh!\nPress 1 to punch the monster. It doesn't hit hard, but it hits often\n"
                                    + "Press 2 to kick the monster. It might miss but it hits hard!\n");
            while(input != 1 && input != 2){
                input = UI.readInt("Nooo!!! Enter 1 or 2! ");
            }
            while(monsterHealth > 0  && player.checkHealth() > 0 && (input == 1 || input == 2)){
                if(input == 1){
                    bonus = rand.nextInt(6);
                    bonus = punch[bonus];                                              
                    if(bonus == 1){
                        monsterHealth -= player.checkAttack();
                        System.out.print("You hit the monster! " + "Monster has " + max(0, monsterHealth) + " health left!\n");
                        player.changeHealth(cthulu.checkAttack());
                        System.out.print("The monster responded with a hit of his own!\n");
                        System.out.print("You have " + max(0, player.checkHealth()) + " health left!\n");
                    }               
                    else{
                       monsterHealth -= userAttack*2;
                       System.out.print("Critical hit!\n" + "Monster has " + max(0, monsterHealth) + " health left!\n");
                       System.out.print("The monster couldn't hit you because he stumbled!\n");
                       System.out.print("You have " + max(0, player.checkHealth()) + " health left!\n");
                    }
                }
                if(input == 2){
                      bonus = rand.nextInt(6);
                      bonus = kick[bonus];
                      if(bonus == 0){
                            System.out.print("You missed! " + "Monster has " + monsterHealth + " health left!\n");
                            player.changeHealth(cthulu.checkAttack()*2);
                            System.out.print("The monster hit you harder because you missed!\n");
                            System.out.print("You have " + max(0, player.checkHealth()) + " health left!\n");
                      }   
                      else{
                          monsterHealth -= (player.checkAttack())*2;
                          System.out.print("Critical hit! " + "Monster has " + max(0,monsterHealth) + " health left!\n");
                          System.out.print("The monster couldn't hit you because he stumbled!\n");
                          System.out.print("You have " + max(0, player.checkHealth()) + " health left!\n");
                      }
                }
                if(monsterHealth > 0 && player.checkHealth() > 0){
                    input = UI.readInt("Press 1 to punch or 2 to kick the monster!\n");
                    while(input < 1 || input > 2){
                        input = UI.readInt("You'll die! Press 1 or 2 to attack the monster and not die!\n");
                    }
                }
            }
            if(player.checkHealth() <= 0){
                System.out.print("The monster dances on your corpse. You died.\n");
                player.die();
                return true;
            }
            else{
                System.out.print("Your foe is defeated! Continue your journey.\n");
                maze.reduceMonsterCount();
                Maze.printMonsterCount();
                return false;
            }
    }
}  
