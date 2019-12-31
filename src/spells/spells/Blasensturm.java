package spells.spells;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import esze.utils.ParUtils;
import net.minecraft.server.v1_14_R1.Particles;
import spells.spellcore.Spell;
import spells.stagespells.Bubble;

public class Blasensturm extends Spell{

	int rec = 65;
	public Blasensturm() {
		name = "§eBlasensturm";
		steprange = 30;
		speed =2;
		
		hitboxSize = 1;
		cooldown = 20*25;
		hitSpell = true;
		
		
		
		
	}
	
	
	

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		if (refined) {
			speed = 6;
			steprange = 90;
		}
			
	}

	@Override
	public void cast() {
		
	}

	@Override
	public void launch() {
		Player t = pointEntity(caster);
		
		if (t == null) {
			dead = true;
			refund = true;
			return;
		}
		for (int i = 0;i < 30; i++) {
			new Bubble((loc.clone()).setDirection(randVector()), caster, t, name);
		}
		if (refined) {
			for (int i = 0;i < 30; i++) {
				new Bubble((loc.clone()).setDirection(randVector()), caster, t, name);
			}
		}
		dead = true;
	}

	@Override
	public void move() {
	}

	@Override
	public void display() {
		
		
	}

	@Override
	public void onPlayerHit(Player p) {
		
	}

	@Override
	public void onEntityHit(LivingEntity ent) {
		
	}

	@Override
	public void onSpellHit(Spell spell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBlockHit(Block block) {
		
	}
	
	@Override
	public void onDeath() {
		
		
	}
	

}
