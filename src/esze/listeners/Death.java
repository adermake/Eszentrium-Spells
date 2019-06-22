package esze.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import esze.enums.GameType;
import esze.enums.Gamestate;
import esze.utils.ParUtils;

public class Death implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		
			e.getEntity().setHealth(20);
			Bukkit.broadcastMessage("Hea"+e.getEntity().getHealth());
			e.setDeathMessage("");
			ParUtils.createRedstoneParticle(e.getEntity().getLocation(), 0.3, 0.5, 0.3, 10, Color.RED, 3);
			GameType.getType().death(e);
	}

}
