package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.ThermolanzeLaser;

public class Thermolanze extends Spell {

	
	public Thermolanze() {
		cooldown = 20 * 60;
		name = "§cThermolanze";
		steprange = 20 * 4;
		speed = 0.5;
		casttime = 30;
	}
	Location bLoc;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		SoundUtils.playSound(Sound.ENTITY_ZOMBIE_HORSE_AMBIENT, caster.getLocation(),0.3F,50);
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		ParUtils.dashParticleTo(Particles.SMOKE, caster, caster.getLocation().add(randVector().multiply(15)));
		
	}

	
	@Override
	public void launch() {
		// TODO Auto-generated method stub
	
		
		dirLoc = caster.getLocation();
		dirLoc.setYaw(dirLoc.getYaw()-5);
	}
	Location dirLoc;
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		if (caster.getGameMode() == GameMode.ADVENTURE)
			dead = true;
		
		Vector sub = caster.getLocation().getDirection().subtract(dirLoc.getDirection()).normalize().multiply(0.03);
		
		dirLoc.setDirection(dirLoc.getDirection().add(sub));
		
		new ThermolanzeLaser(caster,dirLoc.getDirection(),false);
		playSound(Sound.ENTITY_HUSK_DEATH,loc,10F,1F);
		
		
		
		
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
