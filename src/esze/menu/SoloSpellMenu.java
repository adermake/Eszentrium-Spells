package esze.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import esze.main.SpellList;
import esze.utils.ItemStackUtils;
import esze.utils.NBTUtils;
import spells.spellcore.Spell;

public class SoloSpellMenu extends ItemMenu{

	boolean used = false;
	public SoloSpellMenu() {
		super(1,"spellmenu");
		ArrayList<Spell> spells = SpellList.getDiffrentRandom(4);
		addClickableItem(2, 1, Material.ENCHANTED_BOOK, spells.get(0).getName());
		addClickableItem(4, 1, Material.ENCHANTED_BOOK, spells.get(1).getName());
		addClickableItem(6, 1, Material.ENCHANTED_BOOK, spells.get(2).getName());
		addClickableItem(8, 1, Material.ENCHANTED_BOOK, spells.get(3).getName());
	}

	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		if (used)
			return;
		
		used = true;
		p.closeInventory();
		ItemStack is = NBTUtils.setNBT("Spell", "true", icon);
		p.getInventory().addItem(is);
		p.setNoDamageTicks(10);
		p.setVelocity(p.getVelocity().setY(1));
	}

	

}
