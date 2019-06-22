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
		Bukkit.broadcastMessage("a");
			// TEST
		if (e.getEntity() instanceof Player) {
			Bukkit.broadcastMessage("b");
			Player p = (Player) e.getEntity();
			Bukkit.broadcastMessage("p.getHealth() = "+p.getHealth());
			Bukkit.broadcastMessage("e.getFinalDamage() = "+e.getFinalDamage());
			Bukkit.broadcastMessage("^DIFFERENCE^ = "+(p.getHealth() - e.getFinalDamage()));
			
			if (p.getHealth() - e.getFinalDamage() < 1 ) {
				Bukkit.broadcastMessage("c");
				
			
			PlayerDeathEvent event = new PlayerDeathEvent(p, null, 0, "he dead");
			Bukkit.broadcastMessage("d");
			

			e.setCancelled(true);
			p.setHealth(20);
			Bukkit.broadcastMessage("e");
			
			
			
			ParUtils.createRedstoneParticle(e.getEntity().getLocation(), 0.3, 0.5, 0.3, 10, Color.RED, 3);
			Bukkit.broadcastMessage("f");
			GameType.getType().death(event);
			Bukkit.broadcastMessage("g");
			}
		}
		
	}

}
