package spells.spells;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Aufwind extends Spell{

	public Aufwind() {
		name = "§bAufwind";
		cooldown = 20*18;
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUp() {
		spawnSpirale(caster,caster, 2, 2.5, 0);
		spawnSpirale(caster,caster, 2, 2.5, 1);
		spawnSpirale(caster,caster, -2, 2.5, 1);
		spawnSpirale(caster,caster, -2, 2.5, 0);
		dead = true;
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		
	}
	
	public void spawnSpirale(final Player p,final Player caster, final double radius, final double dichte, final double höheplus) {
		new BukkitRunnable() {
			Location la = p.getLocation();
			double t = 0;
			double r = radius;

			public void run() {
				
				t = t + Math.PI / 16;
				double x = r * Math.cos(t);
				double y = dichte * t;
				double z = r * Math.sin(t);
				la.add(x, y, z);
				if (p.getGameMode() == GameMode.ADVENTURE) {
					this.cancel();
				}
				ParUtils.createParticle(Particles.CLOUD, la, 0, 0, 0, 1, 0);
				
				p.playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, (float) 0.1, (float) 0.1);
				p.setVelocity(p.getVelocity().setY(0.5));

				la.subtract(x, y, z);
				la.setX(p.getLocation().getX());
				la.setZ(p.getLocation().getZ());
				if (caster.isSneaking()) {
					this.cancel();
					p.setVelocity(caster.getLocation().getDirection().multiply(2));
					
				}
				if (t > Math.PI * 5) {
					this.cancel();
				}
			}
		}.runTaskTimer(main.plugin, 0, 1);
	}

}
