package spells.spells;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import esze.utils.ParUtils;
import net.minecraft.server.v1_16_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.ExplosionDamage;
import spells.stagespells.Repulsion;
import spells.stagespells.VampirpilzStage2;

public class Vampirpilz extends Spell{

	public Vampirpilz() {
		cooldown = 20 * 62;
		steprange = 60;
		name = "�6Vampirpilz";
		hitSpell = true;
	}
	boolean holding = true;
	Item i;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		ItemStack m = new ItemStack(Material.RED_MUSHROOM);
		
		i = caster.getWorld().dropItem(loc, m);
	
		
		 
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
		if (i.isOnGround()) {
			
			dead = true;
		}
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
		if (spell.getName().contains("Antlitz der G�ttin"))
			i.setVelocity(spell.caster.getLocation().getDirection().multiply(2));
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
		new VampirpilzStage2(caster, name, loc,refined);
		loc = i.getLocation();
		ParUtils.dropItemEffectRandomVector(loc, i.getLocation().add(0,-1,0).getBlock().getType(), 6, 50, 0.4);
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 5, 2);
		
		
		i.remove();
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
	}

}
