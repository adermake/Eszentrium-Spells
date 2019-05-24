package spells.stagespells;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Item;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class RufDerOzeaneFish extends Spell{

	
	LivingEntity ent;
	public RufDerOzeaneFish(Player p) {
		name = "�eRuf der Ozeane";
		steprange = 30;
		castSpell(p,name);
		
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		EntityType type = EntityType.SQUID;
		int i = 6;
		if (i==1) {
			type = EntityType.TROPICAL_FISH;
		}
		if (i==2) {
			type = EntityType.COD;
		}
		if (i==3) {
			type = EntityType.DOLPHIN;
		}
		if (i==4) {
			type = EntityType.PUFFERFISH;
		}
		if (i==5) {
			type = EntityType.SALMON;
		}
		if (i==6) {
			type = EntityType.SQUID;
		}
		ent = (LivingEntity) caster.getWorld().spawnEntity(caster.getLocation(), type);
		ent.setInvulnerable(true);
		ent.setCollidable(false);
		
		//ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,  5, 5));
		if (randInt(1, 2) == 1) {
			ent.removePotionEffect(PotionEffectType.INVISIBILITY);

			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 100));
		}
		bindEntity(ent);
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
		// TODO Auto-generated method stub
		loc = ent.getLocation();
		ent.setVelocity(caster.getLocation().getDirection().multiply(2));
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
		ParUtils.createParticle(Particles.FALLING_WATER, loc, 0.1, 0.1, 0.1, 10, 1);
	
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
		ent.remove();
	}

}
