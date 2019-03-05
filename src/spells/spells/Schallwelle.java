package spells.spells;

import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
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
		ParUtils.createParticle(Particle.EXPLOSION_LARGE, loc, 0, 0, 0, 1, 0);
		
	}

	@Override
	public void onPlayerHit(Player p) {
		p.setVelocity(loc.getDirection().multiply(2));
		damage(p, 1,caster);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		damage(ent, 1,caster);
		ent.setVelocity(loc.getDirection().multiply(2));
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
