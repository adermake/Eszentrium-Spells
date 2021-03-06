package spells.stagespells;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_16_R1.Particles;
import spells.spellcore.Spell;

public class KaminchenEntity extends Spell {
	
	Rabbit ent;
	public KaminchenEntity(Player caster,Vector dir, String namae) {
		steprange = 600;
		hitboxSize = 1.6;
		hitPlayer = true;
		hitEntity = false;
		loc = caster.getEyeLocation();
		ent = (Rabbit) caster.getWorld().spawnEntity(loc, EntityType.RABBIT);
		bindEntity(ent);
		ent.setRabbitType(Type.BROWN);
		ent.setVelocity(dir);
		ent.setTarget(ent);
		ent.setInvulnerable(true);
		name = namae;
		ent.setMaxHealth(1);
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

	double damage = 3;
	int jumpcooldown = 0;
	int i = 0;
	boolean jumpAble = false;
	@Override
	public void move() {
		
		loc = ent.getLocation();
		i++;
		if (i == 5) {
			ent.setRabbitType(Type.BLACK);
		}
		if (i > 10) {
			ent.setRabbitType(Type.BROWN);
			i = 0;
		}
		
		if (!jumpAble)
		jumpcooldown++;
		
		if (jumpcooldown > 60) {
			jumpAble = true;
			jumpcooldown = 0;
		}
		
		
		if(jumpAble && boundOnGround) {
			damage = 9;
			for (Player target : Bukkit.getOnlinePlayers()) {
				if(target == caster)
					continue;
				if (target.getGameMode() == GameMode.SURVIVAL) {
					if (target.getLocation().distance(ent.getLocation())<6) {
						
						Location  loc = ent.getLocation();
						loc.setDirection(target.getEyeLocation().toVector().subtract(ent.getLocation().toVector()).normalize());
						ent.teleport(loc);
						
						playSound(Sound.ENTITY_RABBIT_DEATH,loc,5,1);
						playSound(Sound.ENTITY_RABBIT_JUMP,loc,5,1);
						ParUtils.createFlyingParticle(Particles.CLOUD, loc, 0.1, 0.1, 0.1, 5, 1.2, loc.getDirection());
						ParUtils.dropItemEffectRandomVector(loc, loc.clone().add(0,-1,0).getBlock().getType(), 6, 30, 1);
						ent.setVelocity(loc.getDirection().multiply(2));
						
						ent.setRabbitType(Type.WHITE);
						jumpAble = false;
						ent.setTarget(target);
						
					}
					
				}
				
				
			}
			
		}
		loc = ent.getLocation();
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		doKnockback(p, ent.getLocation(), 2);
		damage(p,damage,caster);
		dead = true;
		loc = ent.getLocation();
		ent.remove();
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 0, 1);
		playSound(Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,loc,1,1);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
	
		
		
		
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
		
		if (ent != null) {
			ent.remove();
			ParUtils.createFlyingParticle(Particles.CLOUD, loc, 0.1, 0.1, 0.1, 5, 0.6, ent.getVelocity());
		}
			
	}

	

}
