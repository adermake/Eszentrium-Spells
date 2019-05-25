package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;  
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.EntityChicken;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.Eggsplosive;

public class Hünchenluftschlag extends Spell{

	public Hünchenluftschlag() {
		name = "§7Hünchenluftschlag";
		cooldown = 20 * 42;
		steprange = 66;
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}
	EntityChicken mcChicken;
	Chicken c;
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
		/*
		mcChicken =  new EntityChicken( , ((CraftWorld) caster.getWorld()).getHandle());
		mcChicken.setPositionRotation(new BlockPosition(loc.getX(), loc.getY(), loc.getZ()), caster.getLocation().getPitch(), caster.getLocation().getYaw());
		((CraftWorld)loc.getWorld()).getHandle().addEntity(mcChicken, SpawnReason.CUSTOM);
		c =  (Chicken) mcChicken.getBukkitEntity();
		c.addPassenger(caster);
		*/
		playSound(Sound.ENTITY_CHICKEN_HURT,loc,4,1);
		
		c = (Chicken) caster.getWorld().spawnEntity(loc, EntityType.CHICKEN);
		c.addPassenger(caster);
	}
	int i = 0;
	@Override
	public void move() {
		// TODO Auto-generated method stub
		//ParUtils.createParticleSqareHorizontal(Particles.FLAME, caster.getLocation(), step/10);
		//mcChicken.setHeadRotation(caster.getLocation().getYaw());
		//mcChicken.setPositionRotation(c.getLocation().getX(), c.getLocation().getY(), c.getLocation().getZ(), caster.getLocation().getYaw(), caster.getLocation().getPitch());
		//c.setVelocity(caster.getLocation().getDirection());
		c.setRotation(caster.getLocation().getYaw(), caster.getLocation().getPitch());
		c.setVelocity(caster.getLocation().getDirection().multiply(1).setY(0.5D));
		
		if (c.getPassengers().isEmpty()) {
			c.remove();
			caster.setVelocity(caster.getVelocity().setY(2));
			dead = true;
		}
		
		i++;
		if (i>3)
		{
			i = 0;
			new BukkitRunnable() {
				Location sLoc = c.getLocation();
				@Override
				public void run() {
					new Eggsplosive(caster,sLoc.add(0,-1,0));
					playSound(Sound.ENTITY_CHICKEN_EGG,loc,4,1);
				}
			}.runTaskLater(main.plugin,3);
			
		}
		
		loc = c.getLocation();
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
		playSound(Sound.ENTITY_CHICKEN_DEATH,loc,4,1);
		
		ParUtils.dropItemEffectRandomVector(loc, Material.FEATHER, 42, 43, 0.3);
		if (c != null)
		c.remove();
	}

}
