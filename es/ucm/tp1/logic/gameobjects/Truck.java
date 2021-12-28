package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;

public class Truck extends Obstacle{

	public final static String info = "[TRUCK] moves towards the player";
	public Truck(Game game, int x, int y) {
		super(game, x, y);
		symbol = "←";
	}

	@Override
	public void update() {
		x--;
	}
}
