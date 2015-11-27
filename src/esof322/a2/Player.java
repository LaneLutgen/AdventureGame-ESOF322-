package esof322.a2;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
				    
**/
/*
 * @author Lane Lutgen
 */
import java.io.Serializable;

public class Player implements Serializable {

	private String text = " \n";

	private final int MAXITEMS = 3;

	private Room myLoc;

	private Item[] myThings = new Item[MAXITEMS];

	private int itemCount = 0;

	public void setRoom(Room r) {
		myLoc = r;
	}

	public String look() {
		return myLoc.getDesc();
	}

	public void go(int direction) {
		myLoc.exit(direction, this);
	}

	public void pickUp(Item i) {
		if (itemCount < MAXITEMS) {
			myThings[itemCount] = i;
			itemCount++;
			myLoc.removeItem(i);
		}
	}

	public boolean haveItem(Item itemToFind) {
		for (int n = 0; n < itemCount; n++)
			if (myThings[n] == itemToFind)
				return true;
		return false;
	}

	public Item[] getItems() {
		return myThings;
	}

	public void drop(int itemNum) {
		if (itemNum > 0 & itemNum <= itemCount) {
			switch (itemNum) {
			case 1: {
				myLoc.addItem(myThings[0]);
				myThings[0] = myThings[1];
				myThings[1] = myThings[2];
				itemCount--;
				break;
			}
			case 2: {
				myLoc.addItem(myThings[1]);
				myThings[1] = myThings[2];
				itemCount--;
				break;
			}
			case 3: {
				myLoc.addItem(myThings[2]);
				itemCount--;
				break;
			}
			}
		}
	}

	public void setLoc(Room r) {
		myLoc = r;
	}

	public Room getLoc() {
		return myLoc;
	}

	public String showMyThings() {
		String outString = "";
		for (int n = 0; n < itemCount; n++)
			outString = outString + Integer.toString(n + 1) + ": " + myThings[n].getDesc() + " ";
		return outString;
	}

	// Sets the players Log txt for the Log view
	public void setLogTxt(String txt) {
		text = txt;
	}

	// returns the players log txt for the log view
	public String getLogTxt() {
		return text;
	}

	public boolean handsFull() {
		return itemCount == MAXITEMS;
	}

	public boolean handsEmpty() {
		return itemCount == 0;
	}

	public int numItemsCarried() {
		return itemCount;
	}

}
