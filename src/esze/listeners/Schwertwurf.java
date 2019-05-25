package esze.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.google.common.collect.Multimap;

import esze.main.main;
import esze.players.PlayerAPI;
import net.minecraft.server.v1_14_R1.AttributeModifier;
import net.minecraft.server.v1_14_R1.EnumItemSlot;
import net.minecraft.server.v1_14_R1.GenericAttributes;

public class Schwertwurf implements Listener {

	public static ArrayList<Player> sword = new ArrayList<Player>();

	@EventHandler
	public void onSwordLaunch(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (sword.contains(p)) {
			return;
		}
		EquipmentSlot hand = e.getHand();
		if (hand != null && !hand.equals(EquipmentSlot.HAND))
			return;
		if (e.getAction() == Action.RIGHT_CLICK_AIR && p.isSneaking() || e.getAction() == Action.RIGHT_CLICK_BLOCK && p.isSneaking()) {
			if (p.getInventory().getItemInMainHand().getType().toString().contains("SWORD")) {
				sword.add(p);
				new BukkitRunnable() {
					int t = 0;

					public void run() {
						t = t + 1;
						if (t > 100) {
							sword.remove(p);
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

						for (LivingEntity pl : as.getWorld().getLivingEntities()) {
							if (!pl.getUniqueId().equals(p.getUniqueId()) && pl.getType() != EntityType.ARMOR_STAND) {
								if(pl instanceof Player){
									if(!PlayerAPI.getPlayerInfo((Player)pl).isAlive){
										continue;
									}
								}
								Location ploc1 = pl.getLocation();
								Location ploc2 = pl.getLocation();
								ploc2.add(0, 1, 0);
								if (ploc1.distance(loc) <= 1 || ploc2.distance(loc) <= 1) {
									main.damageCause.remove((Player)pl);
									main.damageCause.put((Player)pl, "Schwertwurf-" + p.getName()); //Damage Cause
									PlayerAPI.getPlayerInfo((Player)pl).damage(p, (int)getAttackDamage(as.getEquipment().getItemInMainHand()), "§3Schwertwurf");
									p.getInventory().addItem(new ItemStack(as.getEquipment().getItemInMainHand()));
									for (Player pl2 : Bukkit.getOnlinePlayers()) {
										pl2.playSound(as.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 2, 2);
									}

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
	}
	
	public double getAttackDamage(ItemStack itemStack) {
        double attackDamage = 1.0;
        UUID uuid = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
        net.minecraft.server.v1_14_R1.ItemStack craftItemStack = CraftItemStack.asNMSCopy(itemStack);
        net.minecraft.server.v1_14_R1.Item item = craftItemStack.getItem();
        if(item instanceof net.minecraft.server.v1_14_R1.ItemSword || item instanceof net.minecraft.server.v1_14_R1.ItemTool || item instanceof net.minecraft.server.v1_14_R1.ItemHoe) {
            Multimap<String, AttributeModifier> map = item.a(EnumItemSlot.MAINHAND);
            Collection<AttributeModifier> attributes = map.get(GenericAttributes.ATTACK_DAMAGE.getName());
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
        return attackDamage;
    }

}
