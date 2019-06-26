package spells.stagespells;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class ThermolanzeLaser extends Spell{

	
	public ThermolanzeLaser(Player c,boolean refined) {
		this.refined = refined;
		caster = c;
		name ="§eThermolanze";
		speed = 60;
		steprange = 200;
		castSpell(caster, name);	
		
		
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

	@Override
	public void move() {
		// TODO Auto-generated method stub
		loc.add(loc.getDirection().multiply(0.5));
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (step > 3) {
			ParUtils.createFlyingParticle(Particles.FLAME, loc, 0, 0, 0, 1, 5, loc.getDirection());
			ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.ORANGE, 1);
		}
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		damage(p,1.5,caster);
		p.setFireTicks(8);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		damage(ent,1.5,caster);
		ent.setFireTicks(8);
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		ParUtils.createRedstoneParticle(loc.clone().add(loc.getDirection().multiply(-1)), 0, 0, 0, 1, Color.ORANGE, 5);
		Location dir = loc.clone();
		dir.setPitch(dir.getPitch()-90);
		ParUtils.createFlyingParticle(Particles.CAMPFIRE_COSY_SMOKE, loc.clone().add(loc.getDirection().multiply(-1)), 0, 0, 0, 1, 1, dir.getDirection());
		
		if (refined) {
			bounce();
		}
		else {
			FallingBlock fb = loc.clone().add(loc.getDirection().multiply(-1)).getWorld().spawnFallingBlock(loc.clone().add(loc.getDirection().multiply(-1)), Material.FIRE, (byte)0);
			fb.setVelocity(dir.getDirection().multiply(0.7));
			dead = true;
		}
		
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
