package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Coin extends GameObject{

	public static final String info = "[Coin] gives 1 coin to the player";

	private static int cont = 0;

	public Coin(Game game, int x, int y) {
		super(game, x, y);
		symbol = "¢";
	}
	
	public static int getCoinNumber() {
		return cont;
	}
	
	public static void reset() {
		cont = 0;
	}
	
	@Override
	public void onEnter() {
		cont++;
	}
	@Override
	public boolean doCollision() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean receiveCollision(Player player) {
		
		kill();
		player.addCoins();
		return false;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDelete() {
		cont--;
		
	}

	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveExplosion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveThunder() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
