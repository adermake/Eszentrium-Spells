package stagespells;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import eszeRemastered.utils.ParUtils;
import spellcore.Spell;

public class SwordExplosion extends Spell{
	
	Location svloc;
	
	public SwordExplosion(Location loca,Player castguy) {
		loc = loca;
		svloc = loca;
		steprange = 3;
		caster = castguy;
		hitboxSize = 5;
		speed = 3;
		castSpell(caster,"§eSchwerter aus Licht");
	}

	@Override
	public void setUp() {
		loc = svloc;
		playSound(Sound.ENTITY_GENERIC_EXPLODE, loc, 1, 1.5F);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		
		
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		ParUtils.createParticle(Particle.EXPLOSION_HUGE, loc, 0.5, 0.5, 0.5, 3, 1);
		//ParUtils.createRedstoneParticle(loc, 2, 2, 2, 10, Color.YELLOW, 5);
		
		for (int i = 0;i<=10;i++) {
			//Bukkit.broadcastMessage(""+loc);
			Vector v = randVector().add(loc.getDirection().multiply(2)).normalize().multiply(5);
			if (v.getY()<0)
				v= v.setY(-v.getY());
			
			v.setY(v.getY()/9);
			ParUtils.createFlyingParticle(Particle.CLOUD, loc, 0, 0, 0, 1, 1, v);
			//ParUtils.createFlyingParticle(Particle.END_ROD, loc, 0, 0, 0, 1, 1, randVector().add(loc.getDirection().multiply(-1.2)).normalize());
		}
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		doKnockback(p, loc.add(loc.getDirection()), 1);
		p.damage(5);
	}

	@Override
	public void onEntityHit(Entity ent) {
		// TODO Auto-generated method stub
		doKnockback(ent, loc.add(loc.getDirection()), 1);
		
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
