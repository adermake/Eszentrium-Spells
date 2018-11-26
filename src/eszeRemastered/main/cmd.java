package eszeRemastered.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eszeRemastered.enums.GameType;
import eszeRemastered.enums.GameType.TypeEnum;
import eszeRemastered.enums.Gamestate;
import eszeRemastered.map.MapMenu;
import eszeRemastered.voice.Discord;
import net.minecraft.server.v1_13_R2.EntityPlayer;

public class cmd implements CommandExecutor, TabCompleter{
	
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String [] args) {
		final Player p = (Player) sender;			
				
				
				if(cmd.getName().startsWith("game")) {
					if(p.isOp()){
						if(args.length == 1){
							if(args[0].equalsIgnoreCase("start")){
								LobbyCountdownRunnable.start();
							}else if(args[0].equalsIgnoreCase("stop")){
								LobbyCountdownRunnable.stop();
							}else if(args[0].equalsIgnoreCase("info")){
								p.sendMessage("§8| §3Discord4J §7- "+Discord.getVersion() + " - " + (Discord.isLoggedIn() ? "§aCONNECTED" : "§cDISCONNECTED"));
							}else{
								p.sendMessage("§8| §c/game <start/stop/info>");
							}
							
						}else{
							p.sendMessage("§8| §c/game <start/stop/info>");
						}
					}
		        }
				
				if(cmd.getName().startsWith("maps")) {
					if(p.isOp()){
						MapMenu.sendOverview(p);
					}
		        }
				
				if(cmd.getName().startsWith("setspawn")) {
					if(p.isOp()){
						if(args.length == 2){
							try{
								String name = args[0];
								int i = Integer.parseInt(args[1]);
								main.plugin.getConfig().set("maps."+name+"."+i, p.getLocation());
								main.plugin.saveConfig();
								p.sendMessage("§8| §7Der Spawn §6"+i+" §7für Map §6"+name+" §7wurde gesetzt!");
							}catch(Exception e){
								p.sendMessage("§8| §cEin Fehler ist aufgetreten. Vielleicht ist deine Spawnnummer keine Zahl?");
							}
						}else{
							p.sendMessage("§8| §c/setspawn <Map> <Spawnnummer>");
						}
					}
		        }
				
				
				//TODO REWRITE
				if(cmd.getName().startsWith("setitem")) {
					if(p.isOp()){
						if(args.length == 2 || args.length == 3){
							String name = args[0];
							String mat = args[1];
							short dur = 0;
							try{
								dur = Short.parseShort(args[2]);
							}catch(Exception e){ }
							main.plugin.getConfig().set("maps."+name+".material", Material.getMaterial(mat).toString());
							main.plugin.getConfig().set("maps."+name+".durability", dur);
							main.plugin.saveConfig();
							p.sendMessage("§8| §7Das Item für Map §6"+name+" §7ist jetzt §3"+Material.getMaterial(mat).toString()+"§7("+dur+")!");
							
						}else{
							p.sendMessage("§8| §c/setitem <Map> <Material (NICHT ID)> [Haltbarkeit]");
						}
					}
		        }
				
				if(cmd.getName().startsWith("setlobby")) {
					if(p.isOp()){
						main.plugin.getConfig().set("lobby.loc", p.getLocation());
						main.plugin.saveConfig();
						p.sendMessage("§8| §7Die Lobbyposition wurde gespeichert!");
					}
		        }
				
				if(cmd.getName().startsWith("downloadfile")) {
					if(p.isOp()){
						if(args.length == 2){
							String filename = args[0];
							String urlstring = args[1];
							Bukkit.getScheduler().runTaskAsynchronously(main.plugin, new Runnable() {
								
								@Override
								public void run() {
									File f = new File(main.plugin.getDataFolder().getAbsolutePath()+"/downloads/");
									if(!f.exists() || !f.isDirectory()){
										f.mkdirs();
									}
									try {
										saveFile(new URL(urlstring), main.plugin.getDataFolder().getAbsolutePath()+"/downloads/"+filename, p);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
							
						}
					}
		        }
				
				if(cmd.getName().startsWith("ping")) {
					
					if(args.length == 1){
						String name = args[0];
						OfflinePlayer statss = Bukkit.getOfflinePlayer(name);
						if(statss.isOnline()){
							Player stats = Bukkit.getPlayer(name);
							p.sendMessage("§8| §7"+name+"'s Ping: §6"+getPing(stats));
						}else{
							p.sendMessage("§8| §cSpieler nicht online!");
						}
					}else if(args.length == 0){
						p.sendMessage("§8| §7Dein Ping: §6"+getPing(p));
					}else{
						p.sendMessage("§8| §cZu viele Parameter! /ping <Name>");
					}
				}
								
				if(cmd.getName().startsWith("setmode")) {
					if(p.isOp()){
						if(Gamestate.getGameState() == Gamestate.LOBBY){
							if(args.length == 1){
								String name = args[0];
								if(!GameType.setTypeByName(name)){
									p.sendMessage("§8| §cUngültiger Modus!");
									return false;
								}
								p.sendMessage("§8| §cModus geändert!");
								
							}else{
								p.sendMessage("§8| §c/setmode <Modus>");
							}
						}else{
							p.sendMessage("§8| §cSpielmodus nicht während dem Spiel änderbar!");
						}
					}
		        }
				
				if(cmd.getName().startsWith("removemap")) {
					if(p.isOp()){
						if(args.length == 1){
							String name = args[0];
							main.plugin.getConfig().set("maps."+name, null);
							main.plugin.saveConfig();
							MapMenu.sendOverview(p);
						}else{
							p.sendMessage("§8| §c/removemap <Map>");
						}
					}
		        }
				
				if(cmd.getName().equalsIgnoreCase("itemname")) {
					if(p.isOp()){
						String myString = "";
				        for(int i = 0; i < args.length; i++){
				            String arg = args[i] + " ";
				            myString = myString + arg;
				        }
				        String myStrings = myString.substring(0, myString.length() -1);
				        String s = ChatColor.translateAlternateColorCodes('&', myStrings);
				        ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
				        m.setDisplayName(s);
				        p.getInventory().getItemInMainHand().setItemMeta(m);
				        p.sendMessage("§8| §7Der Name wurde in "+myStrings+" §7geändert!");
					}
			}
				if (sender instanceof Player) {
		            Player player = (Player) sender;
		            if (cmd.getName().equals("spell")) {
		            ItemStack is = new ItemStack(Material.BOOK);
		            ItemMeta im = is.getItemMeta();
		            String name = args[0];
		            name = name.replace("&", "§");
		            im.setDisplayName(name);
		            is.setItemMeta(im);
		            
		            
		            player.getInventory().addItem(is);
		            return true;
		            }
		        }
				
				return false;
		}

	@Override
	public List<String> onTabComplete(CommandSender player, Command cmd, String cmdname, String[] args) {
		if(args.length == 1){
			if(cmdname.contains("esze")){
				List<String> to = new ArrayList<String>();
				List<String> from = new ArrayList<String>();
				from.add("start");
				from.add("stop");
				from.add("speedup");
				from.add("info");
				from.add("debug");
				from.add("reload");
				try{
					for(String s : from){
						String eing = args[0];
						String a = s;
						String eing2 = a.substring(0, eing.length());
						
						if(eing.equalsIgnoreCase(eing2)){
							to.add(a);
						}
					}
				}catch(Exception e){}
				return to;
			}else if(cmdname.contains("ping")){
				List<String> to = new ArrayList<String>();
				List<String> from = new ArrayList<String>();
				for(Player p : Bukkit.getOnlinePlayers()){
					from.add(p.getName());
				}
				try{
					for(String s : from){
						String eing = args[0];
						String a = s;
						String eing2 = a.substring(0, eing.length());
						
						if(eing.equalsIgnoreCase(eing2)){
							to.add(a);
						}
					}
				}catch(Exception e){}
				return to;
			}else if(cmdname.contains("setmode")){
					List<String> to = new ArrayList<String>();
					List<String> from = new ArrayList<String>();
					for(TypeEnum type : GameType.TypeEnum.values()){
						from.add(type.toString());
					}
					try{
						for(String s : from){
							String eing = args[0];
							String a = s;
							String eing2 = a.substring(0, eing.length());
							
							if(eing.equalsIgnoreCase(eing2)){
								to.add(a);
							}
						}
					}catch(Exception e){}
					return to;
			}else if(cmdname.contains("setitem")){
				List<String> to = new ArrayList<String>();
				List<String> from = new ArrayList<String>();
				for(String arena: main.plugin.getConfig().getConfigurationSection("maps").getKeys(false)){
					if(main.plugin.getConfig().get("maps."+arena)!=null){
						from.add(arena);
					}
				}
				try{
					for(String s : from){
						String eing = args[0];
						String a = s;
						String eing2 = a.substring(0, eing.length());
						
						if(eing.equalsIgnoreCase(eing2)){
							to.add(a);
						}
					}
				}catch(Exception e){}
				return to;
			}
		}else if(args.length == 2){
			if(cmdname.contains("setitem")){
				List<String> to = new ArrayList<String>();
				List<String> from = new ArrayList<String>();
				for(Material mat : Material.values()){
					from.add(mat.toString());
				}
				try{
					for(String s : from){
						String eing = args[1];
						String a = s;
						String eing2 = a.substring(0, eing.length());
						
						if(eing.equalsIgnoreCase(eing2)){
							to.add(a);
						}
					}
				}catch(Exception e){}
				return to;
			}
		}
		
		return null;
	}
	
	public static void saveFile(URL url, String file, Player p) throws IOException {
		p.sendMessage("§8| §7Die Verbindung zu §6"+url.toString()+" §7wird aufgebaut.");
		HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
        long completeFileSize = httpConnection.getContentLength();
        
	    InputStream in = url.openStream();
	    FileOutputStream fos = new FileOutputStream(new File(file));

	    p.sendMessage("§8| §7Starte Download.");
	    int length = -1;
	    long downloadedFileSize = 0;
	    byte[] buffer = new byte[1024];// buffer for portion of data from
	    // connection
	    double lastprog = 0;
	    while ((length = in.read(buffer)) > -1) {
	    	downloadedFileSize += length;
	    	double currentProgress = (double) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100d);
	    	currentProgress = round(currentProgress, 1);
	    	if(currentProgress != lastprog){
	    		p.sendMessage("§8| §7Lade Datei herunter... "+currentProgress+"% abgeschlossen");
	    		lastprog = currentProgress;
	    	}
	        fos.write(buffer, 0, length);
	    }
	    


	    fos.close();
	    in.close();
	    p.sendMessage("§8| §7Datei heruntergeladen.");
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		return ep.ping;
	}
				

}
