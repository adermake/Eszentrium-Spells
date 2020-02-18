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
		super(6,colorTag+"Spells :-)");
		// TODO Auto-generated constructor stub
		int x = 1;
		int y = 1;
		for (Spell s : SpellList.spells.keySet()) {
			if (!s.getName().contains(colorTag)) {
				continue;
			}
			ArrayList<String> lore = new ArrayList<String>();
			//lore.add( "Tötungen: "  + SaveUtils.getSaveEsze().getSpellKills(p.getName(), SaveUtils.rmColor(s.getName())) );
			//lore.add( "Tode: "  + SaveUtils.getSaveEsze().getSpellDeaths(p.getName(), SaveUtils.rmColor(s.getName())) );
			//lore.add( "Deine Pickrate: "  + SaveUtils.getSaveEsze().getSpellWorth(p.getName(), SaveUtils.rmColor(s.getName())) + "%" );
			//lore.add( "Metaichkeit: "  + SaveUtils.getSaveEsze().getWorth(SaveUtils.rmColor(s.getName())) + "%" );
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

	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		// TODO Auto-generated method stub
		
	}

}
