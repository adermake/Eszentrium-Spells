package esze.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import esze.analytics.solo.SaveUtils;
import spells.spellcore.Spell;
import spells.spellcore.SpellList;

public class SpellAnalyticsMenu extends ItemMenu {

	public SpellAnalyticsMenu(String colorTag,Player p) {
		super(6,colorTag+"Spells    ");
		// TODO Auto-generated constructor stub
		int x = 1;
		int y = 1;
		for (Spell s : SpellList.spells.keySet()) {
			if (!s.getName().contains(colorTag)) {
				continue;
			}
			ArrayList<String> lore = new ArrayList<String>();
			String number = "§e";
			String text = "§r";
			lore.add( text + "Tötungen: "  					+ number + cut(SaveUtils.getSaveEsze().getSpellKills(p.getName(), SaveUtils.rmColor(s.getName())) ) );
			lore.add( text + "Tode: "  						+ number + cut(SaveUtils.getSaveEsze().getSpellDeaths(p.getName(), SaveUtils.rmColor(s.getName())) ) );
			lore.add( text + "Deine Auswahlrate: "  		+ number + cut(SaveUtils.getSaveEsze().getSpellWorth(p.getName(), SaveUtils.rmColor(s.getName())) ) + "%" );
			lore.add( text + "Allgemeine Auswahlrate: "  	+ number + cut(SaveUtils.getSaveEsze().getWorth(SaveUtils.rmColor(s.getName())) ) + "%" );
			addClickableItem(x, y, Material.BOOK, s.getName(), lore);
			x++;
			if (x>9) {
				y++;
				x=1;
			}
			
		}
		
		for (Spell s : SpellList.traitorSpells) {
			if (!s.getName().contains(colorTag))
				continue;
			addClickableItem(x, y, Material.BOOK, s.getName());
			x++;
			if (x>9) {
				y++;
				x=1;
			}
			
		}
	}
	
	public String cut(double d) {
		String s = "" + d;
		int max = 4;
		if (s.length() > max) {
			s.subSequence(0, max);
		}
		return s;
	}

	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		// TODO Auto-generated method stub
		
	}

}
