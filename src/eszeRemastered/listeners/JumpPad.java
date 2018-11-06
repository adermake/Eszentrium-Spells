package eszeRemastered.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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

import eszeRemastered.main.main;

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
						/*ParticleEffect.VILLAGER_HAPPY.send(Bukkit.getOnlinePlayers(), p.getLocation().getX()+0.5,
								p.getLocation().getY(), p.getLocation().getZ(), 0, 0, 0.4, 5, 10);
						ParticleEffect.VILLAGER_HAPPY.send(Bukkit.getOnlinePlayers(), p.getLocation().getX()-0.5,
								p.getLocation().getY(), p.getLocation().getZ(), 0, 0, 0.4, 5, 10);
						ParticleEffect.VILLAGER_HAPPY.send(Bukkit.getOnlinePlayers(), p.getLocation().getX(),
								p.getLocation().getY(), p.getLocation().getZ()+0.5, 0.4, 0, 0, 5, 10);
						ParticleEffect.VILLAGER_HAPPY.send(Bukkit.getOnlinePlayers(), p.getLocation().getX(),
								p.getLocation().getY(), p.getLocation().getZ()-0.5, 0.4, 0, 0, 5, 10);*/
						if (x/4<t) {
							this.cancel();
						}
						
					}
				}.runTaskTimer(main.plugin, 0, 4);
				
			}
			
		}
	}
	
	public static Location lookAt(Location loc, Location lookat) {
        //Clone the loc to prevent applied changes to the input loc
        loc = loc.clone();

        // Values of change in distance (make it relative)
        double dx = lookat.getX() - loc.getX();
        double dy = lookat.getY() - loc.getY();
        double dz = lookat.getZ() - loc.getZ();

        // Set yaw
        if (dx != 0) {
            // Set yaw start value based on dx
            if (dx < 0) {
                loc.setYaw((float) (1.5 * Math.PI));
            } else {
                loc.setYaw((float) (0.5 * Math.PI));
            }
            loc.setYaw((float) loc.getYaw() - (float) Math.atan(dz / dx));
        } else if (dz < 0) {
            loc.setYaw((float) Math.PI);
        }

        // Get the distance from dx/dz
        double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));

        // Set pitch
        loc.setPitch((float) -Math.atan(dy / dxz));

        // Set values, convert to degrees (invert the yaw since Bukkit uses a different yaw dimension format)
        loc.setYaw(-loc.getYaw() * 180f / (float) Math.PI);
        loc.setPitch(loc.getPitch() * 180f / (float) Math.PI);

        return loc;
    }

}
