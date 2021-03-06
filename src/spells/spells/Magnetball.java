package spells.spells;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_16_R1.Particles;
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
		if (refined) {
			hitboxSize = 14;
		}
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
		if (caster.isSneaking()) {
			dead = true;
			
		}
			
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.createFlyingParticle(Particles.CLOUD, loc, 0, 0,0, 1, 1.1, loc.getDirection());
	}

	@Override
	public void onPlayerHit(Player p) {
		double distance = p.getLocation().distance(loc);
		if (step< steprange-10)
		ParUtils.parLineRedstone(loc, p.getLocation().add(0,0.5,0), Color.fromBGR(0, clamp(200-(int)distance*15, 0, 255),clamp((int) (100+distance*15), 0, 255)), 1, 0.5);
		
	}
	
	public int clamp(int i, int min, int max) {
		return (i < min) ? min : ((i > max) ? max : i);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		double distance = ent.getLocation().distance(loc);
		if (step< steprange-10)
		ParUtils.parLineRedstone(loc, ent.getLocation().add(0,0.5,0), Color.fromBGR(0, clamp(200-(int)distance*15, 0, 255),clamp( (int) (100+distance*15),0 ,255 )), 1, 0.5);
		
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
		for (Entity ent : hitEntitys) {
			LivingEntity e = (LivingEntity) ent;
			if (e.getEyeLocation().distance(loc)<0.6+hitboxSize || e.getLocation().distance(loc)<0.6+hitboxSize ) {
				if (e instanceof Player) {
					tagPlayer((Player) e);
				}
				e.setVelocity(loc.toVector().subtract(e.getLocation().toVector()).normalize().multiply(3));
			}
		}
	}
	

}
