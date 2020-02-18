package weapons;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.menu.ItemMenu;
import esze.menu.ItemMenuIcon;
import esze.utils.Actionbar;
import esze.utils.ItemStackUtils;
import esze.utils.NBTUtils;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.NBTTagInt;
import net.minecraft.server.v1_14_R1.NBTTagList;
import net.minecraft.server.v1_14_R1.NBTTagString;

public class WeaponMenu extends ItemMenu{
	
	public static HashMap<Player,String> items = new  HashMap<Player,String>();
	
	public WeaponMenu(Player p) {
		super(1, "�cArsenal");
		
		if (items.containsKey(p)) {
			addClickableItem(1, 1, Material.WOODEN_SWORD, "�cSchwert","�e---",items.get(p).contains("Schwert"));
			addClickableItem(2, 1, Material.BOW, "�cBogen","�e---",items.get(p).contains("Bogen"));
			addClickableItem(3, 1, Material.HEART_OF_THE_SEA, "�cFokussph�re","�e---",items.get(p).contains("Fokussph�re"));
		}
		else {
			addClickableItem(1, 1, Material.WOODEN_SWORD, "�cSchwert","�e---");
			addClickableItem(2, 1, Material.BOW, "�cBogen","�e---");
			addClickableItem(3, 1, Material.HEART_OF_THE_SEA, "�cFokussph�re","�e---");
		}
		
		
	}

	
	/*
	public static int calcTraitorMenuSize() {
		int spellCount = SpellList.traitorSpells.size();
		int size = 0;
		
		while (size<spellCount) {
			size+=9;
		}
		return size;
	}
	*/
	
	@Override
	public void clicked(ItemMenuIcon icon, Player p) {
		
		items.put(p, icon.getName());
		p.sendMessage("Du hast " +icon.getName() +"�r ausgew�hlt!");
		ItemMeta im = icon.getItemMeta();
		im.addEnchant(Enchantment.LURE, 1, true);
		im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		icon.setItemMeta(im);
		p.closeInventory();
		
	}
	
	public static boolean running = true;
	public static void deliverItems() {
		running = true;
		for (Player p : items.keySet()) {
			WeaponAbilitys.charge1.put(p, 0);
			WeaponAbilitys.charge2.put(p, 0);
			if (items.get(p).contains("Bogen")) {
				ItemStack is = new ItemStack(Material.BOW);
				ItemMeta im = is.getItemMeta();
				im.setUnbreakable(true);
				is.setItemMeta(im);
				p.getInventory().addItem(is);
			
				
				new BukkitRunnable() {
					int i = 0;
					int time = 0;
					@Override
					public void run() {
						
						String text = "�cSammlung: �e" +WeaponAbilitys.charge1.get(p)+"�r --- "+ "�cK�cher �e" +WeaponAbilitys.charge2.get(p);
						if (!running) {
							this.cancel();
						}
						
						
						if (time > 3) {
							
							WeaponAbilitys.charge1.put(p, WeaponAbilitys.charge1.get(p)-1);
							WeaponAbilitys.charge2.put(p, WeaponAbilitys.charge2.get(p)+1);
							time = 0;
						}
						
						if (p.isSneaking() && WeaponAbilitys.charge1.containsKey(p) && WeaponAbilitys.charge1.get(p)>0) {
							if (time == 0)
								text = "�cSammlung: " +WeaponAbilitys.charge1.get(p)+" �r>>> "+ "�cK�cher " +WeaponAbilitys.charge2.get(p);
							if (time == 1)
								text = "�cSammlung: " +WeaponAbilitys.charge1.get(p)+" �r)>> "+ "�cK�cher " +WeaponAbilitys.charge2.get(p);
							if (time == 2)
								text = "�cSammlung: " +WeaponAbilitys.charge1.get(p)+" �r>)> "+ "�cK�cher " +WeaponAbilitys.charge2.get(p);
							if (time == 3)
								text = "�cSammlung: " +WeaponAbilitys.charge1.get(p)+" �r>>) "+ "�cK�cher " +WeaponAbilitys.charge2.get(p);
							time++;
							
						}
						
						
						new Actionbar(text).send(p);
						// TODO Auto-generated method stub
						
					}
				}.runTaskTimer(main.plugin, 5,5);
			}
			
			if (items.get(p).contains("Schwert")) {
				ItemStack is = ItemStackUtils.attackSpeedify(ItemStackUtils.createItemStack(Material.WOODEN_SWORD, 1, 0, "�cSchwert", null, true));
				p.getInventory().setItem(0, is);
			}
			if (items.get(p).contains("Fokussph�re")) {
				ItemStack is = ItemStackUtils.attackSpeedify(ItemStackUtils.createItemStack(Material.HEART_OF_THE_SEA, 1, 0, "�cFokussph�re", null, true));
				is = ItemStackUtils.attackDamage(is, 2);
				new BukkitRunnable() {
					int i = 0;
					int time = 0;
					@Override
					public void run() {
						if (WeaponAbilitys.lastLaunched.containsKey(p)) {
							String spellname = WeaponAbilitys.lastLaunched.get(p).replace("spells.spells.", "");
							if (!WeaponAbilitys.cd.contains(p))
							new Actionbar("�bFokussph�re: "+"�c"+spellname).send(p);
						}
						if (!running) {
							this.cancel();
						}
						
					}
				}.runTaskTimer(main.plugin, 5, 5);
				p.getInventory().setItem(0, is);
			}
		}
	}
	
	public static void stopLoop() {
		running = false;
	}

}