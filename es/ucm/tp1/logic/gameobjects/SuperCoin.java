package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class SuperCoin extends Coin{

	private static final int VALUE = 1000;

	public static final String info = "[SUPERCOIN] gives " + VALUE + " coins";
	
	private static boolean present = false;
	
	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "$";
		present = true;
	}
	public boolean receiveCollision(Player player){
		
		kill();
		present = alive;
		player.addCoins(VALUE);
		return false;
	}
	
	public static boolean isPresent() {
		return present;
	}
	
	public static void reset() {
		present = false;
	}
}
