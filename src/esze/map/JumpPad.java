package esze.map;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;

public class JumpPad {

	double power = 1;
	JumpPadType type = JumpPadType.UP;
	
	
	
	
	
	int[] i = new int[5];
	public void launch(Player p) {
		
		
		if (type == JumpPadType.DIRECTIONAL) {
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					p.setVelocity(p.getVelocity().setY(0.5));
					
					if (t>30 || p.isSneaking()) {
						p.setVelocity(p.getLocation().getDirection().multiply(power));
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin,1,1);
		}
		
		if (type == JumpPadType.UP) {
			
		}
	}
	public void jumpAnimation() {
		
	}
	
	public enum JumpPadType {		
		UP,
		DIRECTIONAL,	
	};
	
}
