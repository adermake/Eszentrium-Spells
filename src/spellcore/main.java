package spellcore;
import org.bukkit.plugin.java.JavaPlugin;


public class main extends JavaPlugin {
	
	public static main plugin;
	

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {

		plugin = this;
		
		this.getServer().getPluginManager().registerEvents(new EventCollector(), this);
		Cooldowns.startCooldownHandler();
		 this.getCommand("spell").setExecutor(new CommandReciver());
		//XXX->x HAAALO
		
 
		

	}

	@Override
	public void onDisable() {
		

	}

}
