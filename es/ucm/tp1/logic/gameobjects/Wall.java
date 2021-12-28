package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;


public class Wall extends Obstacle{

	public final static String info = "[WALL] hard obstacle";
	
	private static final String[] SYMBOLS = new String[] {"░", "▒", "█" };
	
	private final static int value = 5;
	
	private int resistance = 3;

	@Override
	public String toString() {
	    return SYMBOLS[resistance - 1];
	}
	
	@Override
	public String toSerialize() {
		
		if (isAlive())
			return toString() + "(" + x + ", " + y + ") " + resistance;
		
		return "";
	}
	
	public Wall(Game game, int x, int y) {
		super(game, x, y);
	}
	
	@Override
	public boolean receiveShoot() {
		resistance--;
		if (resistance == 0) kill();
		return true;
	}
	
	@Override
	public void onDelete() {
		game.pAddCoin(value);
		cont--;
	}
	
}
