package esof322.a2;

//This class implements all the functionality of the game. We added various methods to allow the user to drop items, pick them up, and change the Log view.
//There is also conditional checking to ensure the program runs correctly

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;

public class AdventureGameModelFacade {

	// some private fields to reference current location,
	// its description, what I'm carrying, etc.
	//
	// These methods and fields are left as exercises.
	private Player thePlayer = new Player();
	private ILevel theCave;
	private Room startRm;
	private Game savedGame;
	private File saveFile;
	private Flashlight theFlashlight;

	private boolean gameInProgress = false;
	private boolean dropButtonPressed = false;
	private boolean newGamePressed = false;
	private boolean levelOne = false;

	public boolean itemPresentInRoom = false;

	public AdventureGameModelFacade() {
		printWelcomeText();
		saveFile = new File("saved_game.txt");
	}

	private void printWelcomeText() {
		setLog("Welcome to the adventure game! Click on New Game to begin!");
	}

	public void newGame() {
		thePlayer = new Player();
		setLog("To play Level 1, click on the 1 button! \n" + "To play Level 2, click on the 2 button!");
		newGamePressed = true;
		if (gameInProgress) {
			gameInProgress = false;
		}
	}

	public void saveGame() {
		if (gameInProgress) {
			if (savedGame == null) {
				setLog("Game saved successfully!");
			} else {
				setLog("Game saved successfully! The previously saved game has been deleted.");
			}
			savedGame = new Game(thePlayer.getLoc(), thePlayer, theCave, thePlayer.getItems());
			try {
				saveGameToFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			setLog("Please begin a new game before saving.");
		}
	}

	private void saveGameToFile() throws Exception {
		saveFile = new File("saved_game.txt");
		FileOutputStream fos = new FileOutputStream(saveFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(savedGame);
	}

	public void loadGame() throws Exception {
		FileInputStream fis = new FileInputStream(saveFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		savedGame = (Game) ois.readObject();
		if (savedGame != null) {
			gameInProgress = true;
			theCave = savedGame.getLevel();
			thePlayer = savedGame.getPlayer();
			thePlayer.setLoc(savedGame.getRoom());
			setLog("Previous game loaded succesfully!");
		} else {
			setLog("No saved game was found.");
		}
	}

	// Calls the methods to send the player in the proper direction
	public void goUp() {
		if (gameInProgress) {
			thePlayer.go(4);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void goDown() {
		if (gameInProgress) {
			thePlayer.go(5);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void goNorth() {
		if (gameInProgress) {
			thePlayer.go(0);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void goSouth() {
		if (gameInProgress) {
			thePlayer.go(1);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void goEast() {
		if (gameInProgress) {
			thePlayer.go(2);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void goWest() {
		if (gameInProgress) {
			thePlayer.go(3);
			decreaseFlashlightCharge();
		}else{
			gameOver();
		}
	}

	public void onePressed() {
		if (!newGamePressed) {
			printWelcomeText();
		} else if (!gameInProgress) {
			theCave = LevelFactory.chooseLevel(0);
			levelOne = false;
			beginGame(levelOne);
			gameInProgress = true;
		} else if (dropButtonPressed) {
			dropItem(1);
			dropButtonPressed = false;
		} else {
			chooseRoomItem(1);
		}
	}

	public void twoPressed() {
		if (!newGamePressed) {
			printWelcomeText();
		} else if (!gameInProgress) {
			theCave = LevelFactory.chooseLevel(1);
			levelOne = true;
			beginGame(levelOne);
			gameInProgress = true;
		} else if (dropButtonPressed) {
			dropItem(2);
			dropButtonPressed = false;
		} else {
			chooseRoomItem(2);
		}
	}

	public void threePressed() {
		if (!newGamePressed) {
			printWelcomeText();
		} else if (!gameInProgress) {
			setLog("Level 3 is not available at this time.");
		} else if (dropButtonPressed) {
			dropItem(3);
			dropButtonPressed = false;
		} else {
			chooseRoomItem(3);
		}
	}

	private void decreaseFlashlightCharge() {
		if (thePlayer.haveItem(theFlashlight)) {
			gameInProgress = theFlashlight.decreaseBatteryLife();
			displayBatteryLife();
			if(!gameInProgress){
				gameOver();
			}
		}
	}

	private void displayBatteryLife() {
		setLog("Flashlight Battery :" + theFlashlight.getBatteryLife() + "%");
	}
	
	private void gameOver(){
		setLog("GAME OVER, your flashlight has run out of batteries!");
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
		if (!gameInProgress) {
			return thePlayer.getLogTxt();
		} else {
			return (thePlayer.look());
		}
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
		if(toRemove.isBattery()){
			theFlashlight.addBattery();
			displayBatteryLife();
		}
		else{
			thePlayer.pickUp(toRemove);
		}
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
		if (!gameInProgress) {
			return "";
		} else {
			return thePlayer.getLogTxt();
		}
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

	private void beginGame(boolean levelOne) {
		startRm = theCave.createAdventure();
		thePlayer.setRoom(startRm);
		if (levelOne) {
			theFlashlight = (Flashlight) startRm.getFirstItem();
			thePlayer.pickUp(theFlashlight);// grab flashlight
			displayBatteryLife();
		}
	}
}
