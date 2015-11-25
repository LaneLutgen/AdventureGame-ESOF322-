package esof322.a2;

public class Game {
	
	private Room room;
	private Player player;
	private ILevel level;
	private Item[] items;
	
	public Game(){
		room = null;
		player = null;
		level = null;
		items = null;
	}
	
	public Game(Room room, Player player, ILevel level, Item[] items){
		this.room = room;
		this.player = player;
		this.level = level;
		this.items = items;
	}
	
	public Room getRoom(){
		return room;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public ILevel getLevel(){
		return level;
	}
	
	public Item[] getItems(){
		return items;
	}
}
