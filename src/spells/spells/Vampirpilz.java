package spells.spells;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.ExplosionDamage;
import spells.stagespells.Repulsion;

public class Vampirpilz extends Spell{

	public Vampirpilz() {
		cooldown = 5;
		steprange = 60;
		name = "§eVampirpilz";
		
	}
	boolean holding = true;
	Item i;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		ItemStack m = new ItemStack(Material.MUSHROOM_STEM);
		ItemStack m1 = new ItemStack(Material.MUSHROOM_STEM);
		ItemStack m2 = new ItemStack(Material.RED_MUSHROOM_BLOCK);
		i = caster.getWorld().dropItem(loc, m);
		Item i2 = caster.getWorld().dropItem(loc, m1);
		Item i3 = caster.getWorld().dropItem(loc, m2);
		i.addPassenger(i2);
		i2.addPassenger(i3);
		 
		//ar.addPassenger(i);
		if (caster.isSneaking()) {
			i.setVelocity(caster.getLocation().getDirection().multiply(2));
		}
		else {
			i.setVelocity(caster.getLocation().getDirection().multiply(1));
			step = 20;
		}
		
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
		
	
		loc = i.getLocation();	
		
	}

	@Override
	public void display() {
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
		loc = i.getLocation();
		ParUtils.dropItemEffectRandomVector(loc, Material.RED_MUSHROOM_BLOCK, 22, 50, 1);
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 5, 1);
		new ExplosionDamage(7, 11, caster, loc,name);
		new Repulsion(7, 3, caster, loc,name);
		i.remove();
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
	}

}
