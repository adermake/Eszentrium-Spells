package spells.spellcore;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import esze.utils.NBTUtils;
import esze.utils.SoundUtils;
import net.minecraft.server.v1_14_R1.PacketPlayOutSetCooldown;

public class EventCollector implements Listener {

	
	@EventHandler
	public void onPressSpell(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
			
		
		ItemStack is = p.getInventory().getItemInMainHand();
		boolean refined = false;
		boolean traitor = false;
		if (is != null) {
			if (is.hasItemMeta()) {
				if (is.getItemMeta().hasDisplayName()) {
					
					if (is.getItemMeta().getDisplayName().equals("§7Verbranntes Buch")) {
						SoundUtils.playSound(Sound.ENTITY_GENERIC_BURN, p.getLocation(), 1, 0.5F);
						is.setAmount(0);
						return;
					}
					if (!NBTUtils.getNBT("Cooldown", is).equals("")) {
						if (Double.parseDouble(NBTUtils.getNBT("Cooldown", is)) > 0) {

							return;
						}
						
						if (!NBTUtils.getNBT("Spell", is).equals("true")) {
							return;
						}
						
						String name = is.getItemMeta().getDisplayName();
						if (name.contains("§2")) {
							refined = true;
						}
						
						name = name.substring(2, name.length());
						name = name.replace(" ", "");
						try {
							name = "spells.spells." + name;
							// Bukkit.broadcastMessage("F" + s);
							Class clazz = Class.forName(name);
							Spell sp = (Spell) clazz.newInstance();
							sp.refined = true;
							if (!sp.traitorSpell) {
								
							
							is = NBTUtils.setNBT("Cooldown", "" + sp.cooldown + "", is);
							is = NBTUtils.setNBT("MaxCooldown", "" + sp.cooldown, is);
							is = NBTUtils.setNBT("OriginalName", is.getItemMeta().getDisplayName(), is);
							}
							sp.castSpell(p, is.getItemMeta().getDisplayName());
							if (sp.traitorSpell) {
								is = new ItemStack(Material.BOOK);
								ItemMeta m = is.getItemMeta();
								m.setDisplayName("§7Verbranntes Buch");
								is.setItemMeta(m);
							}
							
								p.getInventory().setItemInMainHand(is);
							
						} catch (Exception ex) {
							ex.printStackTrace(System.out);
						}
						;

					} else {
						
						if (!NBTUtils.getNBT("Spell", is).equals("true")) {
							return;
						}
						String name = is.getItemMeta().getDisplayName();
						if (name.contains("§2")) {
							refined = true;
						}
						
						name = name.substring(2, name.length());

						name = name.replace(" ", "");
						try {
							name = "spells.spells." + name;
							// Bukkit.broadcastMessage("F" + s);
							Class clazz = Class.forName(name);
							Spell sp = (Spell) clazz.newInstance();
							sp.refined = true;
							is = NBTUtils.setNBT("Cooldown", "" + sp.cooldown + "", is);
							is = NBTUtils.setNBT("MaxCooldown", "" + sp.cooldown, is);
							is = NBTUtils.setNBT("OriginalName", is.getItemMeta().getDisplayName(), is);
							sp.castSpell(p, is.getItemMeta().getDisplayName());

							if (sp.traitorSpell) {
								is = new ItemStack(Material.BOOK);
								ItemMeta m = is.getItemMeta();
								m.setDisplayName("§7Verbranntes Buch");
								is.setItemMeta(m);
								Bukkit.broadcastMessage("T");
							}
							
								p.getInventory().setItemInMainHand(is);
							
							
							

						} catch (Exception ex) {
							ex.printStackTrace(System.out);
						}
						;
					}
					

				}

			}

		}
		}

	}
	@EventHandler
	public void onPayRespectF(PlayerSwapHandItemsEvent e) {
		Spell.pressingF.add(e.getPlayer());
		e.setCancelled(true);
	}
	@EventHandler
	public void plsDontLeave(VehicleExitEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		
		e.setCancelled(true);

	}

	@EventHandler
	public void onSolidify(EntityChangeBlockEvent e) {

		e.getEntity().remove();
		e.setCancelled(true);
	}

	@EventHandler
	public void cancelGilding(EntityToggleGlideEvent e) {
		if (Spell.gliding.contains(e.getEntity())) {
			e.setCancelled(true);
		}
	}

}
