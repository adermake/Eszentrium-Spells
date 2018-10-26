package spells;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import core.ParUtils;
import core.Spell;
import core.main;

public class Flucht extends Spell{

	public Flucht() {
		
		
		steprange = 20*5;
		cooldown = 12;
		name = "§eFlucht";
		speed = 1;
		
		hitPlayer = false;
		hitBlock = false;
		
		
		
	}
	
	

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
		// TODO Auto-generated method stub
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.hidePlayer(main.plugin, caster);
		}
		
		ParUtils.createParticle(Particle.SMOKE_LARGE, loc, 1, 1, 1, 100, 0);
		caster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 100));
		follow(bat(caster),caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
		bat(caster);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		caster.setVelocity(caster.getLocation().getDirection().multiply(0.6));
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
		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.showPlayer(main.plugin, caster);
		}
	}
	
	public LivingEntity bat(final Player p) {
		
		final Bat bat = (Bat) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
		
		
		
		main.plugin.getServer().getScheduler().scheduleSyncDelayedTask(main.plugin, new Runnable() {
			public void run() {
				bat.remove();
				
				

			}
		}, 20 * 5L);
		return bat;
	}
	public void follow (LivingEntity le,LivingEntity p) {
		new BukkitRunnable() {
			public void run() {
				
				if (le.isDead()) {
					this.cancel();
				}
				doPull(le, p.getLocation(), 0.8D);
			}
		}.runTaskTimer(main.plugin, 1, 1);
	}
}
