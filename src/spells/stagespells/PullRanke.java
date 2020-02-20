package spells.stagespells;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import spells.spellcore.Spell;

public class PullRanke extends Spell {

	HashMap<Integer,Vector> path = new HashMap<Integer,Vector>();
	HashMap<Integer,FallingBlock> blocks = new HashMap<Integer,FallingBlock>();
	Entity victim;
	int backstep;
	
	public PullRanke(Player caster,Entity p,HashMap<Integer,Vector> path,HashMap<Integer,FallingBlock> blocks,Location loc,int setstep) {
		backstep = setstep;
		this.loc = loc;
		this.path = path;
		this.blocks = blocks;
		victim = p;
		
		
		steprange = 150;
		hitboxSize = 2;
		powerlevel = 20;
		speed = 3;
		
		multihit = false;
		this.caster = caster;
		cooldown = 0;
		
		castSpell(caster, "§eRanke");
	}
	
	
	
	
	@Override
	public void cast() {
		
	}
	
	@Override
	public void setUp() {
		//loc = caster.getEyeLocation();
		
	
	}
	
	@Override
	public void move() {
		if (victim instanceof LivingEntity) {
			LivingEntity ent = (LivingEntity) victim;
			ent.addPotionEffect(new PotionEffect(PotionEffectType.POISON,20,3));
		}
		if (backstep>10) {
			playSound(Sound.ENTITY_LEASH_KNOT_BREAK, loc, 5, 1);
			blocks.get(backstep-10).setVelocity(path.get(backstep-10).clone().multiply(-1));
			if (blocks.get(backstep)!=null) {
				blocks.get(backstep).remove();
			}
			
			loc = loc.subtract(path.get(backstep));
			if (victim != null) {
				doPull(victim,loc.clone(), 1.7);
				
			}
			backstep--;
		}
		else {
			//GlobalUtils.doPull(victim,caster.getLocation(), 1.2);
			for (FallingBlock fb : blocks.values()) {
				if (fb != null) {
					fb.remove();
				}
			}
			dead = true;
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
