package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.EntityChicken;
import spells.spellcore.Spell;

public class Hünchenluftschlag extends Spell{

	public Hünchenluftschlag() {
		cooldown = 20 * 3;
		steprange = 42;
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
		
		mcChicken =  new EntityChicken(((CraftWorld) caster.getWorld()).getHandle());
		mcChicken.setPositionRotation(new BlockPosition(loc.getX(), loc.getY(), loc.getZ()), caster.getLocation().getPitch(), caster.getLocation().getYaw());
		((CraftWorld)loc.getWorld()).getHandle().addEntity(mcChicken, SpawnReason.CUSTOM);
		c =  (Chicken) mcChicken.getBukkitEntity();
		c.addPassenger(caster);
		
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		mcChicken.setHeadRotation(caster.getLocation().getYaw());
		mcChicken.setPositionRotation(c.getLocation().getX(), c.getLocation().getY(), c.getLocation().getZ(), caster.getLocation().getYaw(), caster.getLocation().getPitch());
		c.setVelocity(caster.getLocation().getDirection());
	
		
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
		c.remove();
	}

}
