package spells.spellcore;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;

import esze.utils.NBTUtils;
import esze.utils.SoundUtils;

public class Spelldrop implements Listener {
	
	
	public static HashMap<ArmorStand,ArmorStand> items = new HashMap<ArmorStand,ArmorStand>();
	
	
	public Spelldrop(Location loc) {
		
		Bukkit.broadcastMessage("dont forget to implement Listenr of Spelldrop");
		ArmorStand a = (ArmorStand) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ARMOR_STAND);
		a.setGravity(false);
		a.setVisible(false);
		a.setInvulnerable(true);
		a.setRightArmPose(EulerAngle.ZERO);
        a.setRightArmPose(a.getRightArmPose().add(0, 0.1, 0));
		a.setItemInHand(getRandomSpell());
		
		a.setCustomName(a.getItemInHand().getItemMeta().getDisplayName());
		
		ArmorStand name = (ArmorStand) Bukkit.getWorld("world").spawnEntity(loc.clone().add(0.30, -1.5, 0), EntityType.ARMOR_STAND);
		name.setGravity(false);
		name.setVisible(false);
		name.setInvulnerable(true);
		name.setCustomName(a.getItemInHand().getItemMeta().getDisplayName());
		items.put(a, name);
	}
	
	
	
	public static ItemStack getRandomSpell() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta im = item.getItemMeta();
		
		im.setDisplayName(SpellList.getRandomSpell().name);
		item.setItemMeta(im);
		item = NBTUtils.setNBT("Spell", "true", item);
		return item;
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() != GameMode.SURVIVAL)
			return;
		
		for ( ArmorStand a : items.keySet()) {
			
			if (a.getLocation().distance(p.getLocation())<1.5) {
				p.getInventory().addItem(a.getItemInHand());
				items.get(a).remove();
				SoundUtils.playSound( Sound.ENTITY_ITEM_PICKUP, a.getLocation());
				a.remove();
				
			}
		}
	}
	
	@EventHandler
	public void onManipulate(PlayerArmorStandManipulateEvent e){
		e.setCancelled(true);
	}
	
}
