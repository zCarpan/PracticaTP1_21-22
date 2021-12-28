package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.GameObject;

public class ClearCommand extends Command{
	private static final String NAME = "clear";

	private static final String DETAILS = "[c]lear | []";

	private static final String SHORTCUT = "c";

	public static final String HELP = "Clear [0]: Clears the road";

	public ClearCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.clearObjects();
		game.update();
		return true;
	}
}
