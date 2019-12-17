package spells.spells;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Mondkugel extends Spell {
	
	ArrayList<Entity> hit = new ArrayList<Entity>();
	public Mondkugel() {
		cooldown = 43 * 20;
		name = "§cAntimaterieball";
		steprange = 50;
		
		hitboxSize = 2;
		speed = 1;
		multihit = true;
		hitSpell = true;
	}
	@Override
	public void setUp() {
	
		
		
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}
	Snowball s;
	@Override
	public void launch() {
		// TODO Auto-generated method stub
		s = (Snowball) spawnEntity(EntityType.SNOWBALL);
		s.setGravity(false);
		s.setGlowing(true);
	}
	
	float div = 2;
	@Override
	public void move() {
		div-= 0.2F;
		Vector dir = loc.getDirection();
		loc = s.getLocation();
		loc.setDirection(dir);
	
		s.setVelocity(loc.getDirection().multiply(div));
		for (Entity ent : hit) {
			ent.setVelocity(s.getVelocity());
		}
		
		if (s.isDead()) {
			dead = true;
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		p.setVelocity(s.getVelocity());
		hit.add(p);
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		// TODO Auto-generated method stub
		ent.setVelocity(s.getVelocity());
		hit.add(ent);
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
		ParUtils.createParticle(Particles.FLASH, loc, 0, 0, 0, 1, 1);
		s.remove();
	}

}
