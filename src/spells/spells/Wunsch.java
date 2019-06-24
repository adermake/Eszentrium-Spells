package spells.spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Wunsch extends Spell{

	public Wunsch() {
		name = "�3Wunsch";
		cooldown = 20 * 40;
	}
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		caster.setVelocity(caster.getVelocity().multiply(-1));
		caster.setHealth(21-caster.getHealth());
		
		ArrayList<Location> locs = ParUtils.preCalcCircle(caster.getLocation(), 3, caster.getVelocity(), 0);
		
		for (Location loc : locs) {
			ParUtils.createParticle(Particles.ENTITY_EFFECT, loc, 0,0.1, 0, 10,2);
		}
		playSound(Sound.ENTITY_STRAY_DEATH, caster.getLocation(), 3, 0.2F);
		dead = true;
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
