package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Obstacle extends GameObject{


	public static final String info = "[Obstacle] hits car";

	protected static int cont = 0;

	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		symbol = "░";
	}
	
	public static int getObstacleNumber() {
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
		return false;
	}
	@Override
	public boolean receiveCollision(Player player) {
		
		player.kill();
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
		kill();
		return true;
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
		System.out.print(" -> " + toString());
		kill();
		return true;
	}
	
	
}
