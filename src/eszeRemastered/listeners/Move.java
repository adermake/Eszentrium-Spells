package eszeRemastered.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import eszeRemastered.enums.Gamestate;
import eszeRemastered.main.main;
import eszeRemastered.players.PlayerAPI;
import eszeRemastered.players.PlayerInfo;

public class Move implements Listener{
	
	@EventHandler
	public void onVoid(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.SURVIVAL){
			if(Gamestate.getGameState() == Gamestate.INGAME){
				if(e.getTo().getBlockY() <= 60){
					PlayerInfo pi = PlayerAPI.getPlayerInfo(p);
					pi.damageVoid();
				}
			}else{
				if(e.getTo().getBlockY() <= 60){
					p.teleport((Location) main.plugin.getConfig().get("lobby.loc"));
				}
			}
		}
	}

}
