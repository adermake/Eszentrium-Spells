package spells.spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


import esze.main.PacketListner;
import esze.main.main;
import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Opfersuche extends Spell {

	public Opfersuche() {
		name = "§6Opfersuche";
		cooldown = 20 * 34;
		steprange = 200;
		hitboxSize= 4;
		hitSpell = true;
	}
	
	Phantom ent;
	ArrayList<Entity> grabbed = new ArrayList<Entity>();
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		ent = (Phantom) caster.getWorld().spawnEntity(caster.getEyeLocation(), EntityType.PHANTOM);
		
		
		ent.setInvulnerable(true);
		ent.setCollidable(false);
		
		bindEntity(ent);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		playSound(Sound.ENTITY_PHANTOM_BITE,loc,5,1.5F);
		loc = caster.getLocation();
		loc.setYaw(loc.getYaw()+-45);
	}

	int groundTime = 0;
	@Override
	public void move() {
		// TODO Auto-generated method stub
		ent.setFireTicks(-1000);
		playSound(Sound.ENTITY_PHANTOM_FLAP,ent.getLocation(),1,1.5F);
		ent.setRotation(loc.getYaw(), loc.getPitch());
		
		
		
		if (step>45) {
			loc.setDirection(caster.getLocation().toVector().subtract(ent.getLocation().toVector()));
			ParUtils.createParticle(Particles.LARGE_SMOKE, ent.getLocation(), 0, 0, 0, 2, 0.01);
			if (ent.getPassengers().isEmpty()) {
				ent.teleport(loc.add(loc.getDirection().multiply(1)));
			}
			else {
				ent.setVelocity(loc.getDirection().multiply(3));
			}
			
			speed = 3;
		}
		else {
			
			ParUtils.createParticle(Particles.LARGE_SMOKE, ent.getLocation().add(0,2,0), 0, 0, 0, 2, 0.01);
			loc.setYaw(loc.getYaw()+2);
			if (ent.getPassengers().isEmpty()) {
				ent.teleport(loc.add(loc.getDirection().multiply(2)));
			}
			else {
				ent.setVelocity(loc.getDirection().multiply(2));
			}
		}
		if (ent.getLocation().distance(caster.getLocation())<1 && step > 50) {
			dead = true;
		}
		
		if (caster.isSneaking() && refined) {
			Location save = ent.getLocation();
			boolean hadPassanger = false;
			for (Entity e : ent.getPassengers()) {
				//e.remove();
				
				hadPassanger = true;
				
				
				ent.remove();
				
				ent = (Phantom) caster.getWorld().spawnEntity(save, EntityType.PHANTOM);
				bindEntity(ent);
				e.setVelocity(randVector().multiply(2));
			}
			
			if (hadPassanger) {
				playSound(Sound.ENTITY_WITHER_HURT,loc,5,1.5F);
				ParUtils.parKreisDot(Particles.LARGE_SMOKE, loc.clone(), 2, 0, 1, loc.getDirection());
			}
			
		}
		Vector dir = loc.getDirection();
		loc = ent.getLocation();
		loc.setDirection(dir);
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		damage(p, 4, caster);
		playSound(Sound.ENTITY_PHANTOM_HURT,loc,5,2F);
		PacketListner.noExit.add(p);
		ent.addPassenger(p);
		
		
	}

	@Override
	public void onEntityHit(LivingEntity ent2) {
		/*
		new BukkitRunnable() {
			int t = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				t++;
				doPull(ent2, ent.getLocation(), 2);
				if (t>30) {
					this.cancel();
				}
			}
		}.runTaskTimer(main.plugin, 1, 1);
		
		*/
		damage(ent, 4, caster);
		ent.addPassenger(ent2);
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		if (step<50 && !ent.getPassengers().isEmpty())
			step = 50;
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		for (Entity ent : grabbed) {
			if (ent instanceof Player) {
				PacketListner.noExit.remove(ent);
			}
		}
		ent.remove();
	}

	
}
