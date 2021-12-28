package es.ucm.tp1.supercars.control.commands;

import java.io.IOException;
import java.util.Arrays;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;


public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	protected static final String INVALID_POSITION_MSG = "Invalid postion";

	/* @formatter:off */
	private static final Command[] AVAILABLE_COMMANDS = {
		new GrenadeCommand(),
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new MoveDownCommand(),
		new MoveUpCommand(),
		new ExitCommand(),
		new TestCommand(),
		new ResetCommand(),
		new ShootCommand(),
		new ClearCommand(),
		new WaveCommand(),
		new DumpCommand(),
		new CheatCommand(),
		new SerializeCommand(),
		new SaveCommand(),
		new ShowRecordCommand(),
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords/*, Level level*/) throws CommandParseException{
		Command command = null;
		boolean parseFailed = false;
		for (Command c: AVAILABLE_COMMANDS) {
			try {
				command = c.parse (commandWords);
			}
			catch (CommandParseException e) {
				parseFailed  = true;
				System.out.println(e.getMessage());
			}
			if(command != null) {
				return command;
			}
		}
		if (command == null && !parseFailed) {
			parseFailed  = false;
			throw new CommandParseException (String.format("[ERROR]: Command %s: %s%n%n", commandWords[0], UNKNOWN_COMMAND_MSG));
		}
		return command;
	}

	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;

	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}

	public abstract boolean execute(Game game) throws CommandExecuteException, IOException;

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) 
				throw new CommandParseException (String.format("[ERROR]: Command %s: %s%n%n", name, INCORRECT_NUMBER_OF_ARGS_MSG));
			
			else 
				return this;
		}
		return null;
	}

}
