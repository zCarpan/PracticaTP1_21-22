package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.control.Level;
import es.ucm.tp1.instantActions.ThunderAction;
import es.ucm.tp1.logic.Game;
import es.ucm.tp1.logic.gameobjects.Coin;
import es.ucm.tp1.logic.gameobjects.GameObject;
import es.ucm.tp1.logic.gameobjects.Obstacle;
import es.ucm.tp1.logic.gameobjects.Pedestrian;
import es.ucm.tp1.logic.gameobjects.SuperCoin;
import es.ucm.tp1.logic.gameobjects.Truck;
import es.ucm.tp1.logic.gameobjects.Turbo;
import es.ucm.tp1.logic.gameobjects.Wall;

public class GameObjectGenerator {

	private static final int MIN_OBJ_ID = 1;
	private static final int MAX_OBJ_ID = 5;
	
	public static void generateGameObjects(Game game, Level level) {

		for(int x = game.getVisibility() /2; x < game.getRoadLength(); x++) {
			
			game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.obstacleFrequency());
			game.tryToAddObject(new Coin(game, x, game.getRandomLane()), level.coinFrequency());
			
			if (level.hasAdvancedObjects()) {
				game.tryToAddObject(new Wall(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Turbo(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				if (!SuperCoin.isPresent()) {
					game.tryToAddObject(new SuperCoin(game, x, game.getRandomLane()), level . advancedObjectsFrequency());
				}
				game.tryToAddObject(new Truck(game, x, game.getRandomLane()), level.advancedObjectsFrequency());
				game.tryToAddObject(new Pedestrian(game, x, 0), level.advancedObjectsFrequency());
			}
		}
	}
	
	public static void reset(Level level) {
		Obstacle.reset();
		Coin.reset();
		SuperCoin.reset();
	}

	public static void generateRuntimeObjects(Game game) {
		if (game.getLevel().hasAdvancedObjects()) {
			game.execute(new ThunderAction());
		}
	}
	
	public static void forceAdvanceObject(Game game, int id, int x) {
		GameObject o = null;
		switch (id) {
			case 1:
			o = new Wall(game, x, game.getRandomLane());
			break;
			case 2:
			o = new Turbo(game, x, game.getRandomLane());
			break;
			case 3:
			o = new SuperCoin(game, x, game.getRandomLane()); 
			break;
			case 4:
			o = new Truck(game, x, game.getRandomLane());
			break;
			case 5:
			o = new Pedestrian(game, x, 0);
			break;
		}

		for (int y = 0; y < game.getRoadWidth(); y++) {
			if (game.getObjectInPosition(x, y) != null) {
				game.getObjectInPosition(x, y).kill();
				game.update();
			}
		}
		game.forceAddObject(o);
	}
	
	public static int getMinId() {
		return MIN_OBJ_ID;
	}
	
	public static int getMaxId() {
		return MAX_OBJ_ID;
	}
}
