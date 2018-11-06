package eszeRemastered.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import eszeRemastered.enums.GameType;
import eszeRemastered.enums.Gamestate;

public class Death implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(Gamestate.getGameState() == Gamestate.INGAME){
			GameType.getType().death(e);
		}
	}

}
