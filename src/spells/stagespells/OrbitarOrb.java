package spells.stagespells;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class OrbitarOrb extends Spell {
	
	ArmorStand a;
	int offset;
	public OrbitarOrb(Player p,int offset,int steps) {
		name = "§6Orbitar";
		steprange = steps;
		castSpell(p,name);
		speed = 2;
		a = createArmorStand(p.getLocation());
		a.setHelmet(new ItemStack(Material.CACTUS));
		noTargetEntitys.add(a);
		hitboxSize = 2;
		multihit = true;
		this.offset = offset;
	}
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}
	Location lastloc;
	@Override
	public void move() {
		
		// TODO Auto-generated method stub
		loc.setPitch(loc.getPitch()+90);;
		lastloc = loc;
		loc = ParUtils.stepCalcCircle(caster.getEyeLocation(),6+Math.sin(step/20), loc.getDirection(), 0, step+offset);
		a.teleport(loc.clone().add(0,-0.5,0));
		
	}
	Vector dir = new Vector(0,0,0);
	@Override
	public void display() {
		// TODO Auto-generated method stub
		if (lastloc == null)
			lastloc = loc;
		dir = loc.toVector().subtract(lastloc.toVector());
		if (speed > 2)
			ParUtils.dropItemEffectVector(loc.clone().add(0,1,0), Material.CACTUS, 1,1, 1,dir);
			
			//ParUtils.createFlyingParticle(Particles.CRIT, loc.clone().add(0,2.5,0), 0, 0, 0, 1, 5, dir.normalize());
		//ParUtils.createRedstoneParticle(loc.clone().add(0,2.5,0), 0.5,0.5, 0.5, 1, Color.GREEN, 1.5F);
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 1, 0);
		playSound(Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,loc,1,1);
		p.setVelocity(dir.multiply(speed/2));
		damage(p, 2, caster);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, 0, 0, 1, 0);
		playSound(Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,loc,1,1);
		ent.setVelocity(dir.multiply(speed));
		damage(ent, 2, caster);
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
		a.remove();
	}
	
	public void setSpeed(int u) {
		steprange+=3;
		speed = u;
	}

}
