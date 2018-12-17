package esze.utils;

import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemStackUtils {
	
	public static ItemStack createItemStack(@Nonnull Material mat, @Nonnull int amount, @Nonnull int durability, String name, List<String> lore, @Nonnull boolean unbreakable){
		ItemStack i = new ItemStack(mat, amount, (short)durability);
		ItemMeta meta = i.getItemMeta();
		if(name != null)meta.setDisplayName(name);
		if(lore != null)meta.setLore(lore);
		meta.setUnbreakable(unbreakable);
		i.setItemMeta(meta);
		return i;
	}
	
	/*public static ItemStack createSkullStack(@Nonnull int amount, String name, String owner, List<String> lore, @Nonnull boolean unbreakable){
		ItemStack i = new ItemStack(Material.LEGACY_SKULL_ITEM, amount, (short)3);
		SkullMeta meta = (SkullMeta) i.getItemMeta();
		if(name != null)meta.setDisplayName(name);
		if(lore != null)meta.setLore(lore);
		if(owner != null)meta.setOwner(owner);
		meta.setUnbreakable(unbreakable);
		i.setItemMeta(meta);
		return i;
	}*/
	
	public static ItemStack createLeatherArmor(@Nonnull Material mat, @Nonnull int amount, @Nonnull int durability, String name, List<String> lore, @Nonnull boolean unbreakable, Color color){
		ItemStack i = new ItemStack(mat, amount, (short)durability);
		LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
		if(name != null)meta.setDisplayName(name);
		if(lore != null)meta.setLore(lore);
		meta.setColor(color);
		meta.setUnbreakable(unbreakable);
		i.setItemMeta(meta);
		return i;
	}

}
