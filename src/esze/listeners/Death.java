package esze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import esze.enums.GameType;
import esze.enums.Gamestate;

public class Death implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(Gamestate.getGameState() == Gamestate.INGAME){
			GameType.getType().death(e);
		}
	}

}
