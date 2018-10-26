package spells;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import core.Spell;
import core.main;

public class Feuerball extends Spell {

	public Feuerball() {

		steprange = 200;
		cooldown = 2;
		name = "§eFeuerball";
		speed = 1;
		
		hitPlayer = true;
		hitBlock = true;
		
		
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}
	Fireball f;
	@Override
	public void launch() {
		playSound(Sound.ENTITY_BLAZE_SHOOT, caster.getLocation(), 1,0.1F);
		f = caster.launchProjectile(Fireball.class);
		f.setVelocity(caster.getLocation().getDirection().multiply(3));
		f.setIsIncendiary(false);
		f.setYield(0f);
		f.setShooter(caster);
	
	}

	@Override
	public void move() {
		loc = f.getLocation();
		if (caster.isSneaking()) {
			f.setVelocity(f.getVelocity().add(new Vector(0,-0.5,0)));
		}
		
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (f == null || f.isDead())
			dead = true;
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void onDeath() {
		if (f != null)
		f.remove();
		for (int i = 0;i<15;i++) {
			loc.getWorld().spawnFallingBlock(loc, Material.FIRE, (byte) 0).setVelocity(new Vector(randInt(-3,3),randInt(-3,3),randInt(-3,3)));
		}
		for (LivingEntity pl : f.getWorld().getLivingEntities()) {
			if (!pl.getName().equals(f.getShooter())) {
				
			
			if (pl.getLocation().distance(loc) < 4) {
				
				damage(pl, 5, caster);
				doKnockback(pl,loc,2);
				
			}
			
			}
			
		}
	}

	
	
}
