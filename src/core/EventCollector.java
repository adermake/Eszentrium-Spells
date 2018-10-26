package core;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import net.minecraft.server.v1_13_R2.PacketPlayOutSetCooldown;


public class EventCollector implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		

	}
	

	@EventHandler
	public void onPressSpell(PlayerInteractEvent e) {
		Player p = e.getPlayer();
	
		ItemStack is = p.getInventory().getItemInMainHand();
		boolean refined = false;
		if (is != null) {
			if (is.hasItemMeta()) {
				if (is.getItemMeta().hasDisplayName()) {
					if (!NBTUtils.getNBT("Cooldown", is).equals("")) {
						
					
					if (Double.parseDouble(NBTUtils.getNBT("Cooldown", is)) > 0) {
						
						return;
					}
						
						
						String name = is.getItemMeta().getDisplayName();
						if (name.contains("§2")) {
							refined = true;
						}
						name = name.substring(2, name.length());
						name = name.replace(" ", "");
						try {
							name = "spells." + name;
							// Bukkit.broadcastMessage("F" + s);
							Class clazz = Class.forName(name);
							Spell sp = (Spell) clazz.newInstance();
							sp.refined = true;
							is = NBTUtils.setNBT("Cooldown", "" + sp.cooldown+"", is);
							is = NBTUtils.setNBT("MaxCooldown", "" + sp.cooldown, is);
							is = NBTUtils.setNBT("OriginalName", is.getItemMeta().getDisplayName(), is);
							sp.castSpell(p, is.getItemMeta().getDisplayName());
							p.getInventory().setItemInMainHand(is);

						} catch (Exception ex) {
							ex.printStackTrace(System.out);
						}
						;
					
					}
					else {
						String name = is.getItemMeta().getDisplayName();
						if (name.contains("§2")) {
							refined = true;
						}
						name = name.substring(2, name.length());

						name = name.replace(" ", "");
						try {
							name = "spells." + name;
							// Bukkit.broadcastMessage("F" + s);
							Class clazz = Class.forName(name);
							Spell sp = (Spell) clazz.newInstance();
							sp.refined = true;
							is = NBTUtils.setNBT("Cooldown", "" + sp.cooldown+"", is);
							is = NBTUtils.setNBT("MaxCooldown", "" + sp.cooldown, is);
							is = NBTUtils.setNBT("OriginalName", is.getItemMeta().getDisplayName(), is);
							sp.castSpell(p, is.getItemMeta().getDisplayName());
							
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
	
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		Bukkit.broadcastMessage("CANCEL");
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
