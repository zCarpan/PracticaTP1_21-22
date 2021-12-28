package es.ucm.tp1.control;

import java.io.IOException;
import java.util.Scanner;

import es.ucm.tp1.exceptions.GameException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.view.GamePrinter;
import es.ucm.tp1.view.GameSerializer;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "[ERROR]: Unknown command";
	
	public static boolean serializedView = false;
	
	private String command;
	
	private boolean reachedEnd;
	
	private boolean refreshDisplay = true;
	
	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;
	
	private GameSerializer serializePrinter;
	
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
		this.serializePrinter = new GameSerializer(game);
	}

	public void printGame() {
		if (serializedView) {
			System.out.println(serializePrinter);
			serializedView = false;
		}
		else System.out.println(printer);
	}
	
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}
	
	public void run() throws IOException {
		while (!game.isFinished()){
			if (refreshDisplay) {
				printGame();
			}
			refreshDisplay = false;
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			Command command;
			
			try {
				command = Command.getCommand(parameters);
				refreshDisplay = command.execute(game);
			}
			catch (GameException e) {
				System.out.println(e.getMessage());
			}
			catch (NullPointerException f) {
			}
			 
		}
		if(!game.checkPlayerLeaves()) printGame();
		game.updateRecord();
		printEndMessage();

	}
	
	public boolean getSerializedView() {
		return serializedView;
	}
}
