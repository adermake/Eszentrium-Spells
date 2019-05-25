package esze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import esze.enums.Gamestate;
import esze.main.main;

public class Damage implements Listener{
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Gamestate.getGameState() == Gamestate.LOBBY){
				e.setCancelled(true);
			}else if(Gamestate.getGameState() == Gamestate.INGAME){
				// Analytics
				if (e.getCause().equals( DamageCause.ENTITY_ATTACK) || e.getCause().equals( DamageCause.ENTITY_SWEEP_ATTACK)) {
					main.damageCause.remove(p);
					main.damageCause.put(p, "Schwert-"); //TODO: add the person who punched  
				}
				if(e.getCause().equals( DamageCause.FALL)){
					e.setCancelled(true);
				}
			}
		}
	}

}
