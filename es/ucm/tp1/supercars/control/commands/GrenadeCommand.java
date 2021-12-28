package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.InvalidPositionException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.Buyable;

public class GrenadeCommand extends Command implements Buyable{

	private static final int cost = 3;
	
	private int x;
	
	private int y;
	
	private static final String NAME = "Grenade";

	private static final String DETAILS = "[g]renade";

	private static final String SHORTCUT = "g";

	public static final String HELP = DETAILS + " <x> <y>: add a grenade in position x, y";

	public static final String ERROR_MSG = "[ERROR]: Failed to add grenade";
	
	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override 
	
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			else{
				x = Integer.parseInt(words[1]);
				y =  Integer.parseInt(words[2]);
				return this;
			}
		}
		return null;
	}
	
	@Override
	public boolean execute(Game game) {
		try {
			if(game.getObjectInPosition(x + game.pGetX(), y) == null && game.isInRoadLimits(x + game.pGetX(), y)){
				buy(game);
				game.forceAddObject(new Grenade(game, x + game.pGetX(), y));
				game.update();
				game.increaseCycle();
			}
			else throw new InvalidPositionException(INVALID_POSITION_MSG);
		}
		catch (CommandExecuteException e) {
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
