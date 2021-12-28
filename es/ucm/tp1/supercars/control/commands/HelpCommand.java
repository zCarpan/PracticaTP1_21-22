package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.Grenade;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.Pedestrian;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.logic.gameobjects.SuperCoin;
import es.ucm.tp1.logic.gameobjects.Truck;
import es.ucm.tp1.logic.gameobjects.Turbo;
import es.ucm.tp1.logic.gameobjects.Wall;
import es.ucm.tp1.utils.StringUtils;
import es.ucm.tp1.view.GamePrinter;

public class HelpCommand extends Command {
	
	private static final String INFO = "Available commands:\n";

	private static final String NAME = "help";

	private static final String DETAILS = "[h]elp";

	private static final String SHORTCUT = "h";

	public static final String HELP = DETAILS + ": show this help";

	public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(INFO + GamePrinter.getGameHelp());
		return false;
	}

}
