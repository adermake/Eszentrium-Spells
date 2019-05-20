package esze.types;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jflac.io.RandomFileInputStream;

import esze.enums.Gamestate;
import esze.main.LobbyBackgroundRunnable;
import esze.main.main;
import esze.menu.SoloSpellMenu;
import esze.scoreboards.SoloScoreboard;
import esze.utils.ItemStackUtils;
import esze.utils.LobbyUtils;
import esze.utils.MathUtils;
import esze.utils.PlayerUtils;
import esze.utils.Title;

public class TypeTTT extends Type{
	boolean gameOver = false;
	public ArrayList<Player> innocent = new ArrayList<Player>();
	public ArrayList<Player> traitor = new ArrayList<Player>();
	public HashMap<Player,Player> foundBody = new HashMap<Player,Player>();
	
	public TypeTTT() {
		name = "TTT";
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
		
		int playerCount = players.size();
		int traitorCount = 1;
		
		
		for (Player p : players) {
			p.teleport(nextLoc());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.getInventory().addItem(ItemStackUtils.createItemStack(Material.WOODEN_SWORD, 1, 0, "§eHolz-Schwert", null, true));
			
			
			
			
			
		}
		
		traitorCount += ((int)playerCount/3)-1;
		
		
		for (int i = 0;i<traitorCount;i++) {
			int index = MathUtils.randInt(0, players.size());
			setTraitor(players.get(index));
		}
		
		for (Player p : players) {
			if (!traitor.contains(p))
				setInnocent(p);
		}
		
		/*
		scoreboard = new SoloScoreboard();
		scoreboard.showScoreboard();
		for (Player p : players) {
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
		*/
		
	}
	
	@Override
	public void death(PlayerDeathEvent event) {
		Player p = event.getEntity();
		p.setHealth(p.getMaxHealth());
		players.remove(p);
		p.setGameMode(GameMode.ADVENTURE);
		checkWinner();
		
		
	}
	
	
	public void setTraitor(Player p) {
		
		p.sendMessage("§7Du bist der §4Verräter");
		traitor.add(p);
	}
	public void setInnocent(Player p) {
		p.sendMessage("§7Du bist §aunschuldig");
		innocent.add(p);
	}
	
	boolean won = false;
	public void checkWinner() {
		if (!won) {
			
		
		if (innocent.isEmpty() && !gameOver) {
			
			scoreboard.hideScoreboard();
			
			
			
			for (Player p : Bukkit.getOnlinePlayers()) {
			
			
				Title t = new Title("§7Die §4Verräter §7haben gewonnen!");
				won = true;
				t.send(p);
				
			
			}
			
			
			
		}
		
		if (traitor.isEmpty() && !gameOver) {
			
			scoreboard.hideScoreboard();
			
			
			
			for (Player p : Bukkit.getOnlinePlayers()) {
			
			
				Title t = new Title("§7Die §aUnschuldigen §7haben gewonnen!");
				won = true;
				t.send(p);
				
			
			}
			
			
			
		}
			
		if (won && !gameOver) {
			Bukkit.broadcastMessage("END");
			Gamestate.setGameState(Gamestate.LOBBY);
			LobbyBackgroundRunnable.start();
			LobbyUtils.recallAll();
			scoreboard.hide = true;
			gameOver = true;
			players.clear();
			
		}
		
		
	}
	}
	
}
