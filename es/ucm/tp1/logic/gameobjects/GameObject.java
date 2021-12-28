package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.logic.Game;

public abstract class GameObject implements Collider{

	protected int x, y;

	protected Game game;

	protected String symbol;
	
	protected boolean alive = true;

	public GameObject(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	protected String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}

		return "";
	}

	public String toSerialize() {
		if (isAlive()) {
			return getSymbol() + "(" + x + ", " + y + ")";
		}
		return "";
	}
	
	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void kill() {
		alive = false;
	}
	
	public boolean receiveWave() {
		x = x + 1;
		return true;
	}
	
	public abstract void onEnter();

	public abstract void update();

	public abstract void onDelete();

	public abstract boolean receiveShoot();
	
	public abstract boolean receiveExplosion();
	
	public abstract boolean receiveThunder();
	
	

}
