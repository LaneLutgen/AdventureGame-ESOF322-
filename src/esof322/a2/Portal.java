package esof322.a2;
/*
 * @author Lane Lutgen
 */
import java.util.*;
import java.io.Serializable;

public class Portal implements CaveSite, Serializable{
	
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
