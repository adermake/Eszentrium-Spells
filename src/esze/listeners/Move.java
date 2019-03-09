package esze.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import esze.enums.Gamestate;
import esze.main.main;
import esze.players.PlayerAPI;
import esze.players.PlayerInfo;

public class Move implements Listener{
	
	@EventHandler
	public void onVoid(PlayerMoveEvent e){
		Player p = e.getPlayer();
		
			if(Gamestate.getGameState() == Gamestate.INGAME){
				if(e.getTo().getBlockY() <= 60){
					
					PlayerInfo pi = PlayerAPI.getPlayerInfo(p);
					pi.damageVoid();
				}
			}else{
				if(e.getTo().getBlockY() <= 60){
					if (p.getGameMode() != GameMode.CREATIVE)
					p.teleport((Location) main.plugin.getConfig().get("lobby.loc"));
				}
			}
		
	}

}
