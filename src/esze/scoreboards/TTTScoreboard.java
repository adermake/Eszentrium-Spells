package esze.scoreboards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import easyscoreboards.ScoreboardUtil;
import esze.enums.GameType;
import esze.enums.Gamestate;
import esze.main.main;
import esze.types.TypeSOLO;
import esze.types.TypeTTT;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class TTTScoreboard extends Scoreboard{
	
	@Override
	public void showScoreboard(){
		
		
		new BukkitRunnable() {
			
			public void run() {
				
				if(GameType.getType().name.equals("TTT")){
					if(Gamestate.getGameState() == Gamestate.INGAME){
						HashMap<String, Integer> players = new HashMap<String, Integer>();
						
						TypeTTT game = ((TypeTTT)GameType.getType());
						for (Player p : game.players) {
							
							for (Player other : game.players) {
								
								
							}
							
						}
						
						
						ScoreboardUtil.rankedSidebarDisplay(game.innocent, "§aUnschuldig", players);
						ScoreboardUtil.rankedSidebarDisplay(game.traitor, "§cVerräter", players);
						
						if (hide) {
							this.cancel();
							hide = false;
						}
					}
				}
				
				
			}
		}.runTaskTimer(main.plugin, 0, 10);
		
				
		
	}
	
	
	
}
