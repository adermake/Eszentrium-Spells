package esze.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import esze.utils.NBTUtils;
import spells.spellcore.Spell;
import spells.spellcore.SpellList;

public class TraitorshopMenu extends ItemMenu {

	public TraitorshopMenu() {
		super(1, "§cSchwarzmarkt");
		
		addClickableItem(1, 1, Material.SPECTRAL_ARROW, "§4Magmanadel");
		addClickableItem(2, 1, Material.SHEEP_SPAWN_EGG, "§4Verzaubern");
		addClickableItem(3, 1, Material.STRING, "§4Griff der sieben Winde");
		addClickableItem(4, 1, Material.MELON_SLICE, "§4Miiilone");
		
	}

	
	/*
	public static int calcTraitorMenuSize() {
		int spellCount = SpellList.traitorSpells.size();
		int size = 0;
		
		while (size<spellCount) {
			size+=9;
		}
		return size;
	}
	*/
	
	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		
		if (icon.getName().equals("§4Magmanadel")) {
			buyItem(p,icon,8);
		}
		if (icon.getName().equals("§4Verzaubern")) {
			buyItem(p,icon,6);
		}
		if (icon.getName().equals("§4Griff der sieben Winde") ) {
			buyItem(p,icon,6);
		}
		if (icon.getName().equals("§4Miiilone")) {
			buyItem(p,icon,4);
		}
	}
	
	public void buyItem(Player p,ItemMenuIcon icon,int price) {
		if (p.getLevel()>= price) {
			
		
			ItemStack is= new ItemStack(Material.BOOK);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(icon.getName());
		
			is.setItemMeta(im);
			is = NBTUtils.setNBT("Spell", "true", is);
			p.getInventory().addItem(is);
			p.setLevel(p.getLevel()-price);
		}
	}
		
	

}
