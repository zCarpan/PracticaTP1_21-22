package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class CheatCommand extends Command{

	private int id;
	
	private static final String NAME = "Cheat";

	private static final String DETAILS = "[c]heat";

	private static final String SHORTCUT = "1";

	public static final String HELP = "Cheat [1..5]: Removes all elements of last visible column, and adds an Advanced Object";

	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	protected Command parse(String[] commandWords) throws CommandParseException{
		
		if(isNumeric(commandWords[0])) {
			id = Integer.parseInt(commandWords[0]);
			if (commandWords.length != 1) {
				throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
		}
		if (id >= Game.getMinId() && id <= Game.getMaxId()) 
			commandWords[0] = SHORTCUT;
		
		return super.parse(commandWords);
	}
	
	@Override
	public boolean execute(Game game) {
		
		GameObjectGenerator.forceAdvanceObject(game, id, game.pGetX() + game.getVisibility() - 1);
		return true;
	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
