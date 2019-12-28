package spells.spells;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import spells.spellcore.Spell;
import spells.stagespells.ThermolanzeLaser;

public class Thermolanze extends Spell {

	
	public Thermolanze() {
		cooldown = 20 * 30;
		name = "žeThermolanze";
		steprange = 30;
		speed = 4;
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
		loc.setYaw(loc.getYaw()-60);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		playSound(Sound.ENTITY_HUSK_DEATH,loc,10F,1F);
		loc.setYaw(loc.getYaw()+6);
		new ThermolanzeLaser(caster,loc.getDirection(),refined);
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
