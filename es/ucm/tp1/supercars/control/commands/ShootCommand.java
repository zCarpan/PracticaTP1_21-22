package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.instantActions.ShootAction;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.Buyable;

public class ShootCommand extends Command implements Buyable{

	private static final int cost = 1;
	
	private static final String NAME = "Shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	public static final String HELP = DETAILS + ": shoot bullet";
	
	public static final String ERROR_MSG = "[ERROR]: Failed to shoot";

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		
		try {
			buy(game);
			game.execute(new ShootAction());
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
