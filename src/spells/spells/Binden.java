package spells.spells;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import spells.spellcore.Spell;

public class Binden extends Spell {

	
	public Binden() {
		name = "§eBinden";
		//hitEntity = true;
		cooldown = 20*24;
		steprange = 200;
		speed = 1;
		//hitboxSize = 2;
		//
	}
	Entity ent1;
	Entity ent2;
	
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		ent1 = pointEntity(caster);
		
		if (ent1 == null) {
			refund = true;
			dead = true;
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
	boolean standBy = true;
	@Override
	public void move() {
		if (dead)
			return;
		// TODO Auto-generated method stub
		
		
		
		if (swap()) {
			ent2 = pointEntity(caster);
			if (ent2 != null) {
				standBy = false;
				steprange = 100;
				step = 0;
			}
			
		}
		
		if (!standBy) {
			
			doPull(ent1,ent2.getLocation(),2);
			doPull(ent2,ent1.getLocation(),2);
			
		}
		else {
			ParUtils.stepCalcCircle(loc.clone(), 3, new Vector(0,1,0), 0, step);
			ParUtils.stepCalcCircle(loc.clone(), 3, new Vector(0,1,0), 0, -step);
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
