package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Pedestrian extends Obstacle{
	
	public final static String info = "[PEDESTRIAN] person crossing the road up and down";
	
	private boolean ascend = true;

	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		symbol = "☺";
	}
	
	@Override
	public String toSerialize() {
		
		String direction;
		if (isAlive()) {
			
			if (ascend) direction = "up";
			else direction = "down";
			return toString() + "(" + x + ", " + y + ") " + direction;
			
		}
		return "";
	}
	
	@Override
	public void update() {
		if (y == 0) ascend = false;
		if (ascend && y>0) {
			y--;	
		}
		else {
			y ++;
			if (y == game.getRoadWidth() - 1) ascend = true;
		}
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		player.kill();
		player.subCoins(player.getCoins());
		return false;
	}
	
	@Override 
	public boolean receiveShoot(){
		
		kill();
		game.pSubCoin(game.pGetCoins());
		return false;
	}
	
	public boolean receiveExplosion() {
		return receiveShoot();
	}
}

