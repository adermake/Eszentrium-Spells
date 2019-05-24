package esze.types;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.analytics.solo.SaveUtils;
import esze.enums.Gamestate;
import esze.main.LobbyBackgroundRunnable;
import esze.main.main;
import esze.menu.SoloSpellMenu;
import esze.scoreboards.SoloScoreboard;
import esze.utils.ItemStackUtils;
import esze.utils.LobbyUtils;
import esze.utils.PlayerUtils;
import esze.utils.Title;

public class TypeSOLO extends Type{
	boolean gameOver = false;
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
		
		won = false;
		gameOver = false;
		
		scoreboard = new SoloScoreboard();
		scoreboard.showScoreboard();
		for (Player p : players) {
				SaveUtils.addPlayer(p.getName()); //Analytics
				p.teleport(nextLoc());
				p.setGameMode(GameMode.SURVIVAL);
				p.getInventory().clear();
			
					p.getInventory().addItem(ItemStackUtils.createItemStack(Material.WOODEN_SWORD, 1, 0, "§eHolz-Schwert", null, true));
				
				PlayerUtils.hidePlayer(p,100);
				p.setNoDamageTicks(100);
				SoloSpellMenu s = new SoloSpellMenu();
				s.open(p);
				lives.put(p, 4);
			}
		setupJumpPad(currentmap);
		new SoloScoreboard();
		
	}
	
	@Override
	public void death(PlayerDeathEvent event) {
		Player p = event.getEntity();
		SaveUtils.addPlayerDeath(p.getName(), ""); //Analytics TODO: Add cause of death
		p.setHealth(p.getMaxHealth());
		loseLife(p);
		checkWinner();
		
		
	}

	
	public void loseLife(Player p) {
		lives.put(p, lives.get(p)-1);
		
		p.setVelocity(new Vector(0,0,0));
		if (lives.get(p) < 1) {
			out(p);
			checkWinner();
		}
		else {
			
			
			PlayerUtils.hidePlayer(p,100);
			p.setNoDamageTicks(100);
			p.teleport(nextLoc());
			SoloSpellMenu s = new SoloSpellMenu();
			new BukkitRunnable() {
				public void run() {
					s.open(p);
				}
			}.runTaskLater(main.plugin, 2);
		}
		
		
		
	}
	boolean won = false;
	public void checkWinner() {
		if (!won) {
			
		
		if (players.size()<=1 && !gameOver) {
			
			scoreboard.hideScoreboard();
			gameOver = true;
			
			
			for (Player p : Bukkit.getOnlinePlayers()) {
			
			
			for (Player winner : players) {
				Title t = new Title("§a"+winner.getName()+" hat gewonnen!");
				won = true;
				t.send(p);
				
			}
			
			}
			
		if (won) {
			Bukkit.broadcastMessage("END");
			Gamestate.setGameState(Gamestate.LOBBY);
			LobbyBackgroundRunnable.start();
			LobbyUtils.recallAll();
			scoreboard.hide = true;
			players.clear();
			
		}
		
		}
	}
	}
	
}
