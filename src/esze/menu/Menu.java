package esze.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class Menu implements Listener {

	
	@EventHandler
	public void omMenu(InventoryClickEvent e) {
		if(e == null)
			return;
		
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem().hasItemMeta()) {
			ItemMenuIcon.ditributeClicks(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory(),p);
		}
			
		
		
		
		
	}
	
	@EventHandler
	public void inventoryCloseEvent(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals("spellmenu")) {
			ItemMenuIcon.ditributeClicks(e.getInventory().getItem(1).getItemMeta().getDisplayName(), e.getInventory(),(Player) e.getPlayer());
			
		}
	}
}
