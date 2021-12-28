package es.ucm.tp1.logic;

import java.io.IOException;
import java.util.Random;
import es.ucm.tp1.control.Level;
import es.ucm.tp1.exceptions.InputOutputRecordException;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.GameObjectContainer;
import es.ucm.tp1.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;
import es.ucm.tp1.supercars.logic.InstantAction;
import es.ucm.tp1.supercars.logic.Record;


public class Game {

	private Player player;
	private Level level;
	private Random rand;
	private GameObjectContainer gameObjectContainer;
	private GameObjectGenerator gameObjectGenerator;
	private int cycle;
	
	private long startingTime;
	private long currentTime;
	private boolean testMode = false;
	private long seed;
	private boolean exit = false;
	private boolean pLeaves = false;
	
	private Record record;
	
	public Game(long seed, Level level) {
		initilize(seed,level);
	}
	
	public void initilize(long seed, Level level) {
		
		startingTime = System.currentTimeMillis();
		cycle = 0;
		this.level = level;
		this.seed = seed;
		rand = new Random(seed);
		gameObjectContainer = new GameObjectContainer();
		gameObjectGenerator = new GameObjectGenerator();
		gameObjectGenerator.reset(level);
		player = new Player(this);
		gameObjectGenerator.generateGameObjects(this, level);
		record = new Record();
		try{
			record.loadRecord();
		}
		catch (InputOutputRecordException e) {
			System.out.println(e.getMessage());
			exit = true;
		}
		catch (IOException ex) {
			exit = true;
			System.out.println(ex.getMessage());
		}
		
	}

	public void toggleTest() {
		testMode = true;
	}
	public void setExitTrue() {
		exit = true;
	}
	
	public int getVisibility() {
		return level.getLevelVisibility();
	}
	
	public int getRoadLength() {
		return level.getLevelLength();
	}
	
	public int getRoadWidth() {
		return level.getLevelWidth();
	}
	
	public boolean isInRoadLimits(int x, int y) {
	
		return (x>=0 && x < pGetX() + getVisibility() && y >= 0 && y < getRoadWidth());
	}
	
	public String positionToString(int x, int y) {
		String s = "";
		if(player.isInPosition(x, y)) {
			s = s + player.toString() + " ";
		}
		s += gameObjectContainer.positionToString(x, y);
	
		if(x == level.getLevelLength()) {
			s = s + "¦";
		}
		
		return s;
	}
	
	public boolean checkEndCondition() {
		return (!pIsAlive() || pGetX() >= getRoadLength() + 1);
	}
	
	public void pGoUp(){
		player.goUp();
	}
	public void pGoDown(){
		player.goDown();
	}
	public void pGoForward() {
		player.goForward();
	}
	public void update() {
		gameObjectContainer.update();
		removeDeadObjects();
	}
	
	public void removeDeadObjects() {
		gameObjectContainer.removeGameObjects();
	}
	public void increaseCycle() {
		GameObjectGenerator.generateRuntimeObjects(this);
		cycle++;
	}
	public int pGetX(){
		return player.getX();
	}
	
	public int pGetY() {
		return player.getY();
	}

	public boolean pIsAlive() {
		return player.isAlive();
	}
	
	public int pGetCoins() {
		return player.getCoins();
	}
	
	public Double getRandomNumber() {
		return rand.nextDouble(); 
	} 
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth()); 
	}
	
	public int getRandomColumn() {
		return (int) (getRandomNumber() * getVisibility()); 
	}
	public GameObject getObjectInPosition(int x, int y) {
		return gameObjectContainer.getContainerObjectInPosition(x, y);
	}
	
	public boolean getTestMode() {
		return testMode;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public long getStartingTime() {
		return startingTime;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime() {
		currentTime = System.currentTimeMillis();
	}
	
	public int getCycle() {
		return cycle;
	}

	public boolean isFinished() {
		if(checkEndCondition()) exit = true;
		return exit;
	}

	public void tryToAddObject(GameObject g, double levelObjectFrequency) {
		if(rand.nextDouble() < levelObjectFrequency && gameObjectContainer.getContainerObjectInPosition(g.getX(), g.getY()) == null){
			 gameObjectContainer.generate(g);
			 g.onEnter();
		}
	}

	public void playerLeaves() {
		pLeaves = true;
	}
	public boolean checkPlayerLeaves() {
		return pLeaves;
	}

	public void pSubCoin() {
		player.subCoins();
	}
	
	public void pSubCoin(int x) {
		player.subCoins(x);
	}
	
	public void pAddCoin (int x) {
		player.addCoins(x);
	}
	
	public void clearObjects() {
		for(int x= pGetX(); x < getRoadLength(); x++) {
			for(int y = 0; y < getRoadWidth(); y++) {
				gameObjectContainer.clearObjects(x, y);
			}
		}
		removeDeadObjects();
	}

	public void execute(InstantAction action) {
		action.execute(this);
	}

	public void forceAddObject(GameObject o) {
		gameObjectContainer.generate(o);
	}
	
	public static int getMinId() {
		return GameObjectGenerator.getMinId();
	}
	
	public static int getMaxId() {
		return GameObjectGenerator.getMaxId();
	}
	
	public Record getRecord() {
		return record;
	}

	public void updateRecord() {
		record.updateRecord(this);
	}
	
	public boolean playerWins() {
		return pGetX() >= getRoadLength();
	}

	public void updateAfterMove() {
		update();
		removeDeadObjects();
		increaseCycle();
	}

	
}
