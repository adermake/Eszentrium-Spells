package spells.spells;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import spells.spellcore.Spell;

public class Scharfschuss extends Spell{
	
	public Scharfschuss() {
		name = "§aScharfschuss";
		hitSpell = true;
		steprange = 300;
		speed = 100;
		casttime = 30;
		cooldown = 20*60;
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	int t = 0;
	int stage = 0;
	@Override
	public void cast() {
		// TODO Auto-generated method stub
		t++;
		stage++;
		if (t<10) {
			ParUtils.dashParticleTo(Particle.FLAME, caster, loc);
			ParUtils.dashParticleTo(Particle.FLAME, caster, loc);
			ParUtils.dashParticleTo(Particle.FLAME, caster, loc);
		}
		
		if (t < 20) {
			if (stage > 2) {
				
				playGlobalSound(Sound.ENTITY_ELDER_GUARDIAN_DEATH_LAND, 1, 1);
				stage = 0;
			}
			
		}
		
		
		
	}

	@Override
	public void move() {
		loc.add(loc.getDirection());
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.RED, 1);
		ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.FUCHSIA, 1);
		ParUtils.createParticle(Particle.CRIT, loc, 0, 0,0, 1, 0);
		ParUtils.createParticle(Particle.SMOKE_NORMAL, loc, 0, 0,0, 1, 0);
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		damage(p, 20,caster);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		
		damage(ent, 20,caster);
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		playSound(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,loc, 10, 1);
		ParUtils.createParticle(Particle.EXPLOSION_LARGE, loc, 0, 0,0, 1, 0);
		dead = true;
	}

	@Override
	public void launch() {
		loc = caster.getEyeLocation();
		playSound(Sound.ENTITY_ZOMBIE_INFECT,loc, 10, 1);
		
	}

}
