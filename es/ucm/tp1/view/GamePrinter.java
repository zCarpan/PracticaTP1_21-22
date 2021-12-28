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
import es.ucm.tp1.supercars.control.commands.DumpCommand;
import es.ucm.tp1.supercars.control.commands.ExitCommand;
import es.ucm.tp1.supercars.control.commands.GrenadeCommand;
import es.ucm.tp1.supercars.control.commands.HelpCommand;
import es.ucm.tp1.supercars.control.commands.InfoCommand;
import es.ucm.tp1.supercars.control.commands.MoveDownCommand;
import es.ucm.tp1.supercars.control.commands.MoveUpCommand;
import es.ucm.tp1.supercars.control.commands.ResetCommand;
import es.ucm.tp1.supercars.control.commands.SaveCommand;
import es.ucm.tp1.supercars.control.commands.SerializeCommand;
import es.ucm.tp1.supercars.control.commands.ShootCommand;
import es.ucm.tp1.supercars.control.commands.TestCommand;
import es.ucm.tp1.supercars.control.commands.UpdateCommand;
import es.ucm.tp1.supercars.control.commands.WaveCommand;
import es.ucm.tp1.utils.*;


public class GamePrinter {
	
	private static final String SPACE = " ";

	private static final String VERTICAL_DELIMITER = "|";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;
	
	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game"; 
	
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	
	public String newLine; 

	protected Game game;
	
	private static float elapsedTime;
	boolean firstTime = true;
	
	private static long finishTime;

	public GamePrinter(Game game) {
		this.game = game;
	}
	

	private String getInfo() {

		String s = "Distance: " + (game.getRoadLength() - game.pGetX()) + "\n" +
					"Coins: " + game.pGetCoins() + "\n" + 
					"Cycle: " + game.getCycle() + "\n" +
					"Total obstacles: " + Obstacle.getObstacleNumber() + "\n" + 
					"Total coins: " + Coin.getCoinNumber();
			
			if(SuperCoin.isPresent())
				s = s +"\nSupercoin is present";
			
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
			   
			   String v = String.format(Locale.FRANCE,"%.2f", elapsedTime);
			   s += "\n" + "Elapsed Time: " + v + " s";
		   };
		   
		   return s;
	}
	
	@Override
	public String toString() {
		
		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
		newLine =  System.getProperty("line.separator");
		

		newLine =  System.getProperty("line.separator");
		
		StringBuilder str = new StringBuilder();

		// Game Status
		
		str.append(getInfo());
		
		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = game.pGetX(); x < game.getVisibility() + game.pGetX(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);
		
		return str.toString();
	}
	
	public static String getGameObjectInfo(){
		StringBuilder b = new StringBuilder();
		b.append(Player.info +"\n");
		b.append(Coin.info +"\n");
		b.append(Obstacle.info +"\n");
		b.append(Grenade.info + "\n");
		b.append(Wall.info + "\n");
		b.append(Turbo.info + "\n");
		b.append(SuperCoin.info + "\n");
		b.append(Truck.info + "\n");
		b.append(Pedestrian.info);
		
		return b.toString();
	}
	
	public static String getGameHelp() {
		StringBuilder b = new StringBuilder();
		b.append(HelpCommand.HELP +"\n");
		b.append(InfoCommand.HELP +"\n");
		b.append(UpdateCommand.HELP +"\n");
		b.append(MoveUpCommand.HELP + "\n");
		b.append(MoveDownCommand.HELP + "\n");
		b.append(ExitCommand.HELP + "\n");
		b.append(ResetCommand.HELP + "\n");
		b.append(TestCommand.HELP + "\n");
		b.append(ShootCommand.HELP + "\n");
		b.append(GrenadeCommand.HELP + "\n");
		b.append(WaveCommand.HELP + "\n");
		b.append(SerializeCommand.HELP + "\n");
		b.append(SaveCommand.HELP + "\n");
		b.append(DumpCommand.HELP + "\n");
		
		b.append(ClearCommand.HELP + "\n");
		b.append(CheatCommand.HELP + "\n");
		return b.toString();
	}

	public String endMessage(){
		
		String s = GAME_OVER_MSG;
		
		if(!game.pIsAlive()) {
			s = s + CRASH_MSG;
		}
		else if (game.playerWins()) {
			if(game.getTestMode()) {
				s = s + WIN_MSG;
			}
			else { 
				String v = String.format("%.2f", elapsedTime); 
			    s = s + WIN_MSG + "New record!: " + v + " s";
			}
		}
		else if(game.isFinished()) {
			s = s + DO_EXIT_MSG;
		}
		
		return s;
	}
	
	public static long getElapsedTime(){
		return finishTime;
	}
}
