package esze.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import esze.enums.Gamestate;

public class Damage implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			Bukkit.broadcastMessage(""+p.getNoDamageTicks());
			if(Gamestate.getGameState() == Gamestate.LOBBY){
				Bukkit.broadcastMessage("RALF HAT RECHt");
				e.setCancelled(true);
			}else if(Gamestate.getGameState() == Gamestate.INGAME){
				if(e.getCause().equals( DamageCause.FALL)){
					e.setCancelled(true);
				}
			}
			
			
		}
	}

}
