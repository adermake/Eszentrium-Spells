package esze.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.server.v1_14_R1.Particles;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jflac.io.RandomFileInputStream;

import com.google.common.base.Preconditions;

import esze.enums.Gamestate;
import esze.main.GameRunnable;
import esze.main.LobbyBackgroundRunnable;
import esze.main.main;
import esze.menu.SoloSpellMenu;
import esze.scoreboards.SoloScoreboard;
import esze.scoreboards.TTTScoreboard;
import esze.utils.ItemStackUtils;
import esze.utils.LobbyUtils;
import esze.utils.MathUtils;
import esze.utils.ParUtils;
import esze.utils.PlayerUtils;
import esze.utils.TTTCorpse;
import esze.utils.TabList;
import esze.utils.Title;
import spells.spellcore.DamageCauseContainer;
import spells.spellcore.Spell;
import spells.spellcore.Spelldrop;

public class TypeTTT extends Type{
	boolean gameOver = false;
	public ArrayList<Player> innocent = new ArrayList<Player>();
	public ArrayList<Player> traitor = new ArrayList<Player>();
	public ArrayList<Player> startInnocent = new ArrayList<Player>();
	public ArrayList<Player> startTraitor = new ArrayList<Player>();
	public HashMap<Player,Player> foundBody = new HashMap<Player,Player>();
	
	int gameLengthSeconds = 60 * 10; 
	int secondsLeft = 0;
	public TypeTTT() {
		name = "TTT";
	}
	
	int sec = 0;
	@Override
	public void runEverySecond() {
		secondsLeft--;
		if (secondsLeft <= 0) {
			//
			traitor.clear();
			checkWinner();
			//
		}
		
		TabList tlist = new TabList("§cTTT","§e"+secToMin(secondsLeft));
		
		for (Player p : players) {
			
			tlist.send(p);
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
	
	
	public String secToMin(int sec ) {
		int min = sec / 60;
		sec = sec - min * 60;
		
		String ret = min+":"+"sec";
		if (sec < 10) {
			ret = min+":0"+"sec";
		}
		return ret;
	}
	@Override
	public void runEveryTick() {
		for(org.bukkit.entity.ArmorStand as : Spelldrop.items.keySet()){
			as.setRightArmPose(as.getRightArmPose().add(0, 0.1, 0));
			ParUtils.createParticle(Particles.CLOUD, as.getLocation().clone().add(0, 0.5, 0), 0, 0, 0, 1, 0);
		}		
	}
	
	
	
	@Override
	public void gameStart() {
		secondsLeft = gameLengthSeconds;
		innocent.clear();
		traitor.clear();
		
		startInnocent.clear();
		startTraitor.clear();
		

		scoreboard = new TTTScoreboard();
		scoreboard.showScoreboard();
		
		won = false;
		gameOver = false;
		
		int playerCount = players.size();
		int traitorCount = 1;
		
		
		scoreboard = new TTTScoreboard();
		scoreboard.showScoreboard();
		
		PlayerUtils.showAllPlayers();

		setupJumpPad(currentmap);
		
		for (Player p : players) {
			p.teleport(nextLoc());
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.setLevel(0);
			p.getInventory().addItem(ItemStackUtils.createItemStack(Material.WOODEN_SWORD, 1, 0, "§eHolz-Schwert", null, true));
			if (traitor.contains(p)) {
				p.setLevel(10);
			}
		}
		
		
		
		for(int i = 0; i<10; i++){
			spawnNewSpell();
		}
		
		if ( ((int)playerCount/3)-1 > 0)
		traitorCount += ((int)playerCount/3)-1;
		
		
		for (int i = 0;i<traitorCount;i++) {
			int index = MathUtils.randInt(0, players.size()-1);
			setTraitor(players.get(index));
			
		}
		
		for (Player p : players) {
			if (!traitor.contains(p)){
				setInnocent(p);
			}
		}
		
		for(Player p : innocent){
			p.sendMessage("§8| §7Du bist §6unschuldig!");
			new Title("§a§lUNSCHULDIGER", "ist deine Rolle").send(p);
			p.getInventory().setItem(8, ItemStackUtils.createItemStack(Material.EMERALD, 1, 0, "§eWeltenkatalysator", null, true));
		}
		for(Player p : traitor){
			if(traitor.size() > 1){
				String build = "§8| §7Du bist ein Traitor mit §6";
				for(Player p2 : traitor){
					if(!p2.equals(p)){
						build += p2.getName()+", ";
					}
				}
				build = build.substring(0, build.length()-2) + ".";
				p.sendMessage(build);
			}else{
				p.sendMessage("§8| §7Du bist §6alleine §7Verräter!");
			}
			new Title("§c§lVERRÄTER", "ist deine Rolle").send(p);
			p.getInventory().setItem(8, ItemStackUtils.createItemStack(Material.EMERALD, 1, 0, "§cSchwarzmarkt", null, true));
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
		TTTCorpse corpse = new TTTCorpse(p, true);
		corpse.spawn();
		
		p.getInventory().clear();
		players.remove(p);
		p.setGameMode(GameMode.ADVENTURE);
		if (Spell.damageCause.containsKey(p)) {
			DamageCauseContainer dcc = Spell.damageCause.get(p);
			dcc.killer.setLevel(dcc.killer.getLevel()+5);
			
			
		}
	
		
		
		p.setHealth(20);
		players.remove(p);
		p.setGameMode(GameMode.ADVENTURE);
		PlayerUtils.hidePlayer(p);
		
		if (innocent.contains(p))
			innocent.remove(p);
		if (traitor.contains(p))
			traitor.remove(p);
		
		p.setNoDamageTicks(100);
		p.teleport(p.getLocation());
		checkWinner();
		
		
	}
	
	public void spawnNewSpell() {
		
		Location nLoc = nextLoc();
			
		Location a = getRandomLocation(nLoc.clone().add(30,5,30), nLoc.clone().add(-30,-40,-30));
		//int b =  Bukkit.getWorld("world").getHighestBlockYAt(a);
		//a.setY(b);
		
		int tries = 0;
		Location next = a.clone();
		while(true){
			if(next.getBlock().getType() == Material.AIR 
			&& next.getBlock().getRelative(BlockFace.DOWN).getType().isSolid()
			&& next.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR){
				break;
			}
			
			if(tries >= 80){
				break;
			}else{
				tries++;
				next.add(0,1,0);
			}
		}
		
		a = next.clone();
		a.setX(a.getBlockX());
		a.setZ(a.getBlockZ());
		a.setY(a.getBlockY());
		
		if(next.getBlock().getType() == Material.AIR 
				&& next.getBlock().getRelative(BlockFace.DOWN).getType().isSolid()
				&& next.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR){
			new Spelldrop(a);
		}else {
			spawnNewSpell();
		}
			
			
			
		
		}
	
	public static Location getRandomLocation(Location loc1, Location loc2) {
        Preconditions.checkArgument(loc1.getWorld() == loc2.getWorld());
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        return new Location(loc1.getWorld(), randomDouble(minX, maxX), randomDouble(minY, maxY), randomDouble(minZ, maxZ));
    }
	
	 public static double randomDouble(double min, double max) {
        return min + ThreadLocalRandom.current().nextDouble(Math.abs(max - min + 1));
	 }
	
	public void spawnNewSpellOLD() {
		
		Location loc = nextLoc();
		loc.add(MathUtils.randInt(-30, 30),MathUtils.randInt(-10, 30),MathUtils.randInt(-30, 30));
		
		while (!loc.getBlock().getType().isSolid())  {
			loc.add(0,-1,0);
			if (loc.getY()< 60 ) {
				spawnNewSpellOLD();
				return;
				
			}
		}
		while (loc.clone().add(0,1,0).getBlock().getType().isSolid())  {
			loc.add(0,1,0);
			if (loc.getY()> 200 ) {
				spawnNewSpellOLD();
				return;
				
			}
		}
		new Spelldrop(loc);
		
		
	
	}
	
	public void setTraitor(Player p) {
		
		//p.sendMessage("§7Du bist der §4Verräter");
		traitor.add(p);
		startTraitor.add(p);
	}
	public void setInnocent(Player p) {
		//p.sendMessage("§7Du bist §aunschuldig");
		innocent.add(p);
		startInnocent.add(p);
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
			for(Entity e : Bukkit.getWorld("world").getEntities()){
				if(e.getType() != EntityType.PLAYER){
					e.remove();
				}
			}
			GameRunnable.stop();
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
