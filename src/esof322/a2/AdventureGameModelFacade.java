package esof322.a2;

//This class impliments all the functionality of the game. We added various methods to allow the user to drop items, pick them up, and change the Log view.
//There is also conditional checking to ensure the program runs correctly

import java.util.HashSet;

public class AdventureGameModelFacade {

	// some private fields to reference current location,
	// its description, what I'm carrying, etc.
	//
	// These methods and fields are left as exercises.
	private Player thePlayer = new Player();
	private ILevel theCave;
	private Room startRm;

	private boolean gameInProgress = false;
	private boolean dropButtonPressed = false;

	public boolean itemPresentInRoom = false;
	
	public void newGame(){
		
	}

	public void saveGame(){
		
	}
	
	public void loadGame(){
		
	}
	// Calls the methods to send the player in the proper direction
	public void goUp() {
		thePlayer.go(4);
	}

	public void goDown() {
		thePlayer.go(5);
	}

	public void goNorth() {
		thePlayer.go(0);
	}

	public void goSouth() {
		thePlayer.go(1);
	}

	public void goEast() {
		thePlayer.go(2);
	}

	public void goWest() {
		thePlayer.go(3);
	}

	public void onePressed() {
		if(!gameInProgress){
			theCave = LevelFactory.chooseLevel(0);
			beginGame();
			gameInProgress = true;
		}
		else if (dropButtonPressed) {
			dropItem(1);
			dropButtonPressed = false;
		} else {
			chooseRoomItem(1);
		}
	}

	public void twoPressed() {
		if(!gameInProgress){
			theCave = LevelFactory.chooseLevel(1);
			beginGame();
			gameInProgress = true;
		}
		if (dropButtonPressed) {
			dropItem(2);
			dropButtonPressed = false;
		} else {
			chooseRoomItem(2);
		}
	}

	private void chooseRoomItem(int item) {

		// picks the room item based off the user's choice
		if (itemPresentInRoom) {
			boolean itemFound = choosePickupItem(item);
			if (itemFound) {
				itemPresentInRoom = false;
			}
		}
	}

	// You need to finish these getView and getItems methods.
	public String getView() {
		// returns thePlayer's view of the room
		return (thePlayer.look());
	}

	public String getItems() {
		// returns the items the player has
		return (thePlayer.showMyThings());
	}

	public String grabItem() {
		// checks to see if the player's hands are full or if there is an item
		// in the room
		if (thePlayer.handsFull()) {
			// System.out.println("Your hands are full.");
			thePlayer.setLogTxt("Your hands are full");

			return "Your hands are full";
		} else if ((thePlayer.getLoc()).roomEmpty()) {
			// System.out.println("The room is empty.");
			thePlayer.setLogTxt("the room is empty");
			return "The room is empty";
		} else {
			return null;
		}

	}

	public void removeItemFromRoom(Item toRemove) {
		// takes the item in the room and places in the players inventory
		thePlayer.pickUp(toRemove);
		(thePlayer.getLoc()).removeItem(toRemove);
	}

	public void dropItem(int item) {
		// drops the item the player is holing based on the number they entered
		thePlayer.drop(item);
	}

	public Item[] getRoomContents() {

		// shows the items in the room
		return (thePlayer.getLoc()).getRoomContents();
	}

	// Left as an exercise.
	public void grab() {
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
		// checks to see if there is a item in the room to pick up and asks
		// which one to pick up
		if (grabItem() != null) {
			// itemSelectionLog.setText(text + "\n");
			itemPresentInRoom = false;
		} else {
			itemPresentInRoom = true;
			setLog("Press the number of the item to grab: \n");
		}
	}

	// Left as an exercise.
	public void drop() {
		// asks which item the user would like to drop, allows them to drop
		// nothing
		setLog("Press the number of the item to drop: \n");
		dropButtonPressed = true;
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
	}

	public String getLog() {

		// returns the Log view for the player
		return thePlayer.getLogTxt();
	}

	public void setLog(String string) {
		// Sets the Log view for the player
		thePlayer.setLogTxt(string);
	}

	public boolean choosePickupItem(int item) {

		// chooses the item to place into the array.
		Item[] contentsArray = (thePlayer.getLoc()).getRoomContents();
		do {
			if (item < 0 || item > contentsArray.length)
				return false;
		} while (item < 0 || item > contentsArray.length);

		removeItemFromRoom(contentsArray[item - 1]);
		return true;
	}

	private void beginGame(){
		startRm = theCave.createAdventure();
		thePlayer.setRoom(startRm);
	}
}
