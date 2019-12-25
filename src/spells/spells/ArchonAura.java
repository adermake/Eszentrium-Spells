package spells.spells;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.ParticleType;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.ArchonRing;

public class ArchonAura extends Spell {
	public ArchonAura() {
		name = "§3Auran";
		hitSpell = true;
		hitPlayer = true;
		hitBlock = false;
		hitEntity = true;
		
		cooldown = 20*40;
		speed = 1;
		hitboxSize = 1;
		multihit = true;
	}
	ArchonRing ar;
	@Override
	public void setUp() {
		ParticleType pt = Particles.BUBBLE;
		ar = new ArchonRing(name, caster, caster.getLocation(), 1.3,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,0.5F,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1.3,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,2,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,0.5,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1.3,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,0.5F,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1.3,1,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,2,pt);
		new ArchonRing(name, caster, caster.getLocation(), 1,0.5,pt);
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
		loc = caster.getLocation();
		if (caster.isSneaking()) {
			hitboxSize = ar.mult* ar.radius;
			
		}
		
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		damage(p, 1, caster);
		doKnockback(p, caster.getLocation(), 1);
		ParUtils.createParticle(Particles.FIREWORK, p.getLocation(), 0, 0, 0, 5, 1);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		damage(ent, 1, caster);
		doKnockback(ent, caster.getLocation(), 1);
		ParUtils.createParticle(Particles.FIREWORK, ent.getLocation(), 0, 0, 0, 5, 1);
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
