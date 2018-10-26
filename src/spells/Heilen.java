package spells;

import org.bukkit.Bukkit;
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

public class Heilen extends Spell{
	int step = 9;
	
	public Heilen() {
		steprange = 10;
		cooldown = 2;
		name = "§eHeilen";
		speed = 1;
		
		hitEntity = true;
		hitPlayer = true;
		hitBlock = true;
	}
	public Heilen(int s) {
		step = s;
		steprange = 10;
		cooldown = 2;
		name = "§eHeilen";
		speed = 1;
		
		hitEntity = true;
		hitPlayer = true;
		hitBlock = true;
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
		new BukkitRunnable() {
			public void run() {
				if (step>0)
				new Heilen(step-1);
			}
		}.runTaskLater(main.plugin, 3);
		playSound( Sound.ENTITY_ZOMBIE_VILLAGER_CURE,loc, (float) 0.1, 1);
	}

	@Override
	public void move() {
		ParUtils.createParticle(Particle.VILLAGER_HAPPY, loc, 0, 0, 0, 1,0);
		
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
