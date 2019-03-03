package spells.spells;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import spells.spellcore.Spell;

public class AntlitzderGöttin extends Spell{

	
	public AntlitzderGöttin() {
		name = "§3Antlitz der Göttin";
		hitSpell = true;
		hitPlayer = false;
		hitBlock = false;
		hitEntity = false;
		steprange = 60;
		cooldown = 200;
		speed = 1;
		hitboxSize = 4;
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		SoundUtils.playSound(Sound.ENTITY_WITCH_AMBIENT, loc, 3, 0.3F);
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
		SoundUtils.playSound(Sound.ENTITY_BLAZE_BURN, loc, 3, 0.4F);
		Location dot = ParUtils.stepCalcCircle(loc, 3, new Vector(0,1,0), -0.3, step*3);
		Location dot2 = ParUtils.stepCalcCircle(loc, 3, new Vector(0,1,0), -0.3, step*3+22);
		loc = caster.getLocation();
		ParUtils.createParticle(Particle.FLAME, dot, 0, 1, 0, 0, 14);
		ParUtils.createParticle(Particle.FLAME, dot2, 0, 1, 0, 0, 14);
		//ParUtils.createParticle(Particle.VILLAGER_ANGRY, caster.getEyeLocation().add(0,-1.7,0), 0, 1, 0, 0, 1);
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
		spell.caster = caster;
		playSound(Sound.BLOCK_ENCHANTMENT_TABLE_USE, loc, 5, 2);
		spell.loc.setDirection(loc.getDirection());
	}

	@Override
	public void onBlockHit(Block block) {
		// TODO Auto-generated method stub
		
	}

}
