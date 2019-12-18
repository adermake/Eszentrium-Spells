package spells.spells;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import esze.utils.ItemStackUtils;
import esze.utils.NBTUtils;
import esze.utils.PlayerUtils;
import spells.spellcore.Spell;

public class Archon extends Spell {
	
	public Archon() {
		
			cooldown = 33 * 40;
			name = "§8Archon";
		
			
			hitboxSize = 0;
			speed = 1;
			traitorSpell = true;
			
		
	}
	Player target;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		target = pointEntity(caster,15);
		
		if (target == null) {
			refund = true;
			dead = true;
		}
		else {
			target.setGlowing(true);
			PlayerUtils.hidePlayer(caster);
			unHittable.add(caster);
			caster.setAllowFlight(true);
			caster.setFlying(true);
			caster.getInventory().clear();
			
			caster.getInventory().setItem(1,NBTUtils.setNBT("Archon", target.getName(), ItemStackUtils.createSpell("§rPhasenwechsel")));
			caster.getInventory().setItem(2,NBTUtils.setNBT("Archon", target.getName(), ItemStackUtils.createSpell("§rSchockwelle")));
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
		if (!dead) {
		caster.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 10,true));
		
		// TODO Auto-generated method stub
		if (target.getGameMode() == GameMode.ADVENTURE) {
			caster.setNoDamageTicks(0);
			caster.damage(20);
			dead = true;
		}
		
		caster.setNoDamageTicks(20);
		
		if (swap()) {
			caster.setVelocity(caster.getLocation().getDirection().multiply(3));
		}
		
		
		if (caster.getLocation().distance(target.getLocation())>30) {
			
			doPull(caster, target.getLocation(), 1);
			
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
