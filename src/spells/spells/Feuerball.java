package spells.spells;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import spells.spellcore.Spell;

public class Feuerball extends Spell {

	public Feuerball() {

		steprange = 200;
		cooldown = 20*18;
		name = "§eFeuerball";
		speed = 1;
		
		hitSpell = true;
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
		damage(p, 5, caster);
		doKnockback(p,loc,2);
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		damage(ent, 5, caster);
		doKnockback(ent,loc,2);
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void onBlockHit(Block block) {
		

		
	}

	@Override
	public void onDeath() {
		for (int i = 0;i<15;i++) {
			loc.getWorld().spawnFallingBlock(loc, Material.FIRE, (byte) 0).setVelocity(new Vector(randInt(-3,3),randInt(-3,3),randInt(-3,3)).normalize().multiply(1.5));
		}
		callCollision(4);
		if (f != null)
		f.remove();
			}

	
	
}
