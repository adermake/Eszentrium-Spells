package spells.spells;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;

public class Luftsprung extends Spell {

	
	public Luftsprung() {
		steprange = 10;
		name = "§bLuftsprung";
		hitPlayer = false;
		hitEntity = false;
		hitSpell = false;
		cooldown = 20 * 22;
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
		// TODO Auto-generated method stub
		if (step%3 == 0) {
			
			playSound( Sound.ENTITY_WITHER_SHOOT,caster.getLocation(), 1, 2);
			ParUtils.parKreisDot(Particles.CLOUD, caster.getLocation(), 1,0, 0.1, caster.getLocation().getDirection());
			caster.setVelocity(caster.getLocation().getDirection().multiply(2));
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
