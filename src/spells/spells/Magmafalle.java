package spells.spells;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;
import spells.stagespells.ExplosionDamage;
import spells.stagespells.Repulsion;

public class Magmafalle extends Spell {
	
	public Magmafalle() {
		name = "§cMagmafalle";
		cooldown = 20 * 60;
		steprange = 800;
		hitboxSize = 4;
		hitPlayer = false;
		hitEntity = false;
		
	}
	@Override
	public void setUp() {
		loc = null;
		loc = block(caster,10);
		if (loc == null) {
			Cooldowns.refundCurrentSpell(caster);
			dead = true;
		}
		else {
			ParUtils.createParticle(Particles.FLAME, loc, 0, 1, 0, 0, 0.3);
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

	int delay = 0;
	@Override
	public void move() {
		delay++;
		if (delay > 10)
			delay = 0;
		// TODO Auto-generated method stub
		if (step == 60) {
			hitPlayer = true;
			hitEntity = true;
		}
		
		if (step > 60) {
			
			if (delay == 0)
			ParUtils.createParticle(Particles.DRIPPING_LAVA, loc.clone().add(0,0.3,0), 1, 0, 1, 1, 0.3);
		}
			
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
		ParUtils.createParticle(Particles.FLAME, loc, 1,1, 1, 20, 0.3);
		playSound(Sound.BLOCK_LAVA_EXTINGUISH,loc,6,1);
		dead = true;
		
		new BukkitRunnable() {
			public void run() {
				new ExplosionDamage(4, 10, caster, loc.clone());
				new Repulsion(4, 5, caster, loc.clone(),true);
				ParUtils.parKreisDir(Particles.FLAME, loc.clone(), 5, 0, 6, new Vector(0,1,0), new Vector(0,1,0));
				ParUtils.createParticle(Particles.EXPLOSION_EMITTER, loc.clone(), 1,1, 1,4, 1);
				ParUtils.createParticle(Particles.LAVA, loc.clone(), 2,2, 2,40, 1);
				playSound(Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,loc.clone(),6,0.1F);
				
			}
		}.runTaskLater(main.plugin,10);
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
