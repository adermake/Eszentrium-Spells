package esze.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftCow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import esze.main.main;

public class TTTCorpse implements Listener{
	
	private Player player;
	private String username;
	private Inventory inv = null;
	private int corpseID = 0;
	private ArrayList<Entity> cows;
	private Player carrier = null;
	private BukkitTask carryTask;
	
	public TTTCorpse(Player player, boolean withInv) {
		this.player = player;
		this.username = player.getName();
		
		this.cows = new ArrayList<Entity>();
		if(withInv){
			this.inv = Bukkit.createInventory(null, 9*4, username + "s Inventar");
		
			int i = 0;
			for(ItemStack isOld : player.getInventory()){
				if(isOld != null && (isOld.getType() == Material.ENCHANTED_BOOK || isOld.getType() == Material.BOOK)){
					ItemStack is = NBTUtils.setNBT("Cooldown", "0", isOld);
					String spellname = NBTUtils.getNBT("OriginalName", isOld);
					ItemMeta meta = is.getItemMeta();
					meta.setDisplayName(spellname);
					is.setItemMeta(meta);
					is.setType(Material.ENCHANTED_BOOK);
					
					inv.setItem(i, is);
					i++;
				}
			}
		}
		
		main.plugin.getServer().getPluginManager().registerEvents(this, main.plugin);
		
	}
	
	@EventHandler
	public void onClick(PlayerInteractEntityEvent e){
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		
		if(this.cows.contains(ent)){
			
			if(p.isSneaking()){
				carry(p);
			}else{
				if(inv != null)
					p.openInventory(inv);
			}
		}
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == inv){
			p.getInventory().addItem(e.getCurrentItem());
			e.setCancelled(true);
			
			ArrayList<HumanEntity> viewers = new ArrayList<HumanEntity>();
			viewers.addAll(inv.getViewers());
			for(HumanEntity viewer : viewers){
				viewer.closeInventory();
			}
			this.inv = null;
		}
	}
	
	public void carryRunner(){
		carryTask = Bukkit.getScheduler().runTaskTimer(main.plugin, new Runnable() {
			
			@Override
			public void run() {
				
				if(carrier != null){
					Location loc = carrier.getLocation().clone().add(1, 2, -0.3);
					//loc.setPitch(0);
					//loc.setYaw(0);
					
					
					
					CorpseUtils.teleportCorpse(corpseID, loc);
					
					cows.get(0).teleport(loc.clone().add(1, 0, 0));
					cows.get(0).teleport(loc.clone().add(0, 0, 0));
				}else{
					stopCarrying();
				}
				
			}
		}, 0, 1);
	}
	
	public void stopCarrying(){
		if(carryTask != null){
			carryTask.cancel();
		}
		
		
	}
	
	public void carry(Player carrier){
		if(this.carrier == null){
			this.carrier = carrier;
			carryRunner();
		}
	}
	
	
	public void spawn(){
		corpseID = CorpseUtils.spawnCorpseForAll(player, player.getLocation());
		
		Entity e = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.COW);
		((CraftCow)e).setAI(false);
		((CraftCow)e).setSilent(true);
		((CraftCow)e).setGravity(false);
		//((CraftCow)e).getHandle().collides = false;
		((CraftCow)e).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000*1000*1000, 100), false);
		((CraftCow)e).setInvulnerable(true);
		
		Entity e2 = player.getLocation().getWorld().spawnEntity(player.getLocation().clone().add(1, 0, 0), EntityType.COW);
		((CraftCow)e2).setAI(false);
		((CraftCow)e2).setSilent(true);
		((CraftCow)e2).setGravity(false);
		//((CraftCow)e).getHandle().collides = false;
		((CraftCow)e2).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000*1000*1000, 100), false);
		((CraftCow)e2).setInvulnerable(true);
		
		cows.add(e);
		cows.add(e2);
		
	}

}
