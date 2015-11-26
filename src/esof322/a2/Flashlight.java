package esof322.a2;

public class Flashlight extends Item {
	
	private int batteryLife;
	private String description;
	
	public Flashlight(){
		batteryLife = 100;
	}
	
	public void setDesc(String d){
		description = d + batteryLife + "%";
	}
}
