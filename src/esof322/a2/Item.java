package esof322.a2;
/*
 * @author Lane Lutgen
 */
/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
				    
**/

import java.io.Serializable;
// class Item
// no changes were made
public class Item implements Serializable{

  private String description;

  public void setDesc(String d){
      description = d;
  }

  public String getDesc(){
	     return description;
  }

  public boolean isBattery(){
	  return false;
  }
}

