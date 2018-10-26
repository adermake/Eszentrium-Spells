package spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import core.ParUtils;
import core.Spell;
import core.main;

public class Explosion extends Spell{

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		spawnShockWaffel(caster, 3,loc);
		ParUtils.createParticle(Particle.EXPLOSION_LARGE, loc, 0, 0, 0, 1, 1);

		caster.setVelocity(caster.getVelocity().setY(1.0D));
		caster.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		for (LivingEntity le : caster.getWorld().getLivingEntities()) {
			if (checkHit(le,caster.getLocation(),caster,5)) {
				
				le.damage(9);
				doKnockback(le, caster.getLocation(), 1);
			}
		}
		
		dead = true;
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
	public void onEntityHit(Entity ent) {
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

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}
	
	public void spawnShockWaffel(final Player p, final int length,final Location l) {
		new BukkitRunnable() {
			double t = Math.PI / 4;
			Location loc = l;

			public void run() {
				t = t + 0.1 * Math.PI;
				for (double theta = 0; theta <= 2 * Math.PI; theta = theta + Math.PI / 32) {
					double x = t * Math.cos(theta);
					double y = 2 * Math.exp(-0.1 * t) * Math.sin(t) + 0.5;
					double z = t * Math.sin(theta);
					loc.add(x, y, z);
					// ParticleEffect.FIREWORKS_SPARK.display(loc,0,0,0,0,1);
					ParUtils.createParticle(Particle.SMOKE_LARGE, loc, 0, 0, 0, 1, 1);
				
					loc.subtract(x, y, z);

					theta = theta + Math.PI / 64;

					x = t * Math.cos(theta);
					y = 2 * Math.exp(-0.1 * t) * Math.sin(t) + 1.5;
					z = t * Math.sin(theta);
					loc.add(x, y, z);
					// ParticleEffect.WITCH_MAGIC.display(loc,0,0,0,0,1);
					ParUtils.createParticle(Particle.CLOUD, loc, 0, 0, 0, 1, 1);
					loc.subtract(x, y, z);
				}
				if (t > length) {
					this.cancel();
				}
			}

		}.runTaskTimer(main.plugin, 0, 1);
	}

}
