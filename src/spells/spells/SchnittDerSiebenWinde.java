package spells.spells;

import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.Actionbar;
import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;

public class SchnittDerSiebenWinde extends Spell {

	
	
	
	public SchnittDerSiebenWinde() {
		name = "§bSchnitt der sieben Winde";
		cooldown = 20 * 30;
		steprange = 32;
	}
	Player target;
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		target = pointEntity(caster);
		if (target == null) {
			Cooldowns.refundCurrentSpell(caster);
			dead = true;
		}
		
		else if (target.isOnGround()) {
			Cooldowns.refundCurrentSpell(caster);
			dead = true;
			Actionbar bar = new Actionbar("§c Spieler muss sich in der Luft befinden!");
			bar.send(caster);
		}
		else {
			silenced.add(target);
			playSound(Sound.AMBIENT_UNDERWATER_ENTER,target.getLocation(),1,2);
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
		
		if (caster.getGameMode() == GameMode.ADVENTURE) {
			dead = true;
		}
		
		if (step >30) {
			target.setVelocity(caster.getLocation().getDirection().multiply(3));
			caster.setVelocity(caster.getVelocity().setY(3.0D));
			
			dead = true;
		}else {
			doPull(caster, target.getLocation(),2);
			target.setVelocity(target.getVelocity().setY(0.1));
		}
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		ParUtils.chargeDot(target.getLocation(), Particles.CLOUD, 0.4F, 8);
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
		silenced.remove(target);
	}

}
