package esze.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import easyscoreboards.ScoreboardUtil;
import esze.enums.GameType;
import esze.enums.Gamestate;
import esze.players.PlayerAPI;
import esze.utils.Actionbar;

public class LobbyBackgroundRunnable {
	
	private static int runnableID;
	
	public static void start(){
		runnableID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.plugin, new Runnable() {
			
			@Override
			public void run() {
				
				for(Player p : Bukkit.getOnlinePlayers()){
					ArrayList<String> board = new ArrayList<String>();
					
					board.add("§2Spielinfos");
					board.add(" ");
					board.add("§a§lSpielmodus:");
					board.add(GameType.getType().name);
					board.add("  ");
					board.add("§a§lSpieler:");
					board.add(Bukkit.getOnlinePlayers().size()+"/"+Bukkit.getMaxPlayers());
					
					
					String[] array = new String[board.size()];
					int i=0;
					for(String s : board){
						array[i++] = s;
					}
					
					ScoreboardUtil.unrankedSidebarDisplay(p, array);
					
				}
				
			}
		}, 0, 20);
	}
	
	public static void stop(){
		Bukkit.getScheduler().cancelTask(runnableID);
	}
	
}
