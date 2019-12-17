package spells.spells;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import spells.spellcore.Spell;
import spells.stagespells.PullRanke;

public class Ranke extends Spell{
	HashMap<Integer,Vector> path = new HashMap<Integer,Vector>();
	HashMap<Integer,FallingBlock> blocks = new HashMap<Integer,FallingBlock>();
	FallingBlock current;
	public Ranke() {
		
		name = "§cRanke";
		steprange = 150;
		hitboxSize = 1.5;
		powerlevel = 60;
		speed = 3;
		
		cooldown = 20 * 25;
	}
	
	
	
	
	@Override
	public void cast() {
		
	}
	
	@Override
	public void setUp() {
		loc = caster.getEyeLocation();
		
	
	}
	
	@Override
	public void move() {
		playSound(Sound.ENTITY_LEASH_KNOT_PLACE, loc, 5, 2);
		Vector v = caster.getLocation().getDirection();
		path.put((int)step, v);
		loc.add(v.normalize().multiply(0.5));
		FallingBlock fb = loc.getWorld().spawnFallingBlock(loc, Material.JUNGLE_LEAVES, (byte)0);
		fb.setGravity(false);
		current = fb;
		blocks.put((int)step, fb);
		if (step == steprange-1) {
			fb.remove();
			new PullRanke(caster, null, path, blocks, loc, (int) step);
		}
	}
	
	
	


	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		current.remove();
		new PullRanke(caster, p, path, blocks, loc, (int) step);
		damage(p, 5, caster);
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (int)step/3, 1));
		dead = true;
	}


	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		current.remove();
		new PullRanke(caster, ent, path, blocks, loc, (int) step);
		damage(ent, 5, caster);
		dead = true;
	}


	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
	
		current.remove();
		new PullRanke(caster, null, path, blocks, loc, (int) step);
		dead = true;
	}


	@Override
	public void onDeath() {
		
		
		new BukkitRunnable() {
			public void run() {
				for (FallingBlock fb : blocks.values()) {
					if (fb != null) {
						fb.remove();
					}
				}
			}
		}.runTaskLater(main.plugin, 50);
	}

}
