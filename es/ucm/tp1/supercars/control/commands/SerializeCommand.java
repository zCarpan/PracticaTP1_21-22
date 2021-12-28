package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.control.Controller;
import es.ucm.tp1.logic.Game;


public class SerializeCommand extends Command{
	
	private static final String NAME = "serialize";

	private static final String DETAILS = "[i]nfo";

	private static final String SHORTCUT = "z";

	public static final String HELP = DETAILS + ": serialize the game";

	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	@Override
	public boolean execute(Game game) {
		
		Controller.serializedView = true;
		return true;
	}
}
