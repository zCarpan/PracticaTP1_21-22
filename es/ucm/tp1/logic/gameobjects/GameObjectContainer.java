package es.ucm.tp1.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer {


	private List<GameObject> gameObjectContainer;

	
	public GameObjectContainer() {
		gameObjectContainer = new ArrayList<GameObject>();
	}

	public GameObject getContainerObjectInPosition(int x, int y) {
		for(int i = 0; i< gameObjectContainer.size(); i++) {
			if (x == gameObjectContainer.get(i).getX() && y== gameObjectContainer.get(i).getY()) {
				return gameObjectContainer.get(i);
			}
		}
		return null;

	}
	
	public void removeGameObjects() {
		for(int i = 0; i < gameObjectContainer.size(); i++) {
			if (!gameObjectContainer.get(i).isAlive()) {
				gameObjectContainer.get(i).onDelete();
				gameObjectContainer.remove(i);
				i--;
			}
		}
	}

	public void clearObjects(int x, int y) {
		GameObject object = getContainerObjectInPosition(x, y);
		if(object != null)
			object.kill();
	}
	
	public void generate(GameObject g) {
		gameObjectContainer.add(g);
	}
	
	public void update() {
		for(int i = 0; i < gameObjectContainer.size(); i++) 
			gameObjectContainer.get(i).update();
	}

	public String positionToString(int x, int y) {
		String s = "";
		for(int i = 0; i < gameObjectContainer.size(); i++) {
			if (x == gameObjectContainer.get(i).getX() && y== gameObjectContainer.get(i).getY() && gameObjectContainer.get(i) != null && gameObjectContainer.get(i).isAlive()) {
				s +=  gameObjectContainer.get(i).toString();
			}
		}
		return s;
	}
}

