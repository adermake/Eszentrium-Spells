package spells;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import core.Matrix;
import core.ParUtils;
import core.Spell;
import core.main;

public class Heilen extends Spell{
	int s = 9;
	
	public Heilen() {
		steprange = 40;
		cooldown = 2;
		name = "�eHeilen";
		speed = 30;
		
		hitEntity = true;
		hitPlayer = true;
		hitBlock = true;
	}
	public Heilen(int s) {
		this.s = s;
		steprange = 40;
		cooldown = 2;
		name = "�eHeilen";
		speed = 30;
		
		hitEntity = true;
		hitPlayer = true;
		hitBlock = true;
		
		
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		loc.setDirection(caster.getLocation().getDirection());
		loc = Matrix.alignLocRotation(loc, new Vector(-0.3,0,0.4));
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		new BukkitRunnable() {
			public void run() {
				if (s>0) {
					Spell h = (Spell) new Heilen(s-1);
					h.castSpell(caster,name);
				}
				
			}
		}.runTaskLater(main.plugin, 3);
		playSound( Sound.ENTITY_ZOMBIE_VILLAGER_CURE,loc, (float) 0.1, 1);
		loc.setDirection(loc.getDirection().multiply(10).add(randVector()).normalize());
	}

	@Override
	public void move() {
		ParUtils.createParticle(Particle.VILLAGER_HAPPY, loc, 0, 0, 0, 1,0);
		loc.add(loc.getDirection().multiply(0.4));
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		heal(p, 1, caster);
		playSound(Sound.ENTITY_ENDERMITE_DEATH, loc, 2, 1);
		
	}

	@Override
	public void onEntityHit(Entity ent) {
		// TODO Auto-generated method stub
		playSound(Sound.ENTITY_ENDERMITE_DEATH, loc, 2, 1);
		heal((LivingEntity) ent, 1, caster);
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

}
