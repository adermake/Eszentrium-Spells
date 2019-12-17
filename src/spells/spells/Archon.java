package spells.spells;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.PlayerUtils;
import spells.spellcore.Spell;

public class Archon extends Spell {
	
	public Archon() {
		
			cooldown = 33 * 40;
			name = "§8Archon";
			steprange = 15;
			
			hitboxSize = 3;
			speed = 1;
			multihit = true;
			hitSpell = true;
		
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		Player target = pointEntity(caster,15);
		
		if (target == null) {
			refund = true;
			dead = true;
		}
		else {
			PlayerUtils.hidePlayer(caster);
			noTargetEntitys.add(caster);
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
