package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GamePrinter;

public class MoveUpCommand extends Command {
	

	private static final String NAME = "Move Up";

	private static final String DETAILS = "[q]";

	private static final String SHORTCUT = "q";

	public static final String HELP = DETAILS + ": go up";

	public MoveUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {	
		game.pGoUp();
		return true;
	}

}