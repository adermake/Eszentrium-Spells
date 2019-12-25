package spells.spells;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;
import spells.stagespells.Meteor;

public class Meteoritenhagel extends Spell {

	
	public Meteoritenhagel() {
		cooldown = 20 * 30;
		casttime = 11;
		steprange = 25;
		name = "§cMeteoritenhagel";
		speed = 1;
		
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		loc = null;
		loc = block(caster,200);
		
		if (loc == null) {
			dead = true;
			refund = true;
		}
		
		
		
	}

	@Override
	public void cast() {
		
		//ParUtils.parKreisSolidRedstone(Color.RED, 1, loc.clone(), cast/4, 0, 1, new Vector(0,1,0));
		Location dot = ParUtils.stepCalcCircle(loc.clone(), 8, new Vector(0,1,0), 0, cast*4);
		ParUtils.createParticle(Particles.LANDING_LAVA, dot, 0, 1, 0, 0, 0);
		ParUtils.createParticle(Particles.LAVA, dot, 0, 1, 0, 0, 0);
		//ParUtils.createParticle(Particles.CAMPFIRE_SIGNAL_SMOKE, dot, 0, 1, 0, 0, 5);
		dot = ParUtils.stepCalcCircle(loc.clone(), 8, new Vector(0,1,0), 0, cast*4+22);
		ParUtils.createParticle(Particles.LANDING_LAVA, dot, 0, 1, 0, 0, 0);
		ParUtils.createParticle(Particles.LAVA, dot, 0, 1, 0, 0, 0);
		//ParUtils.createParticle(Particles.CAMPFIRE_SIGNAL_SMOKE, dot, 0, 1, 0, 0, 5);
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	int delay = 0;
	@Override
	public void move() {
		delay++;
		if (delay > 3) {
			Location calc = loc.clone().add(0,100,0);
			calc.add(randInt(-6,6),0,randInt(-6,6));
			new Meteor(calc,caster,name);
			delay = 0;
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

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}


}
