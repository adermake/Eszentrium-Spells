package esze.main;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

/*import net.minecraft.server.v1_13_R1.MinecraftKey;
import net.minecraft.server.v1_13_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_13_R1.Particle;
import net.minecraft.server.v1_13_R1.ParticleParam;
import net.minecraft.server.v1_13_R1.ParticleParamItem;*/

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

import esze.enums.GameType;
import esze.enums.GameType.TypeEnum;
import esze.enums.Gamestate;
import esze.listeners.Block;
import esze.listeners.Damage;
import esze.listeners.Death;
import esze.listeners.DropPickup;
import esze.listeners.FBoost;
import esze.listeners.Hunger;
import esze.listeners.Interact;
import esze.listeners.Join;

import esze.listeners.Move;
import esze.listeners.Schwertwurf;
import esze.listeners.CancelClick;
import esze.map.JumpPad;
import esze.map.JumpPadHandler;
import esze.map.MapSelect;
import esze.menu.Menu;
import esze.utils.ChatUtils;
import esze.utils.LibUtils;
import net.minecraft.server.v1_13_R2.MinecraftServer;
import spells.spellcore.Cooldowns;
import spells.spellcore.EventCollector;

public class main extends JavaPlugin {
	
	public static main plugin;
	public static String discord_TOKEN = "lolxd";
	public static String mapname;
	
	@Override
	public void onEnable() {
		
		plugin = this;
		//R
		
		this.getServer().getPluginManager().registerEvents(new EventCollector(), this);
		Cooldowns.startCooldownHandler();
		
		ConfigurationSerialization.registerClass(JumpPad.class);
		 //R
		/*ParticleParam p = new ParticleParamItem((Particle<ParticleParamItem>) Particle.REGISTRY.get(new MinecraftKey("hugeexplosion")), null);
		
		PacketPlayOutWorldParticles w = new PacketPlayOutWorldParticles(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
		Particle.REGISTRY.get(new MinecraftKey("<particlename>"))*/
		
		this.getCommand("showpads").setExecutor(new CommandReciever());
		this.getCommand("loadpads").setExecutor(new CommandReciever());
		this.getCommand("unload").setExecutor(new CommandReciever());
		this.getCommand("spell").setExecutor(new CommandReciever());
		this.getCommand("game").setExecutor(new CommandReciever());
		this.getCommand("maps").setExecutor(new CommandReciever());
		this.getCommand("setspawn").setExecutor(new CommandReciever());
		this.getCommand("setitem").setExecutor(new CommandReciever());
		this.getCommand("setlobby").setExecutor(new CommandReciever());
		this.getCommand("downloadfile").setExecutor(new CommandReciever());
		this.getCommand("ping").setExecutor(new CommandReciever());
		this.getCommand("setmode").setExecutor(new CommandReciever());
		this.getCommand("removemap").setExecutor(new CommandReciever());
		this.getCommand("gamemode").setExecutor(new CommandReciever());
		this.getCommand("gm").setExecutor(new CommandReciever());
		this.getCommand("itemname").setExecutor(new CommandReciever());
		this.getCommand("setjumppad").setExecutor(new CommandReciever());
		this.getCommand("removepads").setExecutor(new CommandReciever());
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		getServer().getPluginManager().registerEvents(new Death(), this);
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Hunger(), this);
		getServer().getPluginManager().registerEvents(new Interact(), this);
		getServer().getPluginManager().registerEvents(new Schwertwurf(), this);
		getServer().getPluginManager().registerEvents(new FBoost(), this);
		getServer().getPluginManager().registerEvents(new CancelClick(), this);
		getServer().getPluginManager().registerEvents(new Block(), this);
		getServer().getPluginManager().registerEvents(new DropPickup(), this);
		getServer().getPluginManager().registerEvents(new JumpPadHandler(), this);
		getServer().getPluginManager().registerEvents(new MapSelect(), this);
		getServer().getPluginManager().registerEvents(new Menu(), this);
		
		
		JumpPadHandler.start();
		Gamestate.setGameState(Gamestate.LOBBY);
		SpellList.registerSpells();
		if(getConfig().contains("settings.mode")){
			GameType.setTypeByName(getConfig().getString("settings.mode"));
		}else{
			GameType.setTypeByEnum(TypeEnum.SOLO);
		}
		
		LobbyBackgroundRunnable.start();
		
		MinecraftServer.getServer().setMotd(ChatUtils.centerMotD("§cEsze§3Remastered").substring(2)+"\n§8"+ChatUtils.centerMotD("Der Klassiker neu aufgelegt!").substring(3));
		
		LibUtils.initlibs();
		//Discord.run(); DISCORD STUFF
	}
	
	
	public String objToJson(Object obj){
		Class<?> objClass = obj.getClass();

	    Field[] fields = objClass.getFields();
	    JsonObject Jobj = new JsonObject();
	    for(Field field : fields) {
	        String name = field.getName();
	        Object value = "ERROR-objToJsonMethod";
			try {
				value = field.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Jobj.addProperty(name, value.toString());
	    }
	    return Jobj.toString();
	}

}
