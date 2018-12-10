package eszeRemastered.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;


public class ItemMenu {

	private Inventory inventory;
	
	
	
	public ItemMenu(int size) {
		
		inventory = Bukkit.createInventory(null, size*9);		
		
		
	}
	
	
	public void addClickableItem(int gridX, int gridY, Material m, String iconname) {
		
		ItemMenuIcon is = new ItemMenuIcon(gridX,gridY,m,iconname,inventory);
		
		
		
		
	}
	
	
	
	
	
	
}
