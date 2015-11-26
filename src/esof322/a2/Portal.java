package esof322.a2;

import java.util.*;

public class Portal implements CaveSite{
	
	private ArrayList<Room> rooms;
	private Random ran;
	
	public Portal(ArrayList<Room> rooms){
		this.rooms = rooms;
		ran = new Random();
	}
	public void enter(Player p){
		int index = ran.nextInt(rooms.size());
		Room randomRoom = rooms.get(index);
		p.setLoc(randomRoom);
	}
}
