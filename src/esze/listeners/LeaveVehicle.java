package esze.listeners;

import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class LeaveVehicle implements Listener { 

	
	@EventHandler
	public void onLeaveVehicle(VehicleExitEvent e){
		
			if (e.getExited() instanceof Phantom) {
				e.setCancelled(true);
			}
		
		}
 }
