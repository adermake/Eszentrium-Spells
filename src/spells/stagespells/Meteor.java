package spells.stagespells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Meteor extends Spell {

	Location overrideLoc;
	public Meteor(Location loca,Player caster) {
		overrideLoc = loca;
		hitboxSize = 2;
		
		castSpell(caster,"Meteoritenhagel");
		
	}
	
	FallingBlock fb;
	@Override
	public void setUp() {
		loc = overrideLoc;
		fb = caster.getWorld().spawnFallingBlock(loc, Material.MAGMA_BLOCK, (byte)0);
		FallingBlock fb2 = caster.getWorld().spawnFallingBlock(loc.clone().add(0,1,0), Material.FIRE,(byte)0);
		fb.addPassenger(fb2);
		fb.setVelocity(fb.getVelocity().setY(-4));
		fb.setFireTicks(100);
		bindEntity(fb);
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
		loc = fb.getLocation();
		if (caster.isSneaking()) {
			Vector v = fb.getVelocity();
			v.setX(caster.getLocation().getDirection().getX());
			v.setZ(caster.getLocation().getDirection().getZ());
			fb.setVelocity(v);
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.createParticle(Particles.EXPLOSION, loc, 0, -1, 0, 0, 2);
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		if (!boundOnGround) {
			p.setVelocity(p.getVelocity().setY(-3));
			damage(p,5,caster);
		}
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		if (!boundOnGround) {
			ent.setVelocity(ent.getVelocity().setY(-3));
			damage(ent,5,caster);
		}
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		//new ExplosionDamage(4, 8, caster, loc);
		
		new Explosion(4, 8, 0.1, 0.5F, caster, loc);
		dead = true;
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
