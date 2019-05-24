package esze.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import esze.analytics.solo.SaveEsze;
import esze.analytics.solo.SaveSelection;
import esze.analytics.solo.SaveUtils;
import esze.utils.NBTUtils;
import esze.utils.PlayerUtils;
import spells.spellcore.Spell;
import spells.spellcore.SpellList;

public class SoloSpellMenu extends ItemMenu{

	boolean used = false;
	private ArrayList<Spell> spells;
	
	public SoloSpellMenu() {
		super(1,"spellmenu");
		spells = SpellList.getDiffrentRandom(4);
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
		PlayerUtils.showPlayer(p);
		
		SaveUtils.addPlayerSelection(p.getName(), new SaveSelection(icon.getName(), spells.get(0).getName(),
				spells.get(1).getName(), spells.get(2).getName(), spells.get(3).getName())); //Analytics
	}

	

}
