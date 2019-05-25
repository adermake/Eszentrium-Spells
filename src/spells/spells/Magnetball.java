package spells.spells;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Magnetball extends Spell{

	public Magnetball() {
		name = "§eMagnetball";
		cooldown = 20 * 34;
		hitEntity = true;
		hitSpell = true;
		hitPlayer = true;
		hitboxSize = 8;
		steprange = 100;
		speed = 2;
		multihit = true;
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
		playSound(Sound.BLOCK_CONDUIT_ATTACK_TARGET,loc,1f,2f);
		if (caster.isSneaking())
			dead = true;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.createFlyingParticle(Particles.CLOUD, loc, 0, 0,0, 1, 1.1, loc.getDirection());
	}

	@Override
	public void onPlayerHit(Player p) {
		double distance = p.getLocation().distance(loc);
		ParUtils.parLineRedstone(loc, p.getLocation().add(0,0.5,0), Color.fromBGR(0, 200-(int)distance*15,(int) (100+distance*15)), 1, 0.5);
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		double distance = ent.getLocation().distance(loc);
		ParUtils.parLineRedstone(loc, ent.getLocation().add(0,0.5,0), Color.fromBGR(0, 200-(int)distance*15,(int) (100+distance*15)), 1, 0.5);
		
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
		ParUtils.chargeDot(loc, Particles.END_ROD, 0.2, 4,60);
		playSound(Sound.ENTITY_WITHER_SPAWN,loc,4f,2f);
		for (Entity e : hitEntitys) {
			if (e.getLocation().distance(loc)<=8) {
				e.setVelocity(loc.toVector().subtract(e.getLocation().toVector()));
			}
		}
	} 

}
