package esze.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import esze.main.main;
import esze.utils.ParUtils;

public class JumpPad implements Listener{
	
	public static void start(){
		Bukkit.getScheduler().runTaskTimer(main.plugin, new Runnable() {
			
			@Override
			public void run() {
				for(LivingEntity e : Bukkit.getWorlds().get(0).getLivingEntities()){
					if(!(e instanceof Player) && !(e instanceof ArmorStand)){
						checkJumpPad(e);
					}
				}
			}
		}, 0, 1);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.ADVENTURE) return;
		checkJumpPad(p);
	}
	
	
	public static void checkJumpPad(Entity p){
		if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK) {
	
			
			if (p.getLocation().add(0,-2,0).getBlock().getType() == Material.CHEST || p.getLocation().add(0,-2,0).getBlock().getType() == Material.TRAPPED_CHEST) {
				InventoryHolder c = (InventoryHolder) p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getState();
				
				new BukkitRunnable() {
					int timer = 0;
					public void run() {
						timer++;
						p.setVelocity(p.getVelocity().setY(0.3));
						
						if(timer > 30){
							this.cancel();
						}
						if(p instanceof Player){
							if(((Player)p).isSneaking()){
								int i = 0;
								for(ItemStack is : c.getInventory().getContents()){
									if(is != null){
										i++;
									}
								}
								final int x = i;
								for (Player pl : Bukkit.getOnlinePlayers()) {
									pl.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 1);
								}
								p.setVelocity(p.getLocation().getDirection().multiply(i/4));
								new BukkitRunnable() {
									int t = 0;
									
									public void run() {
										t = t + 1;
										//ParticleEffect.CRIT.send(Bukkit.getOnlinePlayers(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 0, 0, 0, 0, 10);
							
										if (x/4<t) {
											this.cancel();
										}
										
									}
								}.runTaskTimer(main.plugin, 0, 4);
								this.cancel();
							}
						}
						
					}
				}.runTaskTimer(main.plugin,1,1);
				
			}
			
		}
		
		if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
		
			if (p.getLocation().add(0,-2,0).getBlock().getType() == Material.CHEST || p.getLocation().add(0,-2,0).getBlock().getType() == Material.TRAPPED_CHEST) {
				InventoryHolder c = (InventoryHolder) p.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getState();
				
				int i = 0;
				for(ItemStack is : c.getInventory().getContents()){
					if(is != null){
						i++;
					}
				}
				final double x = i;
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.playSound(p.getLocation(), Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1, 1);
				}
				p.setVelocity(p.getVelocity().setY(x/5));
				new BukkitRunnable() {
					int t = 0;
					
					public void run() {
						t = t + 1;
						ParUtils.createParticleSqareHorizontal(Particle.VILLAGER_HAPPY, p.getLocation(), 0.5);
						if (x/4<t) {
							this.cancel();
						}
						
					}
				}.runTaskTimer(main.plugin, 0, 4);
				
			}
			
		}
	}


}
