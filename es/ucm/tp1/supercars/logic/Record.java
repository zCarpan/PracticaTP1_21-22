package es.ucm.tp1.supercars.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;



public class Record {

	private static final String FILENAME = "record.txt";
	
	private static final long DEFAULT_RECORD_VALUE = Long.MAX_VALUE;
	
	private boolean loaded = false;

	ArrayList<String> availableLevels = new ArrayList<String>();

	private long arrayRecords[] = new long[Level.values().length];
	private String arrayLevels[] = new String [Level.values().length];
	
	
	public Record () {
		addToAvailableLevels();
	}
	public void loadRecord() throws InputOutputRecordException, IOException{
		
		BufferedReader br = null;
		loaded = false;
		try {
			br = new BufferedReader(new FileReader(FILENAME));
			
			for (int i = 0; i < Level.values().length; i++) {
				String line = br.readLine();
				if (line == null) {
					
					if(availableLevels.size() > 0) {
						arrayLevels[i] = availableLevels.get(0);
						deleteUsedLevel(arrayLevels[i]);
						arrayRecords[i] = DEFAULT_RECORD_VALUE;
					}
				}
				else {
					String[] parts = line.split(":");
					arrayLevels[i] = parts[0];
					deleteUsedLevel(arrayLevels[i]);			
					arrayRecords[i] = Long.parseLong(parts[1]);
				}
			}
			loaded = true;
		}
		catch (FileNotFoundException e) {
			throw new InputOutputRecordException("File " + FILENAME +" not found");
		}
		catch (IOException ex) {
			throw new InputOutputRecordException("IO");
		}
		catch(NumberFormatException nfe) {
			throw new InputOutputRecordException("File " + FILENAME +" is corrupted");
		}
		br.close();
	}
	
	
	public void saveRecord() throws IOException {
		
		StringBuilder str = new StringBuilder();
		FileWriter recordFile = null;
		try {
			recordFile = new FileWriter(FILENAME);
			for (int i = 0; i < Level.values().length; i++) {
				recordFile.write(arrayLevels[i] + ":" + arrayRecords[i] + "\n");
			}
		  } 
		catch (IOException e) {
		     throw new IOException("Error opening the file");
		}
		finally {
			recordFile.close();
		}
	}
	
	public String getArrayLevels(int i) {
		return arrayLevels[i];
	}
	
	public long getArrayRecords(int i) {
		return arrayRecords[i];
	}
	
	public void updateRecord(Game game) {
		if(game.playerWins()) {
			for (int i = 0; i < Level.values().length; i++) {
				if (game.getLevel().toString().equals(arrayLevels[i])) {
					if(GamePrinter.getElapsedTime() < arrayRecords[i]) {
						arrayRecords[i] = GamePrinter.getElapsedTime();
					}
				}
			}
		}
		try{
			if(loaded)
				saveRecord();
		}
		catch (IOException e) {
		      System.out.println(e.getMessage());
		}
		
	}
	
	public void addToAvailableLevels() {
		for (Level level : Level.values()) {
			availableLevels.add(level.name());
		}
	}
	
	public void deleteUsedLevel(String level){
		for(int i= 0; i < availableLevels.size(); i++) {
			if(level.equals(availableLevels.get(i))) {
					availableLevels.remove(i);
				}
			}
		}
}
