package spells.spellcore;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import esze.main.main;
import io.netty.util.internal.ThreadLocalRandom;

public abstract class Spell {
	
	
	//TODO
	//Not hit custom spawned entitys
	//Gravity
	
	//STATS
	
	protected  String name;
	protected int cooldown;
	public Player caster;
	public Location loc;
	
	//CODEVARS
	
	
	protected int steprange = 0;
	protected double step = 0;
	protected int casttime = 0;
	protected int cast = 0;
	protected double powerlevel = 1;
	protected double speed  = 1;
	protected double speedmultiplier = 1;
	protected double hitboxSize = 1;
	
	protected boolean refined = false;
	protected boolean hitEntity = true;
	protected boolean hitPlayer = true;
	protected boolean hitSpell = false;
	protected boolean hitBlock = true;
	protected boolean canHitSelf = false;
	protected boolean multihit = false;
	protected boolean dieOnLowPower = true;
	protected boolean powerBattle = false;
	
	protected boolean dead = false;
	
	//VARS
	
	protected boolean spellLoopStarted = false;
	protected ArrayList<Entity> noTargetEntitys = new ArrayList<Entity>();
	protected ArrayList<Entity> hitEntitys = new ArrayList<Entity>();
	protected static ArrayList<Spell> spell = new ArrayList<Spell>();
	protected static ArrayList<Player> gliding = new ArrayList<Player>();
	protected Location startPos;
	//CALLED
	
	
	
	public void castSpell(Player p,String name) {
		
		this.name = name;
		
		createdSpell(p);
	}
	public void createdSpell(Player p) {
		spell.add(this); //XXXX
		caster = p;
		loc = p.getEyeLocation();
		startPos = p.getEyeLocation();
		setUp(); // steht wahrscheinlich an der falschen Stelle
		if (casttime > 0) {
			startCastLoop();
		}
		else {
			launch();
			startSpellLoop();
		}
	}
	
	
	public void startCastLoop() {
		
		new BukkitRunnable() {
			
			public void run()
			{
				cast++;
				cast();
				if (cast>=casttime) {
					//loc = caster.getEyeLocation();
					launch();
					startSpellLoop();
					this.cancel();
				}
			}
		}.runTaskTimer(main.plugin, 1, 1);
	}
	
	
	
	
	
	public void startSpellLoop() {
		
		
		
		if (!spellLoopStarted) {
			
			
			//TickLoop
			
			new BukkitRunnable() {
				public void run() {
					
					 display();
					
					if (dead == true) {
						spell.remove(this);
						this.cancel();
					}
				}
			}.runTaskTimer(main.plugin, 1, 1);
			
			
			//MoveLoop
			
			new BukkitRunnable() {
				int ts = 0;
				public void run() {
					
					ts++;
					
					
					if (ts>=1/speed) {	
						ts = 0;
						for (int i = 0;i<speed;i++) {
							if (speedmultiplier != 0) {
								move();
								display();
							}
							
							collideWithPlayer();
							collideWithEntity();
							collideWithSpell();
							collideWithBlock();
							if (dead) {
								
								this.cancel();
								break;
							}
							step++;
							if (steprange>0 && step>=steprange) {
								dead = true;
								
							}
							
							
								
						}
						
						
					}
					if (dead == true) {
						onDeath();
						this.cancel();
					}
					
				}
			}.runTaskTimer(main.plugin, 1, 1);
			
			
		}		
		spellLoopStarted = true;
	}
	
	
	
	
	public void callCollision(double hitbox) {
		
		hitboxSize = hitbox;
		collideWithPlayer();
		collideWithEntity();
	}
	
	
	
	public void collideWithPlayer() {
		if (hitPlayer) {
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p == caster && !canHitSelf) {
					continue;
				}
				if (multihit == false && hitEntitys.contains(p)) {
					continue;
				}
				if (p.getEyeLocation().distance(loc)<0.6+hitboxSize ||p.getLocation().distance(loc)<0.6+hitboxSize ) {
					hitEntitys.add(p);	
					onPlayerHit(p);
				}			
			}
		}		
	}
	
	public void collideWithEntity() {
		if (hitEntity) {
			for (LivingEntity ent : Bukkit.getWorld("world").getLivingEntities()) {
				if (ent instanceof Player) {
					continue;
				}
				if (multihit == false && hitEntitys.contains(ent)) {
					continue;
				}
				if (ent.getEyeLocation().distance(loc)<0.6+hitboxSize ||ent.getLocation().distance(loc)<0.6+hitboxSize ) {
					hitEntitys.add(ent);	
					onEntityHit(ent);
				}			
			}
		}
		
		
	}
	
	public void collideWithSpell() {
		if (hitSpell) {
			for (Spell spell : spell) {
				if (spell.caster == caster || spell.dead || !spell.hitSpell)
					continue;
				if (spell.getLocation().distance(loc)<hitboxSize+spell.hitboxSize) {
					if (powerBattle && spell.powerBattle) {
						double d = spell.powerlevel;
						spell.powerlevel-=powerlevel;
						powerlevel-=d;
						if (spell.powerlevel<=0 && spell.dieOnLowPower) {
							spell.dead = true;
						}
						if (powerlevel<=0 && dieOnLowPower) {
							dead = true;
							
						}
				}
					onSpellHit(spell);
					spell.onSpellHit(this);
				}
			}
		}
		
	}
	public void collideWithBlock() {
		if (hitBlock) {
			if (loc.getBlock().getType().isSolid()) {
				onBlockHit(loc.getBlock());
				
			}
		}
	}
	
	//GETTER
	
	public Location getLocation() {
		return loc;
	}
	
	public Vector getDirection() {
		return loc.getDirection();
	}
	
	//MEHTHODS
	
	
	public static int randInt(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}
	
	public static double randDouble(double min, double max) {
		double randomNum = ThreadLocalRandom.current().nextDouble(min, max);
		return randomNum;
	}
	
	
	
	public static Vector randVector() {
		int ix = randInt(-100,100);
		int iy = randInt(-100,100);
		int iz = randInt(-100,100);
		double dx = ((double)ix)/100;
		double dy = ((double)iy)/100;
		double dz = ((double)iz)/100;
		Vector v = new Vector(dx,dy,dz);
		return v;
		
	}
	
	public void playSound(Sound s,Location loc,double volume,double pitch) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(loc, s, (float)volume, (float)pitch);
		}
	}
	
	public void playGlobalSound(Sound s,double volume,double pitch) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), s, (float)volume, (float)pitch);
		}
	}
	
	public void playSingleSound(Sound s,Player p,double volume,double pitch) {
			p.playSound(p.getLocation(), s, (float)volume, (float)pitch);
	}
	
	public void clearHitBlacklist() {
		hitEntitys.clear();
	}
	
	public void bounce() {
		
		if (loc.getBlock().getType().isSolid()) {
			
			Vector dir = loc.getDirection();
			double minDist = 1000000;
			BlockFace nearestFace = null;
			for (BlockFace bf : BlockFace.values()) {
				if (bf == BlockFace.DOWN || bf == BlockFace.UP || bf == BlockFace.WEST || bf == BlockFace.SOUTH || bf == BlockFace.EAST || bf == BlockFace.NORTH) {
					
				
				Block test = loc.getBlock().getRelative(bf);
				if (test.getType().isSolid()) {
					continue;
				}
				double dist = test.getLocation().clone().add(0.5,0.5,0.5).distance(startPos);
				if (dist < minDist) {
					minDist = dist;	
					nearestFace = bf;
				}
				}
				
			}
			if (nearestFace == BlockFace.NORTH || nearestFace == BlockFace.SOUTH) {
				dir.setZ(-dir.getZ());
				while (loc.getBlock().getType() == Material.AIR) {
					loc.add(dir.getX(), dir.getY(), dir.getZ());
				}
				//playSound(Sound.BLOCK_ANVIL_LAND, loc, 0.1F, 2);
				startPos = loc.clone();
				
			}
			if (nearestFace == BlockFace.UP || nearestFace == BlockFace.DOWN) {
				dir.setY(-dir.getY());
				while (loc.getBlock().getType() == Material.AIR) {
					loc.add(dir.getX(), dir.getY(), dir.getZ());
				}
				//playSound(Sound.BLOCK_ANVIL_LAND, loc, 1, 2);
				startPos = loc.clone();
			}
			if (nearestFace == BlockFace.EAST || nearestFace == BlockFace.WEST) {
				dir.setX(-dir.getX());
				while (loc.getBlock().getType() == Material.AIR) {
					loc.add(dir.getX(), dir.getY(), dir.getZ());
				}
				//playSound(Sound.BLOCK_ANVIL_LAND, loc, 1, 2);
				startPos = loc.clone();
				
			}
			
			loc.setDirection(dir);
		}
	}
	
	public Entity spawnEntity(EntityType et) {
		return loc.getWorld().spawnEntity(loc, et);
	}
	
	public Entity spawnEntity(EntityType et,Location loca) {
		return (Entity) loc.getWorld().spawnEntity(loca, et);
	}
	
	public Entity spawnEntity(EntityType et,Location loca,int ticks) {
		final Entity ent = loc.getWorld().spawnEntity(loca, et);
		new BukkitRunnable() {
			public void run() {
				ent.remove();
			}
		}.runTaskLater(main.plugin, ticks);
		return ent;
	}
	
	public void damage(Entity ent, double damage,Player damager) {
		if (ent instanceof LivingEntity) {
			((LivingEntity) ent).damage(damage);
		}
		
		
		
	}
	
	public void heal(LivingEntity ent, double damage,Player healer) {
		double newhealth = ent.getHealth()+damage;
		
		if (ent.getMaxHealth()<newhealth) {
			ent.setHealth(ent.getMaxHealth());
		}
		else {
			ent.setHealth(newhealth);
		}
		
		
	}
	public static void doKnockback(Entity e, Location fromLocation,double speed) {
		// multiply default 0.25
		
		e.setVelocity(fromLocation.toVector().subtract(e.getLocation().toVector()).normalize().multiply(-speed));
	}
	
	
	public static void doPull(Entity e, Location toLocation,double speed) {
		// multiply default 0.25
		
		e.setVelocity(toLocation.toVector().subtract(e.getLocation().toVector()).normalize().multiply(speed));
	}
	public Player pointEntity(Player p) {
		int range = 300;
		int toleranz = 1;
		Location loc = p.getLocation();
		for (double t = 1; t <= range; t=t+0.5) {

			Vector direction = loc.getDirection().normalize();
			double x = direction.getX() * t;
			double y = direction.getY() * t + 1.5;
			double z = direction.getZ() * t;
			loc.add(x, y, z);
			Location lo = loc.clone();

			// Particel


			if (loc.getBlock().getType().isSolid()) {

				break;
			}

			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (!(pl.getName().equalsIgnoreCase(p.getName())) && pl.getGameMode() != GameMode.ADVENTURE) {
					
					Location ploc1 = pl.getLocation();
					Location ploc2 = pl.getLocation();
					ploc2.add(0, 1, 0);
					if (ploc1.distance(loc) <= toleranz || ploc2.distance(loc) <= toleranz) {
						

						return pl;
					}
				}
			}
			
			// SUBTRACTING LOCATION UM den prozess
			// von vorne zu
			// starten
			loc.subtract(x, y, z);
		}
		return null;

	}
	
	public Location block(Player p) {
		Location loc = p.getLocation();
		for (int t = 1; t <= 300; t++) {

			Vector direction = loc.getDirection().normalize();
			double x = direction.getX() * t;
			double y = direction.getY() * t + 1.5;
			double z = direction.getZ() * t;
			loc.add(x, y, z);
			Location lo = loc.clone();

			if (loc.getBlock().getType() != Material.AIR) {
				return loc;

			}

			loc.subtract(x, y, z);
		}
		return null;

	}
	public String getName() {
		return name;
	}
	
	public void setGliding(Player p,boolean glide) {
		p.setGliding(glide);
		if (glide) {
			gliding.add(p);
		}
		else {
			gliding.remove(p);
		}
		
		
		
	}
	
	public boolean checkHit(LivingEntity le,Location loc,Entity p,double range) {
		if (le instanceof Player) {
			
			
			if (((Player)le).getGameMode() == GameMode.ADVENTURE) {
				return false;
			}
			
		}
		if (le != p) { 
			
				if (le instanceof Cow || le instanceof ArmorStand)
					return false;
			
			if (le.getLocation().distance(loc)<range || le.getEyeLocation().distance(loc)<range ) {
				return true;
			}
			
		}
		
		
		return false;
	}
	
	
	// OVERRIDEABLES
	
	public Spell() {};
	
	public abstract void setUp();
	public abstract void cast();
	public abstract void launch();
	public abstract void move();
	public abstract void display();
	public abstract void onPlayerHit(Player p);
	public abstract void onEntityHit(LivingEntity ent);
	public abstract void onSpellHit(Spell spell);
	public abstract void onBlockHit(Block block);
	public abstract void onDeath();
}
