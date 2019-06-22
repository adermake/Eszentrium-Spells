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
import esze.scoreboards.TTTScoreboard;
import esze.utils.ItemStackUtils;
import esze.utils.LobbyUtils;
import esze.utils.MathUtils;
import esze.utils.PlayerUtils;
import esze.utils.Title;
import spells.spellcore.Spelldrop;

public class TypeTTT extends Type{
	boolean gameOver = false;
	public ArrayList<Player> innocent = new ArrayList<Player>();
	public ArrayList<Player> traitor = new ArrayList<Player>();
	public HashMap<Player,Player> foundBody = new HashMap<Player,Player>();
	
	public TypeTTT() {
		name = "TTT";
	}
	
	int sec = 0;
	@Override
	public void runEverySecond() {
		for (Player p : players) {
			if (p.getLocation().getY()<60) {
				p.damage(p.getHealth());
			}
			
			
		}
		sec++;
		if (sec>10) {
			spawnNewSpell();
			sec = 0;
		}
	}
	
	@Override
	public void runEveryTick() {
		
	}
	
	@Override
	public void gameStart() {
		
		innocent.clear();
		traitor.clear();
		
		won = false;
		gameOver = false;
		
		int playerCount = players.size();
		int traitorCount = 1;
		
		
		scoreboard = new TTTScoreboard();
		scoreboard.showScoreboard();
		
		PlayerUtils.showAllPlayers();
		
		for (Player p : players) {
			p.teleport(nextLoc());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.getInventory().addItem(ItemStackUtils.createItemStack(Material.WOODEN_SWORD, 1, 0, "§eHolz-Schwert", null, true));
			
			
			
			
			
		}
		
		if ( ((int)playerCount/3)-1 > 0)
		traitorCount += ((int)playerCount/3)-1;
		
		
		for (int i = 0;i<traitorCount;i++) {
			int index = MathUtils.randInt(0, players.size()-1);
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
		Bukkit.broadcastMessage("1");
		p.setHealth(20);
		players.remove(p);
		p.setGameMode(GameMode.ADVENTURE);
		Bukkit.broadcastMessage("2");
		PlayerUtils.hidePlayer(p);
		
		if (innocent.contains(p))
			innocent.remove(p);
		if (traitor.contains(p))
			traitor.remove(p);
		Bukkit.broadcastMessage("3");
		
		p.setNoDamageTicks(100);
		p.teleport(p.getLocation());
		checkWinner();
		
		
	}
	
	public void spawnNewSpell() {
		Location loc = nextLoc();
		loc.add(MathUtils.randInt(-30, 30),MathUtils.randInt(-10, 30),MathUtils.randInt(-30, 30));
		
		while (!loc.getBlock().getType().isSolid())  {
			loc.add(0,-1,0);
			if (loc.getY()< 60 ) {
				spawnNewSpell();
				return;
				
			}
		}
		while (loc.clone().add(0,1,0).getBlock().getType().isSolid())  {
			loc.add(0,1,0);
			if (loc.getY()> 200 ) {
				spawnNewSpell();
				return;
				
			}
		}
		new Spelldrop(loc);
	
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
			
			Bukkit.broadcastMessage("4");
		if (innocent.isEmpty() && !gameOver) {
			
			scoreboard.hideScoreboard();
			
			Bukkit.broadcastMessage("5");
			
			for (Player p : Bukkit.getOnlinePlayers()) {
			
			
				Title t = new Title("§4Verräter");
				t.setSubtitle("§7haben gewonnen!");
				won = true;
				t.send(p);
				
			
			}
			
			
			
		}
		Bukkit.broadcastMessage("5");
		if (traitor.isEmpty() && !gameOver) {
			
			scoreboard.hideScoreboard();
			
			
			
			for (Player p : Bukkit.getOnlinePlayers()) {
			
			

				Title t = new Title("§aUnschuldigen");
				t.setSubtitle("§7haben gewonnen!");
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
