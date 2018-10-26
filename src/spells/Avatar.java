package spells;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import core.ParUtils;
import core.Spell;
import core.main;

public class Avatar extends Spell{

	public Avatar() {
		steprange = 350;
		cooldown = 20;
		hitEntity = false;
		hitPlayer = true;
		hitboxSize = 5;
		
		
		
	}
	Giant g;
	@Override
	public void onDeath() {
		g.remove();
		caster.setFlying(false);
		caster.setAllowFlight(false);
		
	}

	@Override
	public void setUp() {
		spawnSpiraleAnim(caster, 2,3, 0);
		//caster.setAllowFlight(true);
		//caster.setFlying(true);
		g = (Giant) loc.getWorld().spawnEntity(loc, EntityType.GIANT);
		g.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10000,100));
		g.setGlowing(true);
		g.setSilent(true);
		g.setInvulnerable(true);
		g.setCollidable(false);
		caster.setVelocity(caster.getVelocity().setY(2));
		playSound(Sound.ENTITY_ELDER_GUARDIAN_DEATH, loc, 6, 2F);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	int i = 25;
	@Override
	public void move() {
		loc = g.getLocation();
		i--;
		if (caster.isSneaking()) {
			dead = true;
		}
		
		g.teleport(caster.getLocation().clone().add(0,-5,0));
		if (i<=0) {
			
		
		
		
		if (g.getLocation().getBlock().getType() != Material.AIR) {
			caster.setVelocity(caster.getLocation().getDirection().multiply(0.3));
			caster.setVelocity(caster.getVelocity().setY(1).add(caster.getVelocity()));
			playSound(Sound.ENTITY_IRON_GOLEM_STEP, loc, 6, 0.5);
			
		}
		
		
		if (g.getLocation().add(0,-1,0).getBlock().getType() != Material.AIR) {
			caster.setAllowFlight(true);
			caster.setFlying(true);
			
			
		}
		else {
			caster.setAllowFlight(false);
			caster.setFlying(false);
		}
		if (caster.getLocation().clone().add(0,-1,0).getBlock().getType() != Material.AIR) {
			caster.setVelocity(caster.getVelocity().setY(2));
			
			
			caster.setAllowFlight(true);
			caster.setFlying(true);
			
			
		}
		}
	}

	@Override
	public void display() {
		loc = g.getLocation();
		
	}

	@Override
	public void onPlayerHit(Player p) {
		damage(p,20,caster);
		doKnockback(p,g.getLocation(),2);
		
	}

	@Override
	public void onEntityHit(Entity ent) {
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
	
	public void spawnSpiraleAnim(final Player caster, final double radius, final double dichte, final double h�heplus) {
		new BukkitRunnable() {
			Location la = caster.getLocation();
			double t = 0;
			double r = radius;

			public void run() {
				for (int i = 0;i<10;i++) {
					
				
				t = t + Math.PI / 16;
				double x = r*(t/(10)) * Math.cos(t);
				double y = dichte * t;
				double z = r*(t/(10)) * Math.sin(t);
				la.add(x, y, z);
				
				ParUtils.createParticle(Particle.CLOUD, la, 0, 0, 0, 1, 0);
				
		

				la.subtract(x, y, z);
				la.setX(caster.getLocation().getX());
				la.setZ(caster.getLocation().getZ());
				
				if (t > Math.PI * 5) {
					this.cancel();
				}
				}
			}
		}.runTaskTimer(main.plugin, 0, 1);
	}

}
