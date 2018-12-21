package esze.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Menu implements Listener {

	
	@EventHandler
	public void omMenu(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		Bukkit.broadcastMessage("YE");
		if (e.getCurrentItem().hasItemMeta()) {
			Bukkit.broadcastMessage("EHA");
			ItemMenuIcon.ditributeClicks(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory(),p);
		}
			
		
		
		
		
	}
	
	
	
}
