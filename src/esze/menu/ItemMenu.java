package esze.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;


public abstract class ItemMenu {

	private Inventory inventory;
	
	
	
	public ItemMenu(int size) {
		
		inventory = Bukkit.createInventory(null, size*9);		
		
		
	}
	
	
	public void addClickableItem(int gridX, int gridY, Material m, String iconname) {
		
		ItemMenuIcon is = new ItemMenuIcon(gridX,gridY,m,iconname,this);
		inventory.setItem((gridY-1)*9+gridX-1, is);
		
		
		
	}
	
	
	public abstract void clicked(ItemMenuIcon icon,Player p);
	
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	public void open(Player p) {
		p.openInventory(inventory);
	}
	
	
	
		
		
		
	
	
	
	
}
