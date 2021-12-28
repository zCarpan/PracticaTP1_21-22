package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.instantActions.WaveAction;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.Buyable;

public class WaveCommand extends Command implements Buyable{

	private static final int cost = 5;
			
	private static final String NAME = "Wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	public static final String HELP = DETAILS + ": do wave";
	
	public static final String ERROR_MSG = "[ERROR]: Failed to wave";

	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game){
		
		try {
			buy(game);
			game.execute(new WaveAction());
			game.update();
			game.increaseCycle();
		}
		catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			System.out.println (ERROR_MSG);
		}
		return true;
	}

	@Override
	public int cost() {
		return cost;
	}

}

