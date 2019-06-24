package spells.spells;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Blasensturm extends Spell{

	int rec = 65;
	public Blasensturm() {
		name = "§eBlasensturm";
		steprange = 30;
		speed =2;
		casttime =  6;
		hitboxSize = 1;
		cooldown = 20*25;
		hitSpell = true;
		
		
		
		
	}
	public Blasensturm(int counter,boolean ref) {
		refined = ref;
		steprange = 30;
		speed =2;
		casttime = 2;
		rec = counter;
		hitboxSize = 1;
		
		hitSpell = true;
		
	}
	
	

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		if (refined) {
			speed = 6;
			steprange = 90;
		}
			
	}

	@Override
	public void cast() {
		if (cast == 1) {
			if (rec>0) {
				Blasensturm bs = new Blasensturm(rec-1,refined);
				bs.castSpell(caster, name);
			}
		}
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		if (caster.isSneaking()) {
			caster.setVelocity(caster.getLocation().getDirection().multiply(-0.5));
		}
		
	}

	@Override
	public void move() {
		
		loc.add(loc.getDirection().multiply(0.5));
	}

	@Override
	public void display() {
		
		ParUtils.createParticle(Particles.BUBBLE, loc, 0, 0, 0, 5, 0);
		//ParUtils.createRedstoneParticle(loc, 0, 0, 0, 2, Color.AQUA, 2);
		
	}

	@Override
	public void onPlayerHit(Player p) {
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 1, 2);
		playSound(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,loc,0.5f,0.7f);
		p.setVelocity(loc.getDirection());
		damage(p,2,caster);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 1, 2);
		playSound(Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST,loc,0.5f,0.7f);
		ent.setVelocity(loc.getDirection());
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		dead = true;
		
	}
	
	@Override
	public void onDeath() {
		
		ParUtils.createRedstoneParticle(loc, 0, 0, 0, 1, Color.AQUA, 3);
		
		
	}

}
