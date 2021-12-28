package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class UpdateCommand extends Command {

	private static final String NAME = "update";

	private static final String DETAILS = "[n]one | []";

	private static final String SHORTCUT = "n";

	public static final String HELP = DETAILS + ": update";

	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game){
		game.pGoForward();
		return true;
	}

	@Override
	protected Command parse(String[] commandWords) throws CommandParseException{
		if ("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;
		}
		return super.parse(commandWords);
	}
}
