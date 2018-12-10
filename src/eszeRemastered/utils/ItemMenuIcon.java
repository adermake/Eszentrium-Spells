package eszeRemastered.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMenuIcon extends ItemStack{

	private int gridX;
	private int gridY;
	private Inventory inventory;
	

	
	


	public ItemMenuIcon(int gridX, int gridY, Material m, String iconname,Inventory i) {
		
		super(m);
		inventory = i;
		ItemMeta im = this.getItemMeta();
		im.setDisplayName(iconname);
		this.setItemMeta(im);
		
		
	}
	
	public void onClick() {
		
		
		
	}
	
	
	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public Inventory getInventory() {
		return inventory;
	}

	
	
	
	
}
