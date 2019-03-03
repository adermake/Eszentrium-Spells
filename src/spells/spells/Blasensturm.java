package spells.spells;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import spells.spellcore.Spell;

public class Blasensturm extends Spell{

	int rec = 65;
	public Blasensturm() {
		steprange = 30;
		speed =2;
		casttime =  6;
		hitboxSize = 1;
		cooldown = 120;
		
		
	}
	public Blasensturm(int counter) {
		steprange = 30;
		speed =2;
		casttime = 2;
		rec = counter;
		hitboxSize = 1;
		
		
	}
	
	

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		if (cast == 1) {
			if (rec>0) {
				Blasensturm bs = new Blasensturm(rec-1);
				bs.castSpell(caster, name);
			}
		}
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		
		loc.add(loc.getDirection().multiply(0.5));
	}

	@Override
	public void display() {
		
		ParUtils.createParticle(Particle.WATER_BUBBLE, loc, 0, 0, 0, 5, 0);
		//ParUtils.createRedstoneParticle(loc, 0, 0, 0, 2, Color.AQUA, 2);
		
	}

	@Override
	public void onPlayerHit(Player p) {
		ParUtils.createParticle(Particle.EXPLOSION_LARGE, loc, 0, 0, 0, 1, 2);
		playSound(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,loc,0.5f,0.7f);
		p.setVelocity(loc.getDirection());
		damage(p,2,caster);
	}

	@Override
	public void onEntityHit(Entity ent) {
		ParUtils.createParticle(Particle.EXPLOSION_LARGE, loc, 0, 0, 0, 1, 2);
		playSound(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,loc,0.5f,0.7f);
		ent.setVelocity(loc.getDirection());
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		dead = true;
		
	}
	
	@Override
	public void onDeath() {
		
		ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.AQUA, 3);
		
		
	}

}
