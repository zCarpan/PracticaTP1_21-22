package es.ucm.tp1.instantActions;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ThunderAction implements InstantAction{
	
	int x= 0;
	int y = 0;
	public ThunderAction(){
		
	}
	@Override
	public void execute(Game game) {
		
		x = game.getRandomColumn();
		y = game.getRandomLane();
		
		String s = "Thunder hit position: (" + x + ", " +  y + ")";
		
		Collider other = game.getObjectInPosition(x + game.pGetX(), y);
		if (other != null)  {
			System.out.print(s);
			other.receiveThunder();
			System.out.println();
		}

	}
}