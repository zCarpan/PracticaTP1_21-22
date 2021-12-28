package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;

public class TestCommand extends Command{
	private static final String NAME = "Test";

	private static final String DETAILS = "[t]est";

	private static final String SHORTCUT = "t";

	public static final String HELP = DETAILS + ": enables test mode";

	public TestCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.toggleTest();
		return true;
	}

}

