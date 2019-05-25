package esze.main;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;

/*import net.minecraft.server.v1_13_R1.MinecraftKey;
import net.minecraft.server.v1_13_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_13_R1.Particle;
import net.minecraft.server.v1_13_R1.ParticleParam;
import net.minecraft.server.v1_13_R1.ParticleParamItem;*/

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

import esze.analytics.solo.SaveUtils;
import esze.enums.GameType;
import esze.enums.GameType.TypeEnum;
import esze.enums.Gamestate;
import esze.listeners.Block;
import esze.listeners.CancelClick;
import esze.listeners.Damage;
import esze.listeners.Death;
import esze.listeners.DropPickup;
import esze.listeners.FBoost;
import esze.listeners.Hunger;
import esze.listeners.Interact;
import esze.listeners.Join;
import esze.listeners.Move;
import esze.listeners.Schwertwurf;
import esze.listeners.Spawn;
import esze.map.JumpPad;
import esze.map.JumpPadHandler;
import esze.map.MapSelect;
import esze.map.MapUtils;
import esze.menu.Menu;
import esze.utils.ChatUtils;
import esze.utils.ItemStackUtils;
import esze.utils.LibUtils;
import net.minecraft.server.v1_14_R1.MinecraftServer;
import spells.spellcore.Cooldowns;
import spells.spellcore.EventCollector;
import spells.spellcore.SpellList;

public class main extends JavaPlugin {
	
	public static main plugin;
	public static String discord_TOKEN = "lolxd";
	public static String mapname;
	public static final String voiddamage = "void";
	public static HashMap<Player, String> damageCause = new HashMap<Player, String>();
	
	
	
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
		this.getCommand("analytics").setExecutor(new CommandReciever());
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		getServer().getPluginManager().registerEvents(new Death(), this);
		getServer().getPluginManager().registerEvents(new Damage(), this);
		getServer().getPluginManager().registerEvents(new Spawn(), this);
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
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			main.damageCause.put(p, ""); //Damage Cause
			p.setExp(0F);
			p.setLevel(0);
			p.setFoodLevel(20);
			p.setHealth(20);
			p.setMaxHealth(20);
			p.setWalkSpeed(0.2F);
			
			//Clears Inventory of Players
			if (p.getGameMode().equals(GameMode.SURVIVAL)) {
				p.getInventory().clear();
				p.teleport(new Location(Bukkit.getWorld("world"), 0, 105, 0));// teleport into Lobby
			}
			p.getInventory().setItem(8, ItemStackUtils.createItemStack(Material.MAP, 1, 0, "§3Map wählen", null, true));
			p.getInventory().setItem(7, ItemStackUtils.createItemStack(Material.DIAMOND, 1, 0, "§3Test", null, true));
		}
		//Discord.run(); DISCORD STUFF
	}
	
	@Override
	public void onDisable() {
		//SaveUtils.backup();
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
	
	public static String toStringCause(Player p) {
		String[] in = main.damageCause.get(p).split("-");
		String color = "§7";
		//Analysis 
		if (in.length == 1) {
			if (in[0].equals("")) {
				color += p.getName() + " starb!"; //no Cause
			}
			if (in[0].equals(voiddamage)) {
				color += p.getName() + " fiel ins Void!"; //Void
			}
		}
		if (in.length == 2) {
			color += p.getName() + " wurde durch " + in[1] + " mit " + in[0] + " getötet!"; //Cause+Player
		}
		if (in.length == 3) {
			color += p.getName() + " wurde durch " + in[1] + " mit " + in[0] + " ins Void geworfen!"; //Cause+Player+void
		}
		return color;
	}
}
