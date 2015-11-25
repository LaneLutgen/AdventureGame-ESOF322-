package esof322.a2;
//group members: Austin Baune, Lane Lutgen, John Waters, William Paddock 
//Added a way to update the players LogTxt for the Log view

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
				    
**/

// class Wall

import java.io.Serializable;

public class Wall implements CaveSite, Serializable {

    //updates the LogTxt to Ouch that hurts if they run into a wall
 public void enter(Player p)
	     {
                 p.setLogTxt("Ouch! That hurts.");
                 
   //System.out.println("Ouch! That hurts.");
 }

}


