package spells.stagespells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class SchockLaser extends Spell {

	public SchockLaser(Player p, String namae) {
		name = namae;
		hitBlock = true;
		steprange =  500;
		speed = 250;
		castSpell(p, name);
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
		
	}

	
	boolean spiking = false;
	boolean reverseSpiking = false;
	Vector spike;
	int spikeLength = 1;
	int maxSpikeLength = 1;
	Location phaseLoc;
	@Override
	public void move() {
		
		if (!spiking && randInt(1,6) == 2 ) {
			spikeLength = randInt(0,31-(int)(step/10));
			
			maxSpikeLength = spikeLength;
			spike = randVector().normalize();
			spiking = true;
			reverseSpiking = false;
			phaseLoc = loc.clone();
		}
		
		if (phaseLoc != null)
		phaseLoc.add(phaseLoc.getDirection().multiply(0.5));
		if (spiking) {
			spikeLength--;
			if (spikeLength<= 0) {
				spiking = false;
				
			}
			if (spikeLength < maxSpikeLength/1.5 ) {
				loc.add((phaseLoc.clone().toVector().subtract(loc.toVector()).normalize()).multiply(0.5));
			}
			else {
				loc.add(spike.clone().add(loc.getDirection()).normalize().multiply(0.5));
			}
			
			
			
		}
		else {
			loc.add(loc.getDirection().multiply(0.5));
		}
		
		
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.createParticle(Particles.END_ROD, loc, 0, 0, 0, 1, 0);
		//ParUtils.createParticle(Particles.FIREWORK, loc, 0,1, 0, 0, 0.05);
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		onHit();
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		onHit();
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		onHit();
	}

	
	public void onHit() {
		double x = (caster.getLocation().getY()-loc.getY());
		double dmg = 3 + 15/(1 + Math.pow(Math.E, -(1/200)*x)*15);
		new Explosion(2, dmg, 1, 2, caster, loc, name);
		ParUtils.parKreisDot(Particles.CLOUD, loc, 5, 0, 0.05, loc.getDirection().multiply(-1));
		dead = true;
		playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, loc, 4, 0.3F);
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
	}
}
