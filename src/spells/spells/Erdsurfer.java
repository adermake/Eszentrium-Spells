package spells.spells;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import spells.spellcore.Spell;

public class Erdsurfer extends Spell {
	public Erdsurfer() {
		cooldown = 20*60;
		name = "�cErdsurfer";
		speed = 1;
		steprange = 0;
		hitPlayer = false;
		
		
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
		caster.setVelocity(caster.getVelocity().setY(1.5));

	}

	int t = 0;
	@Override
	public void move() {
		t++;
		caster.setAllowFlight(true);
		caster.setFlying(true);
		loc.add(randInt(-3, 3), 0, randInt(-3, 3));
		playSound(Sound.BLOCK_WATER_AMBIENT, caster.getLocation(), 1, 1);
		int ab = 0;
		int ti = 0;
		while (loc.getBlock().getType() == Material.AIR
				|| loc.clone().add(0, 1, 0).getBlock().getType() != Material.AIR) {
			ab++;
			loc.add(0, -1, 0);
			ti++;
			if (ti > 200) {
				break;
			}
		}

		final FallingBlock fb = caster.getWorld().spawnFallingBlock(loc.clone().add(0, 1, 0), loc.getBlock().getType(),
				(byte) 0);
		final FallingBlock binder = caster.getWorld().spawnFallingBlock(loc.clone().add(0, 1, 0), loc.getBlock().getType(),
				(byte) 0);

		doPull(fb, caster.getLocation(), 1);
		doPull(binder, caster.getLocation(), 1);
		new BukkitRunnable() {
			int ti = 0;

			public void run() {
				ti++;
				doPull(binder, caster.getLocation(), 1);
				if (binder.getLocation().distance(caster.getLocation()) < 2) {
					binder.remove();
				}
				for (LivingEntity le : caster.getWorld().getLivingEntities()) {
					if (checkHit(le, fb.getLocation(), caster, 2)) {

						new BukkitRunnable() {
							int time = 0;

							public void run() {
								time++;
								doPull(le, caster.getLocation().clone().add(0, -2, 0), 1);
								if (time < 30) {
									this.cancel();
								}
								if (t > 70 || caster.isSneaking()) {
									le.setVelocity(caster.getLocation().getDirection().multiply(2));
									this.cancel();
								}

							}
						}.runTaskTimer(main.plugin, 1, 1);

						if (le.getLocation().distance(binder.getLocation()) < 2) {
							new BukkitRunnable() {
								int time = 0;

								public void run() {
									time++;
									doPull(le, caster.getLocation().clone().add(0, -2, 0), 1);
									if (time < 30) {
										this.cancel();
									}
									if (t > 70 || caster.isSneaking() || caster.getGameMode() == GameMode.ADVENTURE) {
										le.setVelocity(caster.getLocation().getDirection().multiply(2));
										this.cancel();
									}

								}
							}.runTaskTimer(main.plugin, 1, 1);
						}
					}
				}

				doPull(fb, caster.getLocation(), 1);
				loc = caster.getLocation();
				if (ti > 30) {
					binder.remove();
					fb.remove();
					this.cancel();
				}
				if (t > 70 || caster.isSneaking()) {
					this.cancel();
				}
			}
		}.runTaskTimer(main.plugin, 1, 1);
		if (ab > 7) {
			caster.setAllowFlight(false);
			caster.setFlying(false);
		} else {
			caster.setVelocity(caster.getVelocity().setY(0.5));
			caster.setAllowFlight(true);
			caster.setFlying(true);
		}
		Vector v = caster.getLocation().getDirection();
		v.setY(0);
		caster.setVelocity(v);

		if (t > 70 || caster.isSneaking()) {

			caster.setFlying(false);
			caster.setAllowFlight(false);
			caster.setVelocity(caster.getLocation().getDirection().multiply(-1));
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (!pl.getName().equals(caster.getName()) && pl.getGameMode() != GameMode.ADVENTURE) {
					if (pl.getLocation().distance(caster.getLocation()) < 4) {
						pl.setVelocity(pl.getLocation().getDirection().multiply(2));
					}
				}
			}
			dead = true;
			return;
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
		if (spell.getName().contains("Antlitz der G�ttin")){
			caster.setFlying(false);
			caster.setAllowFlight(false);
		}
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
