package spells.spells;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import spells.spellcore.Spell;

public class Schallbrecher extends Spell {

	public Schallbrecher() {
		name = "žeSchallbrecher";
		cooldown = 20*25;
		hitEntity = true;
		hitPlayer = true;
		hitSpell = false;
		hitBlock = false;
		hitboxSize = 1;
		steprange = 15;
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		playSound(Sound.ENTITY_PHANTOM_FLAP, loc, 20, 0);
		playSound(Sound.ENTITY_WITHER_SHOOT, loc, 0.9, 0.6);
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		Location locate = caster.getEyeLocation();
		locate.setY(locate.getY()-0.5);
		Location midloc = caster.getLocation();
		midloc.setY(midloc.getY()+0.2);
		Vector direction = loc.getDirection();
		direction.setY(0);
		caster.setVelocity(direction);
		ParUtils.parKreisDot(Particle.CLOUD, locate, 0.5, 0.5, 0.2, direction.multiply(-1));
		
		ParUtils.createParticle(Particle.FLAME, midloc, 0.1, 0.1, 0.1, 5, 0);
		
		loc = caster.getLocation();
		
		
		
		
	}

	@Override
	public void display() {
		
		
	}

	

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		p.damage(1);
		doKnockback(p, loc, 3);
		playSound(Sound.ENTITY_PLAYER_ATTACK_SWEEP, loc, 10, 0.7);
		
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		
		ent.damage(1);
		doKnockback(ent, loc, 3);
		playSound(Sound.ENTITY_PLAYER_ATTACK_SWEEP, loc, 10, 0.7);
		
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
		Vector direction = loc.getDirection();
		direction.setY(0);
		caster.setVelocity(direction);
		
		Location locate = caster.getEyeLocation();
		locate.setY(locate.getY()-0.5);
		
		
		ParUtils.parKreisDot(Particle.CLOUD, locate, 0.3, 0.0, 0.2, direction.multiply(-1));
		
		
		
		
		
	}
	
	
}
