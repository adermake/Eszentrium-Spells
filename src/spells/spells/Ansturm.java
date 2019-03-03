package spells.spells;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import spells.spellcore.Spell;

public class Ansturm extends Spell{

	IronGolem golem;
	
	
	public Ansturm() {
		name = "§eAnsturm";
		hitEntity = false;
		cooldown = 160;
		steprange = 60;
		speed = 1;
		hitboxSize = 2;
		
	}
	
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		golem.remove();
	}

	@Override
	public void setUp() {
		golem = (IronGolem) spawnEntity(EntityType.IRON_GOLEM);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		/*
		if (!caster.isSneaking()) {
			loc.setDirection(loc.getDirection().add(new Vector(0,0.1,0)));
		}
		*/
		if (caster.isSneaking()) {
			
			for (BlockFace bf : BlockFace.values()) {
				if (bf == BlockFace.DOWN)
					continue;
				if (golem.getLocation().getBlock().getRelative(bf).getType() != Material.AIR) {
					golem.setVelocity(loc.getDirection().multiply(-1).setY(0.5D));
					break;
				}
				else {
					golem.setVelocity(loc.getDirection().multiply(-1).setY(-1D));
				}
			}
		} else {
			for (BlockFace bf : BlockFace.values()) {
				if (bf == BlockFace.DOWN)
					continue;
				if (golem.getLocation().getBlock().getRelative(bf).getType() != Material.AIR) {
					
						golem.setVelocity(loc.getDirection().multiply(1).setY(0.5D));
					
					break;
				}
				else {
					golem.setVelocity(loc.getDirection().multiply(1).setY(-1D));
				}
				
			}
		}
		Vector dir = loc.getDirection();
		loc = golem.getLocation();
		loc.setDirection(dir);
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		
		if (caster.isSneaking()) {
			doKnockback(p, caster.getLocation(), -3);
		}
		else {
			doKnockback(p, caster.getLocation(), 3);
		}
		playSound(Sound.ENTITY_IRON_GOLEM_ATTACK, loc, 1, 1);
		caster.setVelocity(caster.getVelocity().setY(2.0D));	
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

}
