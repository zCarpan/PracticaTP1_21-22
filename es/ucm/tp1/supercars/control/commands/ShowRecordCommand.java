package es.ucm.tp1.supercars.control.commands;

import java.io.IOException;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.logic.Game;

public class ShowRecordCommand extends Command{

	private static final String NAME = "Record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	public static final String HELP = DETAILS + "show level record";
	
	public ShowRecordCommand(){
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException, IOException {
		for (int i = 0; i < Level.values().length; i++) {
			if (game.getLevel().toString().equals(game.getRecord().getArrayLevels(i))) {
				System.out.println(game.getRecord().getArrayLevels(i) + " record is "  + (game.getRecord().getArrayRecords(i))/1000.00 + " s");
			}
		}
		return true;
	}

}
