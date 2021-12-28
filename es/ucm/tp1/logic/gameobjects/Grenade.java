package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.instantActions.ExplosionAction;
import es.ucm.tp1.logic.Game;

public class Grenade extends GameObject{
	

	public static final String info = "[GRENADE] Explodes in 3 cycles, harming everyone around";
	
	private static final int radius = 1;
	private static final int delayTime = 5;
	private int delay;
	
	public Grenade(Game game, int x, int y){
		super (game, x, y);
		symbol = "ð";
		delay = delayTime;
	}

	public String toString() {
		return symbol + "["+ delay +"]";
	}
	
	@Override
	public String toSerialize() {
		if (isAlive()) {
			return toString() + " (" + x + ", " + y + ") " + delay;
		}
		return "";
	}
	@Override
	public boolean doCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		delay--;
		if (delay <= 0)
			kill();
	}

	@Override
	public void onDelete() {
			game.execute(new ExplosionAction(x, y, radius));
		// TODO Auto-generated method stub
	}

	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveExplosion() {
		// TODO Auto-generated method stub
		kill();
		return true;
	}

	@Override
	public boolean receiveThunder() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
