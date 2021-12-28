package es.ucm.tp1.view;

import java.util.Locale;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.Grenade;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.Pedestrian;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.logic.gameobjects.SuperCoin;
import es.ucm.tp1.logic.gameobjects.Truck;
import es.ucm.tp1.logic.gameobjects.Turbo;
import es.ucm.tp1.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.control.commands.CheatCommand;
import es.ucm.tp1.supercars.control.commands.ClearCommand;
import es.ucm.tp1.supercars.control.commands.ExitCommand;
import es.ucm.tp1.supercars.control.commands.GrenadeCommand;
import es.ucm.tp1.supercars.control.commands.HelpCommand;
import es.ucm.tp1.supercars.control.commands.InfoCommand;
import es.ucm.tp1.supercars.control.commands.MoveDownCommand;
import es.ucm.tp1.supercars.control.commands.MoveUpCommand;
import es.ucm.tp1.supercars.control.commands.ResetCommand;
import es.ucm.tp1.supercars.control.commands.ShootCommand;
import es.ucm.tp1.supercars.control.commands.TestCommand;
import es.ucm.tp1.supercars.control.commands.UpdateCommand;
import es.ucm.tp1.supercars.control.commands.WaveCommand;
import es.ucm.tp1.utils.StringUtils;


public class GameSerializer {
	
	protected Game game;
	private float elapsedTime;
	private boolean firstTime = true;
	private static long finishTime;
	
	public GameSerializer(Game game) {
		this.game = game;
	}

private String getInfo() {

		String s = "Level: " + game.getLevel() + "\n" +
					"Cycle: " + game.getCycle() + "\n" +
					"Coins: " + game.pGetCoins() + "\n" + 
					"GameObjects:";
		   
		   if (!game.getTestMode()) {
			   if (firstTime) {
				   elapsedTime = 0; 
				   firstTime = false;
			   }
			   else {
			   game.setCurrentTime();
			   elapsedTime = (float) ((game.getCurrentTime() - game.getStartingTime())/1000.0);
			   finishTime = (game.getCurrentTime() - game.getStartingTime());
			   }
			   
			   String time = String.format(Locale.FRANCE,"%.2f", elapsedTime);
			   s += "\n" + "Elapsed Time: " + time + " s";
		   };
		   
		   return s;
	}

public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("---- ROAD FIGHTER SERIALIZED ----\n");

		str.append(getInfo() + "\n");
		
		str.append(game.positionToString(game.pGetX(), game.pGetY()) + "(" + game.pGetX() + ", "+ game.pGetY() + ")\n");
		for (int x = 0; x < game.getRoadLength(); x++) {
			for (int y = 0; y < game.getRoadWidth(); y++) {
				if(game.getObjectInPosition(x, y) != null) {
					str.append((game.getObjectInPosition(x, y).toSerialize() + "\n"));
				}
			}
		}
		return str.toString();
	}

public static long getElapsedTime(){
	return finishTime;
}
}
