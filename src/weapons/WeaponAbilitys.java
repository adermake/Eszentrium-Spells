package weapons;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.google.common.collect.Multimap;


import esze.enums.GameType;
import esze.main.main;
import esze.players.PlayerAPI;
import esze.utils.Actionbar;
import esze.utils.ItemStackUtils;
import esze.utils.NBTUtils;
import esze.utils.ParUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.AttributeModifier;
import net.minecraft.server.v1_14_R1.Block.Info;
import net.minecraft.server.v1_14_R1.EnumColor;
import net.minecraft.server.v1_14_R1.EnumItemSlot;
import net.minecraft.server.v1_14_R1.GenericAttributes;
import net.minecraft.server.v1_14_R1.Item;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagInt;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;
import net.minecraft.server.v1_14_R1.PacketPlayOutSetCooldown;
import net.minecraft.server.v1_14_R1.Particles;
import net.minecraft.server.v1_14_R1.PlayerConnection;
import spells.spellcore.Spell;
import spells.spells.BambusDash;
import spells.stagespells.BowArrow;

public class WeaponAbilitys implements Listener {

	public static ArrayList<Player> cd = new ArrayList<Player>();
	public static HashMap<Player,Integer> charge1 = new HashMap<Player,Integer>();
	public static HashMap<Player,Integer> charge2 = new HashMap<Player,Integer>();
	public static HashMap<Player,String> lastLaunched = new HashMap<Player,String>();
	public static HashMap<Player,Vector> lastMovedDir = new HashMap<Player,Vector>();
	@EventHandler
	public void onWeaponUse(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		
		EquipmentSlot hand = e.getHand();
		if (hand != null && !hand.equals(EquipmentSlot.HAND))
			return;
		
		
		
		
		//SPHERE
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().getType() == Material.HEART_OF_THE_SEA) {
				if (lastLaunched.containsKey(p)) {
					if (cd.contains(p))
						return;
					try {
						String name = lastLaunched.get(p);
						Class clazz = Class.forName(name);
						Spell sp = (Spell) clazz.newInstance();
						if (!sp.castSpell(p, "�cFokussph�re")) {
						cd.add(p);
						
						new BukkitRunnable() {
							int sec = 0;
							int cooldown = sp.getCooldown()*2;
							@Override
							public void run() {
								sec++;
								new Actionbar("�bFokussph�re: �e"+(cooldown/20-sec)+" Sekunden").send(p);
								if (sec*20 >= cooldown || !WeaponMenu.running) {
									cd.remove(p);
									new Actionbar("").send(p);
									this.cancel();
								}
								
								// TODO Auto-generated method stub
								
							}
						}.runTaskTimer(main.plugin, 0,20);
						
						} 
						

					} catch (Exception ex) {
						//ex.printStackTrace(System.out);
						p.sendMessage("Spell is not vaild!");
					}
					;
				}
				
			}
			
			if (p.getInventory().getItemInMainHand().getType() == Material.BAMBOO) {
				
				if (charge1.containsKey(p)) {
					
					if (charge1.get(p)>0) {
						
						BambusDash bd = new BambusDash();
						bd.castSpell(p, "�cBambusDash");
						charge1.put(p, charge1.get(p)-1);
					}
				}
			}
			
		}
		
		//BOW 
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().getType() == Material.BOW) {
				if (charge2.containsKey(p) && charge2.get(p)>0) {
					charge2.put(p, charge2.get(p)-1);
					
					new BowArrow(p, p.getEyeLocation(), "�cBogen");
				}
			}
			
		}
		
		
		/*
		if (e.getAction() == Action.LEFT_CLICK_AIR && p.isSneaking() || e.getAction() == Action.LEFT_CLICK_BLOCK && p.isSneaking()) {
			if (p.getInventory().getItemInMainHand().getType() == Material.BOW && p.isSneaking()) {
				p.setVelocity(p.getLocation().getDirection().multiply(-2));
				SoundUtils.playSound(Sound.ENTITY_WITHER_SHOOT, p.getLocation(),2,5);
				ParUtils.createParticle(Particles.CLOUD, p.getLocation(), 0, 0, 0, 11, 2);
				cd.add(p);
				new BukkitRunnable() {
					int t = 0;

					public void run() {
						t = t + 1;
						if (t > 10) {
							cd.remove(p);
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1, (float) 0.9);
							this.cancel();
						}

					}
				}.runTaskTimerAsynchronously(main.plugin, 20, 20);
			}
		}
		*/
		
		
		
		//SWORD
		if (e.getAction() == Action.RIGHT_CLICK_AIR && p.isSneaking() || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()) {
			if (p.getInventory().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
				
				if (cd.contains(p)) {
					return;
				}
				
				cd.add(p);
				new BukkitRunnable() {
					int t = 0;

					public void run() {
						t = t + 1;
						if (t > 100) {
							cd.remove(p);
							p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1, (float) 0.9);
							this.cancel();
						}

					}
				}.runTaskTimerAsynchronously(main.plugin, 0, 0);

				final ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
				as.getEquipment().setItemInMainHand(p.getInventory().getItemInMainHand());
				p.getInventory().setItemInMainHand(null);
				as.setVisible(false);
				as.setArms(true);
				as.setMarker(true);

				as.setRightArmPose(as.getRightArmPose().add(0, 0, 80));

				new BukkitRunnable() {
					double yaa = 0;

					double t = 0;
					Location loc = p.getLocation();
					boolean hasdropped = false;
					int toggle = 0;

					public void run() {

						t = t + 1;
						toggle++;
						Vector direction = loc.getDirection().normalize();
						double x = direction.getX() * t;
						double y = direction.getY() * t + 1.5;
						double z = direction.getZ() * t;
						loc.add(x, y, z);

						as.teleport(loc);
						//ParticleEffect.SWEEP_ATTACK.send(Bukkit.getOnlinePlayers(), loc.getX(), loc.getY(), loc.getZ(), 0.1, 0, 0.1, 0, 1);
						yaa = yaa + 1;
						as.setRightArmPose(as.getRightArmPose().add(-0.7, 0, 0));
						if (loc.getBlock().getType() != Material.WATER) {
							if (loc.getBlock().getType() != Material.AIR) {
								//ParticleEffect.EXPLOSION_LARGE.send(Bukkit.getOnlinePlayers(), loc.getX(),loc.getY(), loc.getZ(), 0, 0, 0, 0, 1);

								p.getInventory().addItem(new ItemStack(as.getEquipment().getItemInMainHand()));
								for (Player pl : Bukkit.getOnlinePlayers()) {
									pl.playSound(as.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2, 2);
								}

								as.remove();

								this.cancel();
								return;
							}
						}

						for (Player pl : Bukkit.getOnlinePlayers()) {
								if (pl instanceof Player && ((Player) pl).getGameMode() == GameMode.SURVIVAL) {
									
									if (p == pl) {
										continue;
									}
								Location ploc1 = pl.getLocation();
								Location ploc2 = pl.getLocation();
								ploc2.add(0, 1, 0);
								if (ploc1.distance(loc) <= 1 || ploc2.distance(loc) <= 1) {
									main.damageCause.remove((Player)pl);
									main.damageCause.put((Player)pl, "Schwertwurf-" + p.getName()); //Damage Cause
									//PlayerAPI.getPlayerInfo((Player)pl).damage(p, (int)getAttackDamage(as.getEquipment().getItemInMainHand()), "�3Schwertwurf");
									p.getInventory().addItem(new ItemStack(as.getEquipment().getItemInMainHand()));
									for (Player pl2 : Bukkit.getOnlinePlayers()) {
										pl2.playSound(as.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 2, 2);
									}
									pl.damage(4);
									as.remove();
									this.cancel();
									return;
								}
								}
							
						}

						loc.subtract(x, y, z);

						if (t > 30) {
							if(!PlayerAPI.getPlayerInfo((Player)p).isAlive){
								
							}else{
								p.getInventory().addItem(new ItemStack(as.getEquipment().getItemInMainHand()));
							}
							as.remove();
							this.cancel();
							return;
						}
					}
				}.runTaskTimer(main.plugin, 0, 0);

			}

		}
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
				if (goldLaunch.contains(p)) {
					goldLaunch.remove(p);
				}
				for (LivingEntity ent : p.getWorld().getLivingEntities()) {
					if (ent.getLocation().distance(p.getLocation())<5 && ent != p) {
						new BukkitRunnable() {
							int t = 0;
							public void run() {
								t++;
								if (goldLaunch.contains(p)) {
									goldLaunch.remove(p);
									this.cancel();
								}
								else {
									doPull(ent, p.getLocation().add(0,0.5,0).add(p.getLocation().getDirection().multiply(3)), ent.getLocation().distance(p.getLocation())/4);
								}
								if (t>10) {
									
									this.cancel();
								}
								
							}
						}.runTaskTimer(main.plugin, 0, 1);
						
					}
				}
			}
			
			
		}
	}
	ArrayList<Player> goldLaunch = new ArrayList<Player>();
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
				
				if (p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_SWORD) {
					if (!goldLaunch.contains(p)) {
						goldLaunch.add(p);
						
					}
					e.getEntity().setVelocity(p.getLocation().getDirection().multiply(8));
					
				}
				
				if (p.getInventory().getItemInMainHand().getType() == Material.BAMBOO) {
					
					if (!charge1.containsKey(p)) {
						charge1.put(p, 1);
					}
					else {
						if (charge1.get(p)<3)
						charge1.put(p, charge1.get(p)+1);
					}
					if (cd.contains(p)) {
						
						return;
						
					}
						
					if (!p.isSneaking()) {
						return;
					}
						Vector dir = p.getLocation().getDirection().normalize().multiply(-1.5);
						//dir.add(new Vector(0,1,0));
						
						//dir = dir.normalize().multiply(1);
						
						p.setVelocity(dir.add(new Vector(0,0.2,0)));
						
						sendCooldownPacket(p, p.getInventory().getItemInMainHand(), 20);
						cd.add(p);
						new BukkitRunnable() {
							public void run() {
								cd.remove(p);
							}
						}.runTaskLater(main.plugin, 20);
					
					
				}
			
		}
	}
	
	
	public void sendCooldownPacket(Player p,ItemStack is,int time) {
		
		
		 PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		
		 Item item = CraftItemStack.asNMSCopy(is).getItem();
		 PacketPlayOutSetCooldown packet = new PacketPlayOutSetCooldown(item, time);
		 connection.sendPacket(packet);
	}
	public void doPull(Entity e, Location toLocation,double speed) {
		// multiply default 0.25
		
		e.setVelocity(toLocation.toVector().subtract(e.getLocation().toVector()).normalize().multiply(speed));
	}
	public double getAttackDamage(ItemStack itemStack) {
        double attackDamage = 5.0;
        UUID uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
        net.minecraft.server.v1_14_R1.ItemStack craftItemStack = CraftItemStack.asNMSCopy(itemStack);
        net.minecraft.server.v1_14_R1.Item item = craftItemStack.getItem();
        if(item instanceof net.minecraft.server.v1_14_R1.ItemSword || item instanceof net.minecraft.server.v1_14_R1.ItemTool || item instanceof net.minecraft.server.v1_14_R1.ItemHoe) {
            Multimap<String, AttributeModifier> map = item.a(EnumItemSlot.MAINHAND);
            Collection<AttributeModifier> attributes = map.get(GenericAttributes.ATTACK_DAMAGE.getName());
            /*
            if(!attributes.isEmpty()) {
                Bukkit.getLogger().info("Found one or more attribute modifiers:");
                for(AttributeModifier am: attributes) {
                    Bukkit.getLogger().info(String.format("  (%s, %s, %f, %d)",am.a().toString(), am.b(), am.d(), am.c()));
                }
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.d() == 0) attackDamage += am.d();
                }
                double y = 1;
                // UPDTATE FAIL?
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.d() == 1) y += am.d();
                }
                attackDamage *= y;
                for(AttributeModifier am: attributes) {
                    if(am.a().toString().equalsIgnoreCase(uuid.toString()) && am.d() == 2) attackDamage *= (1 + am.d());
                }
            }
        }
        */
        }
        return attackDamage;
   
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		//lastMovedDir.put(e.getPlayer(), e.getTo().toVector().subtract(e.getFrom().toVector()));
		
	}
	
	public static void clearLists() {
		lastLaunched.clear();
		cd.clear();
		charge1.clear();
		charge2.clear();
	}

}
