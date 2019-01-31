package esze.map;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.ParUtils;

public class JumpPad {

	double power = 1;
	JumpPadType type = JumpPadType.UP;
	
	
	
	
	
	
	public void launch(Player p) {
		
		
		if (type == JumpPadType.DIRECTIONAL) {
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					p.setVelocity(p.getVelocity().setY(0.5));
					
					if (p.isSneaking()) {
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
	public void jumpAnimation(Player p) {
		
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
	};
	
}
