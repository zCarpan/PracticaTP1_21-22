package es.ucm.tp1.instantActions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ShootAction implements InstantAction{
	public ShootAction() {
		
	}

	@Override
	public void execute(Game game) {
		
		boolean shooted = false;
		int i = 0;
		
		while (!shooted && i < game.getVisibility()) {
			
			Collider other = game.getObjectInPosition(game.pGetX() + i, game.pGetY());
			
			if (other != null) 
				shooted = other.receiveShoot();
			i++;
		}
		
	}
}
