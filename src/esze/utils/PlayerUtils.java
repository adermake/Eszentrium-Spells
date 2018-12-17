package esze.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;

public class PlayerUtils {
	
	
	
	
	
	public static void hidePlayer(Player p, int ammount) {
		
		hidePlayer(p);
		new BukkitRunnable() {
			public void run() {
				showPlayer(p);
			}
		}.runTaskLater(main.plugin,ammount);
	}
	
	
	public static void hidePlayer(Player p) {
		
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.hidePlayer(main.plugin, p);
		}
		
	}
	
	public static void showPlayer(Player p) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.showPlayer(main.plugin, p);
		}
	}
}
