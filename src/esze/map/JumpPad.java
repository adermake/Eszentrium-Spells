package esze.map;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.ParUtils;

public class JumpPad implements ConfigurationSerializable{

	public double power = 1;
	public JumpPadType type = JumpPadType.UP;
	public Location loc;
	
	
	public JumpPad(Location loc,double power,JumpPadType type) {
		
		this.loc = loc;
		this.power = power;
		this.type = type;
		
	}
	
	
	public void launch(Entity p) {
		
		
		if (type == JumpPadType.DIRECTIONAL && p instanceof Player) {
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					p.setVelocity(p.getVelocity().setY(0.5));
					
					if (((Player)p).isSneaking()) {
						p.setVelocity(p.getLocation().getDirection().multiply(power));
						jumpAnimation(p);
						this.cancel();
					}
					if (t>30) {
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin,1,1);
		}
		if (type == JumpPadType.UP) {
			p.setVelocity(p.getVelocity().setY(power));
			jumpAnimation(p);
		}
		
		
	}
	public void jumpAnimation(Entity p) {
		
		if (type == JumpPadType.DIRECTIONAL) {
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					
					ParUtils.createParticle(Particle.CRIT_MAGIC, p.getLocation(), 0, 0, 0, 2, 0.06);
					if (t>30 || p.isOnGround()) {
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin,1,1);
		}
		if (type == JumpPadType.UP) {
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					
					ParUtils.createParticleSqareHorizontal(Particle.VILLAGER_HAPPY, p.getLocation(),(30-t)/15);
					if (t>30 || p.isOnGround() || p.getVelocity().getY()<=0) {
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin,1,1);
		}
	}
	
	public enum JumpPadType {		
		UP,
		DIRECTIONAL,	
	}

	@Override
	public Map<String, Object> serialize() {
		 Map<String, Object> map = new HashMap<String, Object>();
	        map.put("loc", loc);
	        if (type == JumpPadType.UP)
	        map.put("type", "up");
	        if (type == JumpPadType.DIRECTIONAL)
		        map.put("type", "dir");
	        map.put("power", power);
	        return map;
	};
	
	public JumpPad(Map<String, Object> map) {
		loc = (Location) map.get("loc");
		power = (Double) map.get("power");
		if ((String) map.get("type") == "up")
		type = JumpPadType.UP;
		if ((String) map.get("type") == "dir")
			type = JumpPadType.DIRECTIONAL;
        
    }
	
}
