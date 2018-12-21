package esze.types;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import esze.menu.SoloSpellMenu;
import esze.scoreboards.SoloScoreboard;
import esze.utils.PlayerUtils;
import esze.utils.Title;

public class TypeSOLO extends Type{
	public HashMap<Player, Integer> lives = new HashMap<Player,Integer>();
	public TypeSOLO() {
		name = "SOLO";
	}
	
	@Override
	public void runEverySecond() {
		for (Player p : players) {
			if (p.getLocation().getY()<60) {
				p.damage(p.getHealth());
			}
			
			
		}
	}
	
	@Override
	public void runEveryTick() {
		
	}
	
	@Override
	public void gameStart() {
		
		SoloScoreboard scoreBoard = new SoloScoreboard();
		scoreBoard.scoreboard();
		for (Player p : players) {
				p.teleport(nextLoc());
				PlayerUtils.hidePlayer(p,100);
				p.setNoDamageTicks(100);
				SoloSpellMenu s = new SoloSpellMenu();
				s.open(p);
				lives.put(p, 4);
			}
		new SoloScoreboard();
		
	}
	
	@Override
	public void death(PlayerDeathEvent event) {
		Player p = event.getEntity();
		Bukkit.broadcastMessage("DIE!!");
		loseLife(p);
		
		
		
	}

	
	public void loseLife(Player p) {
		lives.put(p, lives.get(p)-1);
		
		
		if (lives.get(p) < 1) {
			out(p);
			checkWinner();
		}
		else {
			
			p.setNoDamageTicks(100);
			PlayerUtils.hidePlayer(p);
			p.teleport(nextLoc());
			SoloSpellMenu s = new SoloSpellMenu();
			s.open(p);
		}
		
		
		
	}
	public void checkWinner() {
		if (players.size()<=1) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				
			
			for (Player winner : players) {
				Title t = new Title(winner+" hat gewonnen!");
				t.send(p);
			}
			}
		}
			
	}
	
}
