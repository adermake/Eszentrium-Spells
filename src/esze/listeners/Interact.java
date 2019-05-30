package esze.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Interact implements Listener{
	
	@EventHandler
    public void onPlayerDoorOpen(PlayerInteractEvent event)
    {
        Action action = event.getAction();
        org.bukkit.block.Block clicked = (org.bukkit.block.Block) event.getClickedBlock();
             
        //Left or Right click?
        
        if(event.getPlayer().getGameMode() != GameMode.CREATIVE){
        if ((action == Action.RIGHT_CLICK_BLOCK) || (action == Action.LEFT_CLICK_BLOCK)){
            //Door Block?
            if((clicked.getType() == Material.CHEST) ||
               (clicked.getType() == Material.TRAPPED_CHEST) ||
               (clicked.getType() == Material.ENDER_CHEST) ||
               (clicked.getType() == Material.HOPPER) ||
               (clicked.getType() == Material.DISPENSER) ||
               (clicked.getType() == Material.DROPPER) ||
               (clicked.getType() == Material.FURNACE) ||
               (clicked.getType() == Material.CRAFTING_TABLE) ||
               (clicked.getType() == Material.BEACON) ||
               (clicked.getType().toString().contains("TRAPDOOR")) || 
               (clicked.getType() == Material.LEVER) || 
               (clicked.getType().toString().contains("FENCE_GATE")) || 
               (clicked.getType() == Material.STONE_BUTTON) || 
               (clicked.getType().toString().contains("BED")) || 
               (clicked.getType().toString().contains("BUTTON"))){
                event.setCancelled(true);
            }
        }
        }
        
    }
	
	@EventHandler
	public void onKlick(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		PlayerInteractEvent IE = new PlayerInteractEvent(p, Action.RIGHT_CLICK_AIR, p.getInventory().getItemInMainHand(), null, null);
		Bukkit.getPluginManager().callEvent(IE);
		
		if(e.getRightClicked().getType() == EntityType.LLAMA) {
			e.setCancelled(true);
		}
		if(e.getRightClicked().getType() == EntityType.SHEEP) {
			p.addPassenger(e.getRightClicked());
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*14, 1));
		}
	}

}
