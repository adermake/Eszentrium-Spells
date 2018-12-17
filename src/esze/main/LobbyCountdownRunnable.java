package esze.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import esze.enums.GameType;
import esze.enums.Gamestate;
import esze.map.MapSelect;
import esze.players.PlayerAPI;
import esze.utils.Actionbar;

public class LobbyCountdownRunnable {
	
	private static int timeLeft = 15;
	private static int runnableID;
	
	public static void start(){
		final int timeDefault = timeLeft;
		runnableID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main.plugin, new Runnable() {
			
			@Override
			public void run() {
				
				String timeShow = calculatePercent(15, timeDefault, timeLeft);
				new Actionbar(timeShow).sendAll();
				
				
				
				if(timeLeft == 0){
					for(Player p : Bukkit.getOnlinePlayers()){
						PlayerAPI.addPlayer(p);
					}
					Gamestate.setGameState(Gamestate.INGAME);
					new Actionbar("").sendAll();
					
					GameType.getType().gameStart();
					
					timeLeft = timeDefault;
					stop();
				}
				
				timeLeft--;
				
			}
		}, 0, 20);
	}
	
	public static void stop(){
		Bukkit.getScheduler().cancelTask(runnableID);
		
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getGameMode() != GameMode.SPECTATOR) {
				GameType.getType().players.add(p);
			}
		}
		GameType.getType().currentmap = MapSelect.maxVotes();
		GameType.getType().gameStart();
		
		
		
		
	}
	
	public static String calculatePercent(int bars, int FULL, int LEFT){
		//ACTIONBAR
		String tosend = "Spielstart ";
		double bar = Math.round(FULL/bars);
		double philled = Math.round(LEFT/bar); 
		
		String s = "";
		
		for(int i = 0; i < philled; i++){
			s+="§e▋";
		}
		
		for(int i = 0; i < bars - philled; i++){
			s+="§7▋";
		}
	    tosend = tosend + s + " §r" + LEFT + " §eSekunden";
		return tosend;
	}

}