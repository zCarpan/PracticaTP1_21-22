package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.exceptions.CommandExecuteException;
import es.ucm.tp1.exceptions.CommandParseException;
import es.ucm.tp1.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.logic.Game;

public interface Buyable {
	
	public static final String NOT_ENOUGH_COINS_MSG = "Player doesnt have enough coins";
	
	public int cost();
	
	public default void buy(Game game) throws NotEnoughCoinsException{
		
		if(game.pGetCoins() >= cost()) 
			game.pSubCoin(cost());
		else
			throw new NotEnoughCoinsException(String.format(NOT_ENOUGH_COINS_MSG));
		
	}
	
}
