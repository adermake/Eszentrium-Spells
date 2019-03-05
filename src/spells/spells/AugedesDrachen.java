package spells.spells;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEnderDragon;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.Matrix;
import esze.utils.ParUtils;
import net.minecraft.server.v1_13_R2.EntityEnderDragon;
import net.minecraft.server.v1_13_R2.EntityLiving;
import spells.spellcore.Spell;

public class AugedesDrachen extends Spell{

	public AugedesDrachen() {
		name = "§aAuge des Drachen";
		hitBlock = false;
		hitPlayer = false;
		hitEntity = false;
		hitSpell = true;
		speed = 1;
		cooldown = 20*60;
		steprange = 130;
		hitboxSize = 1;
		
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		d1.remove();
		
		d2.remove();
		
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
		
		
	}
	
	
	EnderDragon d1;
	EnderDragon d2;
	@Override
	public void move() {
		if (step<=30) {
			loc.add(loc.getDirection().multiply(0.5));
		}
		else {
			loc.add(loc.getDirection().multiply(0.7));
		}
		if (step == 31) {
			hitboxSize = 7;
			hitPlayer = true;
			
			Location l = loc.clone();
			l.setDirection(l.getDirection().multiply(-1));
			d1 = (EnderDragon) loc.getWorld().spawnEntity(l, EntityType.ENDER_DRAGON);
			d1.setSilent(true);
			d2 = (EnderDragon) loc.getWorld().spawnEntity(l, EntityType.ENDER_DRAGON);
			d2.setSilent(true);
			d1.setCollidable(false);
			d2.setCollidable(false);
			d1.setRemoveWhenFarAway(false);
			d2.setRemoveWhenFarAway(false);
			
			//d1.setPhase(Phase.CHARGE_PLAYER);
			//d2.setPhase(Phase.HOVER);
		}
		if (step > 31) {
			
			moveHelix(d1,3, (Math.PI));
			moveHelix(d2,3,0);
			ParUtils.createRedstoneParticle(d1.getLocation(), 0, 0, 0, 1, Color.PURPLE, 3);
			ParUtils.createRedstoneParticle(d2.getLocation(), 0, 0, 0, 1, Color.PURPLE, 3);
			ParUtils.createParticle(Particle.SMOKE_LARGE, d1.getLocation(), 0, 0, 0, 1, 0);
			ParUtils.createParticle(Particle.SMOKE_LARGE, d2.getLocation(), 0, 0, 0, 1, 0);
		}
		
	}

	@Override
	public void display() {
		if (step<=30) {
			ParUtils.createParticle(Particle.VILLAGER_HAPPY, loc, 0, 0, 0, 1, 0);
		}
		else {
			ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.fromBGR(255, 170, 231), 4);
		}
		
		
	}

	@Override
	public void onPlayerHit(Player p) {
		damage(p,7,caster);
		doKnockback(p, loc, 4);
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
	public void moveHelix(EnderDragon d,double r,double pushover) {
		double x = r * Math.cos(step*(Math.PI/16)+pushover);
		double y = 0;
		double z = r * Math.sin(step*(Math.PI/16)+pushover);
		Vector v = new Vector(x,y,z);
		Matrix.rotateMatrixVectorFunktion(v, loc.clone());
		Location l = loc.clone().add(v);
		l.setDirection(l.getDirection().multiply(-1));
		d.teleport(l);
		
		
		
		
	}

}
