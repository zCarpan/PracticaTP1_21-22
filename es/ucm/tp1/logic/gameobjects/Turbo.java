package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Turbo extends GameObject {

	private static final int JUMP_LENGTH = 3;
	
	public  final static String symbol = ">>>";

	public static final String info = "[TURBO] pushes the car: "+ JUMP_LENGTH +" columns";

	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		
	}
	
	public String toString() {
		return symbol;
	}

	@Override
	public String toSerialize() {
		return symbol + "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean doCollision() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {	
		player.setPlayerX(player.getX() + JUMP_LENGTH);
		return false;
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveExplosion() {
		return false;
	}

	@Override
	public boolean receiveThunder() {
		// TODO Auto-generated method stub
		return false;
	}

}
