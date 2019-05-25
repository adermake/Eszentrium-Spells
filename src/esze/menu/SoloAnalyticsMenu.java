package esze.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import esze.analytics.solo.SaveUtils;

public class SoloAnalyticsMenu extends ItemMenu {
	
	public SoloAnalyticsMenu(Player p) {
		super(4,"spellmenu");
		addClickableItem(1, 1, Material.EMERALD, "Siegesrate: " + SaveUtils.getSaveEsze().getVictories(p.getName()));
		addClickableItem(2, 1, Material.REDSTONE, "Verlierrate: " + SaveUtils.getSaveEsze().getLosses(p.getName()));
		addClickableItem(3, 1, Material.DIAMOND_SWORD, "Tötungen: " + SaveUtils.getSaveEsze().getKills(p.getName()));
		addClickableItem(3, 1, Material.STONE_SWORD, "Tode: " + SaveUtils.getSaveEsze().getDeaths(p.getName()));
	}

	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		// TODO Auto-generated method stub
		
	}

}
