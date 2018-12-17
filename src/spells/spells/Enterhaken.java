package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import esze.utils.ParUtils;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;

public class Enterhaken extends Spell{

	public Enterhaken() {
		cooldown = 20*1;
		name = "§eEnterhaken";
		speed = 1;
		steprange = 100;
		hitPlayer = false;
	}	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	Vector dir;
	
	@Override
	public void launch() {
		
		// TODO Auto-generated method stub
		loc = blockHo(caster);
		
		
		
		if (loc == null) {
			Cooldowns.refundCurrentSpell(caster);
			
			dead = true;
		}
		
		
	}

	
	@Override
	public void move() {
		
		dir = (loc.clone()).toVector().subtract(caster.getLocation().toVector()).normalize();
		ParUtils.parLine(Particle.CRIT,caster.getLocation(), loc.clone(), 0, 0, 0, 0, 0, 3);
		playSound(Sound.BLOCK_TRIPWIRE_ATTACH, caster.getLocation(), 1, 2);
		caster.setVelocity(dir.clone().multiply(1.4));
		
		if (caster.isSneaking()) {
			playSound(Sound.BLOCK_TRIPWIRE_CLICK_ON, caster.getLocation(), 1, 2);
			caster.setVelocity(caster.getLocation().getDirection().multiply(2));
			dead = true;
		}
		if (((caster.getLocation()).distance(loc))<2) {
			dead = true;
			new BukkitRunnable() {
				int t = 0;
				public void run() {
					t++;
					if (t>60) {
						this.cancel();
					}
					
					playSound(Sound.BLOCK_TRIPWIRE_DETACH, caster.getLocation(), 0.4F, 0.1F);
					caster.setVelocity(caster.getVelocity().multiply(0));
					if (caster.isSneaking()) {
						playSound(Sound.BLOCK_TRIPWIRE_CLICK_ON, caster.getLocation(), 1, 2);
						caster.setVelocity(caster.getLocation().getDirection().multiply(2));
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin, 1, 1);
			
			
		}
		
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
	public Location blockHo(Player p) {
		Location loc = p.getLocation();
		for (int t = 1; t <= 60; t++) {

			Vector direction = loc.getDirection().normalize();
			double x = direction.getX() * t;
			double y = direction.getY() * t + 1.5;
			double z = direction.getZ() * t;
			loc.add(x, y, z);
		
			Location lo = loc.clone();

			if (loc.getBlock().getType() != Material.AIR) {
				return loc;

			}

			loc.subtract(x, y, z);
		}
		return null;

	}
	public Location blockHoPar(Player p) {
		Location l = p.getLocation();
		for (int t = 1; t <= 60; t++) {

			Vector direction = l.getDirection().normalize();
			double x = direction.getX() * t;
			double y = direction.getY() * t + 1.5;
			double z = direction.getZ() * t;
			ParUtils.createParticle(Particle.CRIT, l, 0, 0, 0, 1, z);
			
			Location lo = l.clone();

			if (l.getBlock().getType() != Material.AIR) {
				return l;

			}

			l.subtract(x, y, z);
		}
		return null;

	}
}
