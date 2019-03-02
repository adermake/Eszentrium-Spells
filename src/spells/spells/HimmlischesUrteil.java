package spells.spells;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import esze.utils.ParUtils;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;

public class HimmlischesUrteil extends Spell {

	public HimmlischesUrteil() {
		String name = "HimmlischesUrteil";
		cooldown = 20;
		casttime = 25;
		steprange = 40;
	}
	
	ArrayList<Location> circleDots = new ArrayList<Location>();
	int index = 0;
	
	@Override
	public void setUp() {
		Bukkit.broadcastMessage("XXXY");
		// TODO Auto-generated method stub
		loc = null;
		loc = block(caster);
		if (loc == null) {
			Cooldowns.refundCurrentSpell(caster);
			dead = true;
		}
		else {
			circleDots = ParUtils.preCalcCircle(loc.clone(), 4, new Vector(0,1,0), 0.3);
		}
	}
	


	@Override
	public void cast() {
		// TODO Auto-generated method stub
		ParUtils.parLineFly(Particle.CLOUD, loc, loc.clone().add(0,100,0), 1, 0.5, new Vector(0,1,0));
		for (Location l : circleDots) {
			ParUtils.createParticle(Particle.END_ROD, l.clone(), 0, 1, 0, 0, 0.2F);
			
		}
		
	}

	ArrayList<FallingBlock> fallingBlocks = new ArrayList<FallingBlock>();
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		Bukkit.broadcastMessage("XXX");
		for (Location block : ParUtils.grabBlocks(loc, 34,5)) {
			
			int t = 0;
			Location blockClone = block.clone();
			while (t < 5) {
				if (blockClone.getBlock().getType().isSolid()) {
					blockClone.add(0,1,0);
				}
				else {
					break;
				}
				
				
				
				
				t++;
			}
			if (!blockClone.getBlock().getType().isSolid()) {
				FallingBlock fb = caster.getWorld().spawnFallingBlock(blockClone, block.getBlock().getType(), block.getBlock().getData());
				fallingBlocks.add(fb);
				fb.setGravity(false);
				fb.setGlowing(true);
				fb.setHurtEntities(false);
				fb.setDropItem(false);
				
				fb.setVelocity(fb.getVelocity().setY(randDouble(1, 2)));
			}
			
			
		}
		
	
		for (Location l : circleDots) {
			
			ParUtils.createFlyingParticle(Particle.CLOUD, l, 1, 1, 1, 111, 1,  loc.clone().add(0,2,0).toVector().subtract(l.toVector()).normalize());
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (step >= 10) {
			
		
		for (FallingBlock fb : fallingBlocks) {
			//fb.setVelocity(new Vector(randDouble(-2, 2),randDouble(-2, 2),randDouble(-2, 2)));
			
		}
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		/*for (Location l : circleDots) {
			ParUtils.createParticle(Particle.END_ROD, l, 0, 1, 0, 0, 1F);
		}*/
		
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
		// TODO Auto-generated method stub
		
	}

}
