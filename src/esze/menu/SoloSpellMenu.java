package esze.menu;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import esze.main.SpellList;
import esze.utils.ItemStackUtils;
import spells.spellcore.Spell;

public class SoloSpellMenu extends ItemMenu{

	public SoloSpellMenu() {
		super(1);
		ArrayList<Spell> spells = SpellList.getDiffrentRandom(4);
		addClickableItem(1, 2, Material.ENCHANTED_BOOK, spells.get(0).getName());
		addClickableItem(1, 4, Material.ENCHANTED_BOOK, spells.get(1).getName());
		addClickableItem(1, 6, Material.ENCHANTED_BOOK, spells.get(2).getName());
		addClickableItem(1, 8, Material.ENCHANTED_BOOK, spells.get(3).getName());
	}

	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		
		p.closeInventory();
		p.getInventory().addItem(ItemStackUtils.createItemStack(Material.ENCHANTED_BOOK, 1, 0, icon.getName(), null, false));
		p.setNoDamageTicks(10);
		p.setVelocity(p.getVelocity().setY(1));
	}

	

}
