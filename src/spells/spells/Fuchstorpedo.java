package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.Explosion;

public class Fuchstorpedo extends Spell {

	Fox ent;
	public Fuchstorpedo() {
		
		name = "�6Fuchstorpedo";
		cooldown = 20 * 32;
		
		hitboxSize = 1;
		hitSpell = true;
		hitPlayer = true;
		hitEntity = true;
		steprange = 80;
		
	}
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		ent = (Fox) spawnEntity(EntityType.FOX);
		ent.setInvulnerable(true);
		ent.setGravity(true);
		ent.setSitting(false);
		noTargetEntitys.add(ent);
		SoundUtils.playSound(Sound.ENTITY_FOX_AGGRO, loc,1,10);
		//SoundUtils.playSound(Sound.ENTITY_FOX_SCREECH, loc,0.5F,10);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}
	
	double speed = 0.3;
	int i = 0;
	@Override
	public void move() {
		i++;
		// TODO Auto-generated method stub
		if (i> 3) {
			SoundUtils.playSound(Sound.ENTITY_FOX_BITE, loc,1,0.5F);
			
		}
	
		speed += 0.03;
		if (speed > 2) {
			speed = 2;
		}
		
		ent.setSitting(loc.getDirection().getY()<-1);
		loc.add(caster.getLocation().getDirection().multiply(speed));
		loc.setDirection(caster.getLocation().getDirection());
		ent.teleport(loc);
		//ent.setVelocity(caster.getLocation().getDirection().multiply(2));
		if (ent.isOnGround()) {
			dead = true;
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (refined)
		ParUtils.createFlyingParticle(Particles.LARGE_SMOKE, loc.clone().subtract(loc.getDirection().multiply(2)), 0.3, 0.3, 0.3, 3, speed, loc.getDirection().multiply(-1));
		ParUtils.createFlyingParticle(Particles.FLAME, loc.clone().subtract(loc.getDirection().multiply(2)), 0.3, 0.3, 0.3, 3, speed, loc.getDirection().multiply(-1));
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		dead = true;
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		dead = true;
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		dead = true;
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		SoundUtils.playSound(Sound.ENTITY_FOX_DEATH, loc,1,4);
		if (!ent.isDead())
		ent.remove();
		if (refined) {
			new Explosion(6, 13, 1, 0, caster, loc, name);
		}
		else {
			new Explosion(6, 8, 1, 0, caster, loc, name);
		}
		
	}

}