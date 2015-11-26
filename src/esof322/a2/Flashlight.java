package esof322.a2;

public class Flashlight extends Item {
	
	private int batteryLife;
	private String description;
	
	public Flashlight(){
		batteryLife = 100;
	}
	
	public void setDesc(String d){
		description = d;
	}
	
	public String getDesc(){
		return description;
	}
	
	public boolean decreaseBatteryLife(){
		if(batteryLife > 0){
			batteryLife -= 5;
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getBatteryLife(){
		return batteryLife;
	}
	
	public void addBattery(){
		batteryLife = 100;
	}
}
