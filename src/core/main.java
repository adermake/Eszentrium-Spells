package core;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.packetlistener.PacketListenerAPI;
import org.inventivetalent.packetlistener.handler.PacketHandler;
import org.inventivetalent.packetlistener.handler.ReceivedPacket;
import org.inventivetalent.packetlistener.handler.SentPacket;






public class main extends JavaPlugin {
	
	public static main plugin;
	

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {

		plugin = this;
		
		this.getServer().getPluginManager().registerEvents(new EventCollector(), this);
		Cooldowns.startCooldownHandler();
		
		//XXX->x
		
 
		

	}

	@Override
	public void onDisable() {
		

	}

}
