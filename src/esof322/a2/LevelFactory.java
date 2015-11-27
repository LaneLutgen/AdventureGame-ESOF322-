package esof322.a2;
/*
 * @author Lane Lutgen
 */
public class LevelFactory {
	public static ILevel chooseLevel(int level){
		switch(level){
			case 0:
				return new LevelZero();
			case 1:
				return new LevelOne();
			default:
				return new LevelZero();
		}

	}
}
