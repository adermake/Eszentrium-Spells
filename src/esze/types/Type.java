package esze.types;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import esze.main.main;

public class Type {
	
	public String name;
	public String currentmap;
	public ArrayList<Player> players = new ArrayList<Player>();
	public ArrayList<Player> spectator = new ArrayList<Player>();
	public int spawnloc = 1;
	
	public void runEverySecond(){
		
	}
	
	public void runEveryTick(){
		
	}
	
	public void death(PlayerDeathEvent event){
	}
	
	public void gameStart(){
		
	}
	
	public Location nextLoc(){
		Location loc = null;
		
		
		if(main.plugin.getConfig().contains("maps."+currentmap+"."+spawnloc)){
			loc = (Location) main.plugin.getConfig().get("maps."+currentmap+"."+spawnloc);
			spawnloc++;
		}else{
			spawnloc = 1;
			return nextLoc();
		}
		
		
		return loc;
	}

	
	public void out(Player p) {
		players.remove(p);
		spectator.add(p);
	}
}
