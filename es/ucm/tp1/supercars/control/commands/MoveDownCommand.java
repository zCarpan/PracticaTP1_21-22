package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GamePrinter;

public class MoveDownCommand extends Command {
	

	private static final String NAME = "Move Down";

	private static final String DETAILS = "[a]";

	private static final String SHORTCUT = "a";

	public static final String HELP = DETAILS + ": go down";

	public MoveDownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.pGoDown();
		return true;
	}

}