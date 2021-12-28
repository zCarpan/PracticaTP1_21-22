package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.logic.Game;


public class DumpCommand extends Command{
	
	String filename;
	
	private static final String NAME = "Dump";

	private static final String DETAILS = "[d]ump";

	private static final String SHORTCUT = "d";

	public static final String HELP = DETAILS + " <filename>: Shows the content of a saved file\r\n";
	
	private static final String OPENING_ERROR = "An error ocurred on reading a file";
	
	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) throws IOException{
		
		filename += ".txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    System.out.println(sb.toString());
		    br.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println(OPENING_ERROR);
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
