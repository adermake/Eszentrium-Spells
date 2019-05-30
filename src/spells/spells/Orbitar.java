package spells.spells;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.block.data.Orientable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import spells.spellcore.Spell;
import spells.stagespells.OrbitarOrb;

public class Orbitar extends Spell {

	public ArrayList<OrbitarOrb> orb = new ArrayList<OrbitarOrb>();
	public Orbitar() {
		name = "§6Orbitar";
		cooldown = 20 * 60;
		steprange = 20 * 10;
		
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
		orb.add(new OrbitarOrb(caster,0,steprange,name));
		orb.add(new OrbitarOrb(caster,14,steprange,name));
		orb.add(new OrbitarOrb(caster,28,steprange,name));
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
		if (caster.isSneaking()) {
			for (OrbitarOrb o : orb) {
				o.setSpeed(4);
				
			}
		}
		else {
			for (OrbitarOrb o : orb) {
				o.setSpeed(2);
			}
		}
		
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
		
	}

}
