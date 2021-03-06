package spells.spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_16_R1.Particles;
import spells.spellcore.Spell;

public class AntlitzderGöttin extends Spell{

	public static ArrayList<Player> deflect = new ArrayList<Player>();
	public AntlitzderGöttin() {
		name = "§3Antlitz der Göttin";
		hitSpell = true;
		hitPlayer = false;
		hitBlock = false;
		hitEntity = false;
		steprange = 60;
		cooldown = 20*40;
		speed = 1;
		hitboxSize = 4;
	}
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		deflect.remove(caster);
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		SoundUtils.playSound(Sound.ENTITY_WITCH_AMBIENT, loc, 3, 0.3F);
		deflect.add(caster);
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
		if (refined)
			caster.setNoDamageTicks(5);
		
		if (caster.getLocation().getY()<62) {
			dead = true;
		}
	}

	@Override
	public void display() {
		//playSound(Sound.BLOCK_NOTE_BLOCK_COW_BELL, loc, 10F, 0.1F);
		SoundUtils.playSound(Sound.ENTITY_BLAZE_BURN, loc, 3, 0.4F);
		Location dot = ParUtils.stepCalcCircle(loc, 1.3, new Vector(0,1,0), -0.3, step*3);
		Location dot2 = ParUtils.stepCalcCircle(loc, 1.3, new Vector(0,1,0), -0.3, step*3+22);
		loc = caster.getLocation();
		
		//ParUtils.createParticle(Particles.FLAME, dot, 0, 1, 0, 0, 14);
		//ParUtils.createParticle(Particles.FLAME, dot2, 0, 1, 0, 0, 14);
		if (refined) {
			ParUtils.dropItemEffectVector(dot, Material.TOTEM_OF_UNDYING, 1, 6, 5,new Vector(0,1,0));
			ParUtils.dropItemEffectVector(dot2, Material.TOTEM_OF_UNDYING, 1, 6, 5,new Vector(0,1,0));
		}
		else {
			ParUtils.dropItemEffectVector(dot, Material.TOTEM_OF_UNDYING, 1, 1, 1,new Vector(0,1,0));
			ParUtils.dropItemEffectVector(dot2, Material.TOTEM_OF_UNDYING, 1, 1, 1,new Vector(0,1,0));
		}
		
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
		if (spell.getName().contains("Antlitz der Göttin")) {
			return;
		}
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
