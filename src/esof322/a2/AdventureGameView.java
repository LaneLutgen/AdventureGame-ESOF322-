package esof322.a2;

//This class impliments the GUI. There is no game functionality within other than a visual representation of what is happening for the user. We added 
//some buttons and labels for the user's benefit
import javax.swing.*;

import BreezySwing.*;

public class AdventureGameView extends GBFrame {

	/**
	 * 
	 */
    
	private static final long serialVersionUID = 1L;

	// Window objects --------------------------------------
	private JLabel welcomeLabel = addLabel(
			"Welcome to the Adventure Game " + "(inspired by an old game called the Colossal Cave Adventure)."
					+ " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
			1, 1, 5, 1);

	private JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
	private JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);

	private JLabel carryingLable = addLabel("You are carying: ", 6, 1, 1, 1);
	private JTextArea carryingArea = addTextArea("Nothing", 7, 1, 4, 3);

	private JLabel itemSelectionLabel = addLabel("Log:", 10, 1, 4, 1);
	private JTextArea itemSelectionLog = addTextArea("", 11, 1, 4, 1);

	private JLabel choiceLabel = addLabel("Choose a direction, pick-up, or drop an item", 12, 1, 5, 1);

	private JButton grabButton = addButton("Grab an item", 12, 5, 1, 1);
	private JButton dropButton = addButton("Drop an item", 13, 5, 1, 1);
	
	private JLabel itemSelection = addLabel("Item Selection", 3, 5, 1, 1);

	private JButton oneButton = addButton("1", 4, 5, 1, 1);
	private JButton twoButton = addButton("2", 5, 5, 1, 1);
	/*private JButton threeButton = addButton("3", 6, 5, 1, 1);
	private JButton fourButton = addButton("4", 7, 5, 1, 1);*/

	private JButton northButton = addButton("North", 12, 2, 1, 1);
	private JButton southButton = addButton("South", 14, 2, 1, 1);
	private JButton eastButton = addButton("East", 13, 3, 1, 1);
	private JButton westButton = addButton("West", 13, 1, 1, 1);
	private JButton upButton = addButton("Up", 12, 3, 1, 1);
	private JButton downButton = addButton("Down", 14, 3, 1, 1);

	private AdventureGameModelFacade model;
	
	private boolean dropButtonPressed = false;

	// Constructor-----------------------------------------------

	public AdventureGameView() {
		setTitle("Adventure Game");
		model = new AdventureGameModelFacade();

		viewArea.setEditable(false);
		carryingArea.setEditable(false);
		itemSelectionLog.setEditable(false);
		displayCurrentInfo();
	}

	// buttonClicked method--------------------------------------

	public void buttonClicked(JButton buttonObj) {
		if (buttonObj == upButton)
			model.goUp();

		else if (buttonObj == downButton){
			model.goDown();
                }

		else if (buttonObj == northButton)
			model.goNorth();

		else if (buttonObj == southButton)
			model.goSouth();

		else if (buttonObj == eastButton)
			model.goEast();

		else if (buttonObj == westButton)
			model.goWest();

		else if (buttonObj == grabButton) {
			grab();
		}

		else if (buttonObj == dropButton) {
			drop();
		}

		else if (buttonObj == oneButton) {
			if (dropButtonPressed) {
				model.dropItem(1);
				dropButtonPressed = false;
			} else {
				chooseRoomItem(1);
			}
		}

		else if (buttonObj == twoButton) {
			if (dropButtonPressed) {
				model.dropItem(2);
				dropButtonPressed = false;
			} else {
				chooseRoomItem(2);
			}
		}

		/*else if (buttonObj == threeButton) {
			if (dropButtonPressed) {
				model.dropItem(3);
				dropButtonPressed = false;
			} else {
				chooseRoomItem(3);
			}
		}

		else if (buttonObj == fourButton) {
			if (dropButtonPressed) {
				model.dropItem(3);
				dropButtonPressed = false;
			} else {
				chooseRoomItem(4);
			}
		}
                */
		displayCurrentInfo();
                model.setLog("");//refreshes the log after each time a button is clicked
	}

	// Private methods-------------------------------------------

	private void displayCurrentInfo() { //Displays all the information through the GUI to the user
          
		viewArea.setText(model.getView());
		carryingArea.setText(model.getItems());
                itemSelectionLog.setText(model.getLog());
	}

	// Left as an exercise.
	private void grab() {
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
                //checks to see if there is a item in the room to pick up and asks which one to pick up
		if (model.grabItem() != null) {
			//itemSelectionLog.setText(text + "\n");
			model.itemPresentInRoom = false;
		} else {
			model.itemPresentInRoom = true;
			model.setLog("Press the number of the item to grab: \n");
		}
	}
	
	private void chooseRoomItem(int item){
            
            //picks the room item based off the user's choice
		if(model.itemPresentInRoom){
			boolean itemFound = model.choosePickupItem(item);
			if(itemFound)
			{
				model.itemPresentInRoom = false;
			}
		}
	}

	// Left as an exercise.
	private void drop() {
            //asks which item the user would like to drop, allows them to drop nothing
		model.setLog("Press the number of the item to drop: \n");
  	    dropButtonPressed = true;
		// Set up a dialog to talk to the model and
		// determine what items to pick up.
	}
        
	public static void main(String[] args) {
		JFrame view = new AdventureGameView();
		view.setSize(950, 600); /* was 400, 250 */
		view.setVisible(true);
	}
}
