package esze.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import esze.analytics.solo.SaveUtils;

public class SoloAnalyticsMenu extends ItemMenu {
	
	public SoloAnalyticsMenu(Player p) {
		super(4,"spellmenu");
		init(p.getName());
	}

	public SoloAnalyticsMenu(String string) {
		super(4,"spellmenu");
		init(string);
	}

	public void init(String s) {
		
		addClickableItem(1, 1, Material.EMERALD, "Siegesrate: " + SaveUtils.getSaveEsze().getVictories(s));
		addClickableItem(2, 1, Material.REDSTONE, "Verlierrate: " + SaveUtils.getSaveEsze().getLosses(s));
		addClickableItem(3, 1, Material.DIAMOND_SWORD, "Tötungen: " + SaveUtils.getSaveEsze().getKills(s));
		addClickableItem(4, 1, Material.STONE_SWORD, "Tode: " + SaveUtils.getSaveEsze().getDeaths(s));
	}
	
	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		// TODO Auto-generated method stub
		
	}

}
