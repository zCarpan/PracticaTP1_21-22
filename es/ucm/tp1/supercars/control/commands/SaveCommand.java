package es.ucm.tp1.supercars.control.commands;

import java.io.FileWriter;
import java.io.IOException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GameSerializer;

public class SaveCommand extends Command{
	
	String filename;
	
	private static final String NAME = "Save";

	private static final String DETAILS = "sa[v]e";

	private static final String SHORTCUT = "v";

	public static final String HELP = DETAILS + " <filename>: Save the state of the game to a file";
	
	private static final String OPENING_ERROR = "Failed to open the file";
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) throws IOException{
		
		filename += ".txt";
		StringBuilder str = new StringBuilder();
		GameSerializer serializer = new GameSerializer(game);
		FileWriter saveFile = new FileWriter(filename);
		
		try {
		     saveFile.write(serializer.toString());
		     System.out.println("Game successfully saved in file "+ filename);
		  } 
		catch (IOException e) {
		      System.out.println("Error opening the file");
		}
		finally {
			saveFile.close();
		}
		
		return true;
	}
	@Override
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length != 2) 
				throw new CommandParseException(String.format("[ERROR]:Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			else 
				filename = words[1];
			
			return this;
		}
		else return null;
	}
}
