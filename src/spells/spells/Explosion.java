package spells.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Explosion extends Spell{
	
	public Explosion() {
		name = "§3Explosion";
		cooldown = 20*45;
		hitSpell = true;
		
		casttime = 20;
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}
	
	int s = 0;
	@Override
	public void cast() {
		
		// TODO Auto-generated method stub
		loc = caster.getLocation();
		SoundUtils.playSound(Sound.BLOCK_BAMBOO_HIT, loc, 1, 1F);
		SoundUtils.playSound(Sound.BLOCK_LAVA_EXTINGUISH, loc, 1, 1F);
		ParUtils.createFlyingParticle(Particles.LARGE_SMOKE, loc, 0, 0, 0, 1, 1, new Vector(0,1,0));
		ParUtils.createFlyingParticle(Particles.FLAME, loc, 0, 0, 0, 1, 1, new Vector(0,1,0));
	}

	@Override
	public void launch() {
		if (refined) {
			Location block = block(caster);
			if (block != null) {
				loc = block;
			}
		}
		
		if (caster.isSneaking()) {
			caster.setVelocity(caster.getVelocity().setY(3));
		}
		spawnShockWaffel(caster, 5,loc.clone());
		ParUtils.createParticle(Particles.EXPLOSION_EMITTER, loc, 0, 0, 0, 1, 10);
		
		if (!refined)
		//caster.setVelocity(caster.getVelocity().setY(1.0D));
		
		caster.playSound(loc, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
		for (LivingEntity le : caster.getWorld().getLivingEntities()) {
			if (checkHit(le,loc,caster,6)) {
				damage(le, 10, caster);
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
					ParUtils.createParticle(Particles.LARGE_SMOKE, loc, 0, 0, 0, 0, 0);
				
					loc.subtract(x, y, z);

					theta = theta + Math.PI / 64;

					x = t * Math.cos(theta);
					y = 2 * Math.exp(-0.1 * t) * Math.sin(t) + 1.5;
					z = t * Math.sin(theta);
					loc.add(x, y, z);
					// ParticleEffect.WITCH_MAGIC.display(loc,0,0,0,0,1);
					ParUtils.createParticle(Particles.CLOUD, loc, 0, 0, 0, 0, 0);
					loc.subtract(x, y, z);
				}
				if (t > length) {
					this.cancel();
				}
			}

		}.runTaskTimer(main.plugin, 0, 1);
	}

	

}
