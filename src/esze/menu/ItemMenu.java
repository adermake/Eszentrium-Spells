package esze.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class ItemMenu {

	private Inventory inventory;
	
	
	
	public ItemMenu(int size) {
		
		inventory = Bukkit.createInventory(null, size*9);		
		
		
	}
	
	public ItemMenu(int size,String name) {
		
		inventory = Bukkit.createInventory(null, size*9,name);		
		
		
	}
	public void addClickableItem(int gridX, int gridY, Material m, String iconname) {
		
		ItemMenuIcon is = new ItemMenuIcon(gridX,gridY,m,iconname,this);
		inventory.setItem((gridY-1)*9+gridX-1, is);
		
		
		
	}
	public void addClickableItem(int gridX, int gridY, Material m, String iconname,String l) {
		
		ItemMenuIcon is = new ItemMenuIcon(gridX,gridY,m,iconname,this);
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		lore.add(l);
		im.setLore(lore);
		is.setItemMeta(im);
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
