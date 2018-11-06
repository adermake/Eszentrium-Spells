package spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import spellcore.Cooldowns;
import spellcore.ParUtils;
import spellcore.Spell;

public class HimmlischesUrteil extends Spell {

	public HimmlischesUrteil() {
		String name = "HimmlischesUrteil";
		cooldown = 20;
		casttime = 25;
	}
	
	
	ArrayList<Location> circleDots = new ArrayList<Location>();
	int index = 0;
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		loc = null;
		Location loc = block(caster);
		if (loc == null) {
			Cooldowns.refundCurrentSpell(caster);
			dead = true;
		}
		else {
			circleDots = ParUtils.preCalcCircle(loc, 4, new Vector(0,1,0), 0.3);
			loc = loc.add(0,50,0);
		}
	}
	
	public void linePar() {
		Location l = circleDots.get(index);		
		if (l == null)
			return;
		ParUtils.parLine(Particle.END_ROD, l, loc.clone(), 0, 0, 0, 1, 1, 0.3f);
		index+=4;
	
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		linePar();
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
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
	public void onEntityHit(Entity ent) {
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
		
	}

}
