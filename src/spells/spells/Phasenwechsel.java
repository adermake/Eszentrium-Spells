package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.NBTUtils;
import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Phasenwechsel extends Spell {

	Player target;
	public Phasenwechsel() {
		
		name = "§rPhasenwechsel";
		cooldown = 20*30;
		hitPlayer = false;
		hitEntity = false;
		hitboxSize = 5;
		casttime = 20;
		steprange = 1;
	}
	
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		target = Bukkit.getPlayer(NBTUtils.getNBT("Archon", caster.getInventory().getItemInMainHand()));
		if (target == null) {
			dead = true;
		}
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
		ParUtils.parLineRedstone(caster.getLocation(), target.getLocation(), Color.WHITE, 2, 2);
		SoundUtils.playSound(Sound.ENTITY_SHULKER_TELEPORT, loc, 1F, 10);
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
		ParUtils.createParticle(Particles.FLASH, target.getLocation(), 0,0, 0, 1, 1);
		ParUtils.createParticle(Particles.FLASH, caster.getLocation(), 0,0, 0, 1, 1);
		target.teleport(caster.getLocation());
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
