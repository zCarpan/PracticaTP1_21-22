package es.ucm.tp1.logic.gameobjects;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.supercars.logic.Collider;

public class Player extends GameObject {

	
	private final static String symbol = ">";
	private final static String deathSymbol = "@";
	private final static int startingCoins = 5;
	public static final String info = "[Car] the racing car";
	
	private int coins = startingCoins;
	
	public Player(Game game) {
		super(game, 0, game.getRoadWidth() / 2);
	}
	@Override
	
	public String toString() {
		if (alive) return symbol;
		else return deathSymbol;
	}

	public void setPlayerX(int x) {
		this.x = x;
	}
	
	public boolean isInPosition(int x, int y) {
		if (this.x == x && this.y== y) 
			return true;
		return false;
	}
	public void setPlayerY(int y) {
		this.y = y;
	}
	
	private void moveDir(int dx, int dy) {
		
		setPlayerX(x + dx);

		
		if (game.isInRoadLimits(x + dx, y + dy))
			setPlayerY(y + dy);

	}
	
	public void move(int y) {
		doCollision();
		moveDir(1, y);
		doCollision();	
		game.updateAfterMove();
	}
		
	public void goUp() {
		move(-1);
	}
	
	public void goDown() {
		move(1);
	}
	
	public void goForward() {
		move(0);
	}
	

	public int getCoins() {
		return coins;
	}

	public void addCoins() {
		coins++;
	}
	
	public void subCoins() {
		coins--;
	}
	
	public void subCoins(int x) {
		coins -= x;
	}
	
	public void kill() {
		alive = false;
	}
	
	@Override 
	public boolean receiveWave() {
		return true;
	}
	
	@Override
	public boolean receiveCollision(Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
	}
	@Override
	public void onDelete() {
		// TODO Auto-generated method stub	
	}
	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x, y);
		if (other != null) 
			return other.receiveCollision (this);
		return false;
	}
	@Override
	public boolean receiveShoot() {
		// TODO Auto-generated method stub
		return false;
	}
	public void addCoins(int i) {
		coins += i;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub	
	}
	@Override
	public boolean receiveExplosion() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean receiveThunder() {
		// TODO Auto-generated method stub
		return false;
	}
				
	
	

	
}
