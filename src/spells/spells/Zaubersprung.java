package spells.spells;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import esze.utils.ParUtils;
import net.minecraft.server.v1_16_R1.Particles;
import spells.spellcore.Cooldowns;
import spells.spellcore.Spell;

public class Zaubersprung extends Spell {

	
	public Zaubersprung() {
		name = "§cZaubersprung";
		cooldown = 20 * 25;
		steprange = 100;
		speed = 1;
		hitPlayer = false;
		hitEntity = false;
		hitSpell = false;
		
	}
	
	Location block;
	Location back;
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		back = caster.getLocation();
		block = block(caster);
		if (block == null) {
			refund = true;
			dead = true;
		}
		else {
			block = getTop(block);
			block.setDirection(caster.getLocation().getDirection());
			spiralyPortAniamtion(caster.getLocation(),block) ;
		}
	}

	@Override
	public void cast() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void launch() {
		if (dead)
			return;
		// TODO Auto-generated method stub
		caster.teleport(block);
		playSound(Sound.BLOCK_PORTAL_TRIGGER, caster.getLocation(), 1, 2);
		//playSound(Sound.BLOCK_PORTAL_AMBIENT, caster.getLocation(), 1, 2);
	}

	@Override
	public void move() {
		if (silenced.contains(caster)) {
			dead = true;
		}
		// TODO Auto-generated method stub
		if (swap()) {
			dead = true;
		}
	}

	@Override
	public void display() {
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
		spiralyPortAniamtion(caster.getLocation(),back) ;
		caster.teleport(back);
		
	}

	public void spiralyPortAniamtion(Location fromC,Location toC) {
		Location l1 = fromC.clone();
		Location l2 = toC.clone();
		double distance = l1.distance(l2);
		Vector dir = l2.toVector().subtract(l1.toVector()).normalize();
		
		
		new BukkitRunnable() {
			double r = 1;
			int t = 0;
			public void run() {
				for (int i = 0;i<15; i++) {
					t++;
					
					l1.add(dir.clone().multiply(0.5));
					Location pos = ParUtils.stepCalcCircle(l1, r, dir, 0, t);
					ParUtils.createParticle(Particles.SNEEZE, pos,0, 0, 0, 0, 0);
				}
				
				if (t>100 || caster.isSneaking()) {
					this.cancel();
				}
				
			}
		}.runTaskTimer(main.plugin, 1, 1);
		
		
		
	}

	
	@Override
	public void onPlayerHit(Player p) {
		// TODO Auto-generated method stub
		
	}
	
}
