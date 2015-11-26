package esof322.a2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class LevelOne implements ILevel {

	private Room entrance;

	public Room createAdventure(){
   // Starting Room 
      Room  start = new Room();
      start.setDesc("room start");
   
   // Room 1:
      Room r1 = new Room();
      r1.setDesc("room 1");

   // Connect the outside to Room 1:
      start.setSide(3,r1);
      r1.setSide(2, start);
      entrance = start;

   // Room 2:
      Room r2 = new Room();
      r2.setDesc("room 2");
      
   // Connect Room 2 and Start
      start.setSide(0, r2);
      r2.setSide(1, start);

  // Room 3:
     Room r3 = new Room();
     r3.setDesc("room 3");

  // Connect Room 3 and Start
     start.setSide(2, r3);
     r3.setSide(3, start);

  // Room 4:
     Room r4 = new Room();
     r4.setDesc("room 4");
     
  // Connect Room 4 and Start 
     start.setSide(1, r4);
     r4.setSide(0, start);

  // Room 5:
     Room r5 = new Room();
     r5.setDesc("room 5");
     
  // Connect Room 5 and Start   
     start.setSide(5, r5);
     r5.setSide(4, start);

  // Room 6:
     Room r6 = new Room();
     r6.setDesc("room 6");
     
  // Connect Room 6 and Start
     start.setSide(4, r6);
     r6.setSide(5, start);

  // Room 7:
     Room r7 = new Room();
     r7.setDesc("room 7");
     
  //Connect Room 4 and Room 7
     r4.setSide(5, r7);
     r7.setSide(4, r4);

  // Room 8:
     Room r8 = new Room();
     r8.setDesc("room 8");
     
  // Connect Room 8 and Room 2
     r2.setSide(0, r8);
     r8.setSide(1, r2);

  // Room 9:
     Room r9 = new Room();
     r9.setDesc("room 9");
     
  // Connect Room 3 and Room 9
     r3.setSide(2, r9);
     r9.setSide(3, r3);

  // Room 10:
     Room r10 = new Room();
     r10.setDesc("room 10");
     
  // Connect Room 10 and Room 8
     r8.setSide(5, r10);
     r10.setSide(4, r8);

 // Room 11:
    Room r11 = new Room();
    r11.setDesc("room 11");
    
 // Connect Room 11 and Room 9
    r9.setSide(5, r11);
    r11.setSide(4, r9);
    
 // Room 12
    Room r12 = new Room();
    r12.setDesc("room 12");
    
 // Connect Room 12 and Room 6
    r6.setSide(0, r12);
    r12.setSide(1, r6);
    
 // Room 13
    Room r13 = new Room();
    r13.setDesc("room 12");
    
    //Connect Room 13 to Room 6
    r6.setSide(2, r13);
    r13.setSide(3, r6);
    
 // Add a portal in Room 13 that takes players to a random room
    
 // Room exit
    Room exit = new Room();
    exit.setDesc("exit");
    
 // Connect Room 12 and Exit
    r12.setSide(4, exit);
    exit.setSide(5, r12);
    
 // Create a flashlight and put it in the Start room
    Flashlight theFlashlight = new Flashlight();
    theFlashlight.setDesc("A red flashlight");
    start.addItem(theFlashlight);
    
 // Create 2 batteries and place them in rooms 5 and 11
    Battery batteryOne = new Battery();
    Battery batteryTwo = new Battery();
    batteryOne.setDesc("A battery");
    batteryTwo.setDesc("A battery");
    r5.addItem(batteryOne);
    r11.addItem(batteryTwo);
    
 // Create an oil can and place it in room 10
    Oil theOil = new Oil();
    theOil.setDesc("An oil can");
    r10.addItem(theOil);

 // Create a key and put it in r7:
    Key theKey = new Key();
    theKey.setDesc("A shiny gold key.");
    r7.addItem(theKey);

 // We add a door between r10 and r11: 
    RustedDoor theDoor = new RustedDoor(exit,r12,theKey,theOil);
    r12.setSide(4,theDoor);
    exit.setSide(5,theDoor);

 // Now return the entrance:
    entrance = start;
    return entrance;

  }
}
