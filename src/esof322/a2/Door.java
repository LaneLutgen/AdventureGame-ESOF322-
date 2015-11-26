package esof322.a2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Created a way to change the Player's Log based on whether they could enter the locked door or not.

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman
     To compile: javac AdventureGame.java
     To run:     java AdventureGame
     The main routine is AdventureGame.main
				    
**/
import java.io.Serializable;
// class Door

public class Door implements CaveSite, Serializable {
  /** In this implementation doors are always locked.
      A player must have the correct key to get through
      a door.  Doors automatically lock after a player
      passes through. */

  protected Key myKey;

  /** The door's location. */
  protected CaveSite outSite;
  protected CaveSite inSite;

  /** We can construct a door at the site. */
  public Door(CaveSite out, CaveSite in, Key k){
    outSite = out;
    inSite = in;
    myKey = k;
  }

 /** A player will need the correct key to enter. */
  
  // with the correct key, a player may enter the door. The player's setTxt is changed to notify them they have unlocked the door.
 public void enter(Player p){
 if (p.haveItem(myKey)) {
   p.setLogTxt("Your key works! The door creaks open, and slams behind you after you pass through.");
    if (p.getLoc() == outSite) inSite.enter(p);
    else if (p.getLoc() == inSite) outSite.enter(p);
 
 }
 else { 
     p.setLogTxt("You don't have the key for this door! Sorry.");
      }

 }

}
