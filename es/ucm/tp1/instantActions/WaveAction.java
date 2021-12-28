package es.ucm.tp1.instantActions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.InstantAction;

public class WaveAction implements InstantAction{
	
	public WaveAction() {
		
	}
	@Override
	public void execute(Game game) {
		
		for (int y = 0; y < game.getRoadWidth(); y++) {
			for (int x = game.getVisibility() + game.pGetX(); x >= game.pGetX(); x--) {
				
				Collider other = game.getObjectInPosition(x + 1, y);
				if (other == null) {
					other = game.getObjectInPosition(x, y);
					if (other != null) other.receiveWave();
				}
			}
		}
	}
}