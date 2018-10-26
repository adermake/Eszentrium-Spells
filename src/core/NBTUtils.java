package core;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;

import net.minecraft.server.v1_13_R2.ItemStack;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagString;

public class NBTUtils {

	
	
	
	public static org.bukkit.inventory.ItemStack setNBT(String key,String value,org.bukkit.inventory.ItemStack is) {
		ItemStack nms = CraftItemStack.asNMSCopy(is);
		NBTTagCompound n = (nms.hasTag()) ? nms.getTag() : new NBTTagCompound();
		n.set(key, new NBTTagString(value));
		nms.setTag(n);
		is = CraftItemStack.asBukkitCopy(nms);
		return is;
	}
	public static String getNBT(String key,org.bukkit.inventory.ItemStack is) {
		ItemStack nms = CraftItemStack.asNMSCopy(is);
		NBTTagCompound n = (nms.hasTag()) ? nms.getTag() : new NBTTagCompound();
		
		return n.getString(key);
		
	}
}
