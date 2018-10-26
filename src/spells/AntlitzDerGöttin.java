package spells;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import core.ParUtils;
import core.Spell;

public class AntlitzDerGöttin extends Spell{

	
	public AntlitzDerGöttin() {
		hitSpell = true;
		hitPlayer = false;
		hitBlock = false;
		hitEntity = false;
		
		
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
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

	@Override
	public void move() {
		loc = caster.getLocation();
		
	}

	@Override
	public void display() {
		loc = caster.getLocation();
		ParUtils.createParticle(Particle.FLAME, loc, 0, 1, 0, 0, speed);
		
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
		spell.caster = caster;
		playSound(Sound.BLOCK_ENCHANTMENT_TABLE_USE, loc, 5, 2);
		spell.loc.setDirection(loc.getDirection());
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		
	}

}
