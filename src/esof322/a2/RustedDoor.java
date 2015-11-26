package esof322.a2;

import java.io.Serializable;

public class RustedDoor extends Door implements Serializable {

	private Oil oilCan;

	public RustedDoor(CaveSite out, CaveSite in, Key k, Oil oil) {
		super(out, in, k);
		oilCan = oil;
	}

	public void enter(Player p) {
		if (p.haveItem(myKey) && p.haveItem(oilCan)) {
			p.setLogTxt("Your key works and you lubricate the door with the oil! The door creaks open, "
					+ "and slams behind you after you pass through.");
			if (p.getLoc() == outSite)
				inSite.enter(p);
			else if (p.getLoc() == inSite)
				outSite.enter(p);
		} else if (p.haveItem(myKey)) {
			p.setLogTxt("You try using the key but the door is rusted shut! \n"
					+ "You need to find some way to lubricate the door handle.");
		} else {
			p.setLogTxt("You try opening the door but it is locked shut!");
		}

	}
}
