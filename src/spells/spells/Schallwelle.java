package spells.spells;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Schallwelle extends Spell{
	
	public Schallwelle() {
		cooldown = 20*10;
		name = "§eSchallwelle";
		speed = 1;
		steprange =30;
		hitPlayer = true;
		hitSpell = true;
		
	}
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		if (refined) {
			steprange *=2;
			speed = 4;
		}
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		loc.add(loc.getDirection().multiply(0.5));
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		//ParUtils.createFlyingParticle(Particle.BUBBLE_POP, loc,0, 0, 0, 1, 2, loc.getDirection().multiply(-1));
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 1, 0);
		
	}

	@Override
	public void onPlayerHit(Player p) {
		if (refined) {
			p.setVelocity(loc.getDirection().multiply(7));
		}
		else {
			p.setVelocity(loc.getDirection().multiply(5));
		}
		
		damage(p, 1,caster);
		dead = true;
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		damage(ent, 1,caster);
		if (refined) {
			ent.setVelocity(loc.getDirection().multiply(7));
		}
		else {
			ent.setVelocity(loc.getDirection().multiply(5));
		}
		
		dead = true;
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		
		
		//bounce();
		
	}


	@Override
	public void onDeath() {
		
		
	}


	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

}
