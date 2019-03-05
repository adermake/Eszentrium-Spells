package spells.spellcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.NBTUtils;

public class Cooldowns {

	
	
	public static void startCooldownHandler() {
		int barcount = 10;
		int downticker = 1;
		new BukkitRunnable() {
			public void run() {
				
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					
					for (int slot = 0;slot<p.getInventory().getSize();slot++) {
						ItemStack i = p.getInventory().getItem(slot);
						if (i ==  null ) {
							continue;
						}
						String nbtData = NBTUtils.getNBT("Cooldown", i);
						if (!(nbtData.equals(""))) {
							double cooldown = Double.parseDouble(nbtData);
							cooldown -= downticker;
							if (cooldown<=0) {
								
								ItemMeta im = i.getItemMeta();
								im.setDisplayName(NBTUtils.getNBT("OriginalName", i));
								i.setItemMeta(im);
								i.setType(Material.ENCHANTED_BOOK);
								i = NBTUtils.setNBT("Cooldown", "0", i);
								p.getInventory().setItem(slot, i);
							}
							else {
								
								double maxCd = Double.parseDouble(NBTUtils.getNBT("MaxCooldown", i));
								ItemMeta im = i.getItemMeta();
								String currentName = NBTUtils.getNBT("OriginalName", i);
								double per = cooldown/maxCd;
								currentName = currentName + " §7<[";
								int anticount = 0;
								for (int count = 0;count<= (int)(per*barcount);count++) {
									currentName = currentName + "§4|";
									anticount++;
								}
								anticount = barcount-anticount;
								for (int count = 0;count<= anticount;count++) {
									currentName = currentName + "§8|";
								}
								currentName = currentName + "§7]>";
								im.setDisplayName(currentName);
								i.setItemMeta(im);
								i.setType(Material.BOOK);
								i = NBTUtils.setNBT("Cooldown", ""+cooldown, i);
								p.getInventory().setItem(slot, i);
							}
						}
						
					}
					
				}
			}
		}.runTaskTimer(main.plugin, 1, downticker);
		
		
	}
	
	
	public static void refundCurrentSpell(Player p) {
		ItemStack is = p.getInventory().getItemInMainHand();
		is = NBTUtils.setNBT("Cooldown", "0", is);
		p.getInventory().setItemInMainHand(is);
		Bukkit.broadcastMessage("xx");
	}
	
}
