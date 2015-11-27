package esof322.a2;
/*
 * @author Lane Lutgen
 */
public class Flashlight extends Item {
	
	private int batteryLife;
	
	public Flashlight(){
		batteryLife = 100;
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
