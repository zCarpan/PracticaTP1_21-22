package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GamePrinter;

public class InfoCommand extends Command {
	
	private static final String INFO = "Available objects:\n";
	
	private static final String NAME = "info";

	private static final String DETAILS = "[i]nfo";

	private static final String SHORTCUT = "i";

	public static final String HELP = DETAILS + ": prints gameobject info";

	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	@Override
	public boolean execute(Game game) {

		System.out.println(INFO + GamePrinter.getGameObjectInfo());
		return false;
	}

}