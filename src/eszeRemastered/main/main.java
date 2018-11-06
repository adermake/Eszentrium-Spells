package eszeRemastered.main;

import java.lang.reflect.Field;

/*import net.minecraft.server.v1_13_R1.MinecraftKey;
import net.minecraft.server.v1_13_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_13_R1.Particle;
import net.minecraft.server.v1_13_R1.ParticleParam;
import net.minecraft.server.v1_13_R1.ParticleParamItem;*/

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

import eszeRemastered.enums.GameType;
import eszeRemastered.enums.GameType.TypeEnum;
import eszeRemastered.listeners.Block;
import eszeRemastered.listeners.Damage;
import eszeRemastered.listeners.DropPickup;
import eszeRemastered.listeners.FBoost;
import eszeRemastered.listeners.Hunger;
import eszeRemastered.listeners.Interact;
import eszeRemastered.listeners.Join;
import eszeRemastered.listeners.JumpPad;
import eszeRemastered.listeners.Move;
import eszeRemastered.listeners.Schwertwurf;
import eszeRemastered.listeners.Wheat;
import eszeRemastered.map.MapSelect;
import eszeRemastered.utils.ChatUtils;
import eszeRemastered.utils.LibUtils;
import eszeRemastered.voice.Discord;
import net.minecraft.server.v1_13_R2.MinecraftServer;

public class main extends JavaPlugin {
	
	public static main plugin;
	public static String discord_TOKEN = "MzY0NDMzMzA2Nzk3OTk4MTAy.DTv0Wg.pS_sw8q8ctTy-jkUUB7a3R1t3AE";
	public static String mapname;
	
	@Override
	public void onEnable() {
		
		
		/*ParticleParam p = new ParticleParamItem((Particle<ParticleParamItem>) Particle.REGISTRY.get(new MinecraftKey("hugeexplosion")), null);
		
		PacketPlayOutWorldParticles w = new PacketPlayOutWorldParticles(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
		Particle.REGISTRY.get(new MinecraftKey("<particlename>"))*/
		plugin = this;
		this.getCommand("game").setExecutor(new cmd());
		this.getCommand("maps").setExecutor(new cmd());
		this.getCommand("setspawn").setExecutor(new cmd());
		this.getCommand("setitem").setExecutor(new cmd());
		this.getCommand("setlobby").setExecutor(new cmd());
		this.getCommand("downloadfile").setExecutor(new cmd());
		this.getCommand("ping").setExecutor(new cmd());
		this.getCommand("setmode").setExecutor(new cmd());
		this.getCommand("removemap").setExecutor(new cmd());
		this.getCommand("itemname").setExecutor(new cmd());
		
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Hunger(), this);
		getServer().getPluginManager().registerEvents(new Interact(), this);
		getServer().getPluginManager().registerEvents(new Schwertwurf(), this);
		getServer().getPluginManager().registerEvents(new FBoost(), this);
		getServer().getPluginManager().registerEvents(new Wheat(), this);
		getServer().getPluginManager().registerEvents(new Block(), this);
		getServer().getPluginManager().registerEvents(new DropPickup(), this);
		getServer().getPluginManager().registerEvents(new JumpPad(), this);
		getServer().getPluginManager().registerEvents(new MapSelect(), this);
		
		if(getConfig().contains("settings.mode")){
			GameType.setTypeByName(getConfig().getString("settings.mode"));
		}else{
			GameType.setTypeByEnum(TypeEnum.SOLO);
		}
		
		LobbyBackgroundRunnable.start();
		
		MinecraftServer.getServer().setMotd(ChatUtils.centerMotD("§cEsze§3Remastered").substring(2)+"\n§8"+ChatUtils.centerMotD("Der Klassiker neu aufgelegt!").substring(3));
		
		LibUtils.initlibs();
		Discord.run();
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
