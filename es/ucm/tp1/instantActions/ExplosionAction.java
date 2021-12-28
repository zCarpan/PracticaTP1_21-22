package es.ucm.tp1.instantActions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ExplosionAction implements InstantAction{
	
	private int x;
	private int y;
	private int radius;
	
	public ExplosionAction(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void execute(Game game) {
		
		for (int i = x - radius; i <= x + radius; i++) {
			for (int j = y - radius; j <= y + radius; j++) {
					Collider other = game.getObjectInPosition(i, j);
					if (other != null) {
					other.receiveExplosion();

				}
			}
		}
	}
}