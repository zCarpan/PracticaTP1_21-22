package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;

public class ResetCommand extends Command{
	
	private static final String LEVEL_INFO_MSG = "Level must be one of: " + Level.all(", ");
	
	private static final String NAME = "Reset";

	private static final String DETAILS = "[r]eset";

	private static final String SHORTCUT = "r";

	public static final String HELP = DETAILS +" [<level> <seed>]: reset game";
	
	private static final String SEED_INFO_MSG = "Random generator initialized with seed: ";
	
	private static final String SEED_IS_NUMBER_MSNG = "the seed must be a number";
	
	private static final String USAGE_MSG = "Usage: Super cars <level> [<seed>]";
	
	private Level level;
	
	private long seed;
	
	private boolean resetSameParameters = false;
	
	private boolean resetWrongInput = false;
	

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {

		if(resetSameParameters){
			level = game.getLevel();
			seed = game.getSeed();
			resetSameParameters = false;
		}
		if(!resetWrongInput) { 
			System.out.println("Level: " + level.name());
			System.out.println(SEED_INFO_MSG + seed);
			game.initilize(seed, level); 
		}
		resetWrongInput = false;
		return true;
	}
	
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length > 3 || words.length == 2) {
				throw new CommandParseException(String.format("[ERROR]:Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 
			else {
				if (words.length == 1) {
					resetSameParameters = true;
				}
				else if(words.length==3){
					try  {
						level = Level.valueOfIgnoreCase(words[1]);
						if(level == null) 
							throw new CommandParseException(String.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, LEVEL_INFO_MSG));
						seed = Long.parseLong(words[2]);
					}
					catch (CommandParseException e) {
						resetWrongInput = true;
						System.out.println(e.getMessage());
					}
					catch (NumberFormatException nfe) {
						resetWrongInput = true;
						System.out.println(nfe.getMessage());
					}
				}
				
				return this;
			}
		}
		else return null;
	}

	
}
