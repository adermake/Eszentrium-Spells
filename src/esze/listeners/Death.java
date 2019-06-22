package esze.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import esze.enums.GameType;
import esze.enums.Gamestate;
import esze.utils.ParUtils;

public class Death implements Listener {
	
	@EventHandler
	public void onDeath(EntityDamageEvent e){
		
			// TEST
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getHealth()<=0) {
				
			
			PlayerDeathEvent event = new PlayerDeathEvent(p, null, 0, "he dead");
			
			((LivingEntity) e.getEntity()).setHealth(20);
			
			
			
			ParUtils.createRedstoneParticle(e.getEntity().getLocation(), 0.3, 0.5, 0.3, 10, Color.RED, 3);
			GameType.getType().death(event);
			}
		}
		
	}

}
