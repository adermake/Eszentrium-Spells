package spells.stagespells;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Bubble extends Spell {
	
	Location overrideLoc;
	Vector vel;
	
	public Bubble(Location l,Player c,String namae) {
		vel = randVector().normalize().multiply(0.06).add(l.getDirection()).normalize();
		
		caster = c;
		overrideLoc = l;
		
		
		hitSpell = true;
		steprange = 200;
		speed =2 ;
		hitboxSize = 0.3;
		name = namae;
		castSpell(caster,namae);
	}

	@Override
	public void setUp() {
		
		loc = overrideLoc;
	}

	@Override
	public void cast() {
		
	}

	@Override
	public void launch() {
		
		away = getNearestPlayer(caster,loc).getLocation().toVector().subtract(loc.toVector()).normalize();
	}
	Player target;
	float sp = 1;
	Vector away ;
	@Override
	public void move() {
		if (step % 10 == 0) {
			away = getNearestPlayer(caster,loc).getLocation().toVector().subtract(loc.toVector()).normalize();
		}
		if (step<100) {
			loc.add(vel.clone().multiply(sp));
			sp-= 0.01;
			if (sp<0.1) {
				sp = 0.1F;
			}
			
			loc.add(away.multiply(sp));
		}
		else {
			if (target == null) {
				
				target = getNearestPlayer(caster,loc);
			}
			
			Vector toTarget = target.getLocation().toVector().subtract(loc.toVector()).normalize();;
			
			loc.add(toTarget.multiply(sp));
			
			sp+= 0.01;
			if (sp>1) {
				sp = 1F;
			}
		}
		
		
	}
	
	@Override
	public void display() {
		ParUtils.createParticle(Particles.BUBBLE, loc, 0, 0, 0, 5, 0);
		
	}

	@Override
	public void onPlayerHit(Player p) {
		tagPlayer(p);
		hitEnt(p);
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		hitEnt(ent);
		
	}
	
	public void hitEnt(LivingEntity ent) {
		ent.setVelocity(loc.getDirection().normalize().multiply(3));
		damage(ent, 2, caster);
		dead = true;
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		
	}

	@Override
	public void onDeath() {
		ParUtils.createParticle(Particles.BUBBLE_POP, loc, 0, 0, 0, 1, 1);
		
	}
	
	public Vector lerp(Vector start, Vector end,double val) {
		return new Vector(lerp(start.getX(),end.getX(),val),lerp(start.getY(),end.getY(),val),lerp(start.getZ(),end.getZ(),val));
	}
	
	public double lerp(double start, double end,double val) {
		return ((end - start)*val)+ start;
	}
}
