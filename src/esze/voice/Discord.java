package esze.voice;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import esze.main.main;
import sx.blah.discord.Discord4J;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.util.RequestBuffer;

public class Discord {
	
	public static IDiscordClient discordbot;
	public static HashMap<Player, IUser> muted = new HashMap<Player, IUser>();
	public static IVoiceChannel channel;
	
	public static void run(){
		System.out.println("D1");
		discordbot = new ClientBuilder()
        .withToken(main.discord_TOKEN)
        .login();
		
		System.out.println("D2");
			Bukkit.getScheduler().runTaskLater(main.plugin, new Runnable() {
				
			@Override
			public void run() {
				System.out.println("DEBUGGGING");
				for(IGuild g : discordbot.getGuilds()){
					System.out.println("GUILDS ::::::::::::::::::::::"+g);
					channel = g.getVoiceChannelByID(621375797953036328L);
					channel.join();
				}
				System.out.println("DONE");
			}
		}, 20*3);	
		
		
		
	}
	
	public static void setMuted(Player player, boolean shouldMute){
		for (IGuild g : discordbot.getGuilds()) {
			channel = g.getVoiceChannelByID(621375797953036328L);
		}
		
		RequestBuffer.request(() -> {
			if (channel == null) {
				System.out.print("CHANNEL IS NULL");
			}
			if (channel.getGuild() == null) {
				System.out.print("GUILD IS NULL");
			}
			for(IUser p : channel.getGuild().getUsers()){
				if (!channel.getConnectedUsers().contains(p)) {
					continue;
				}
				if(p.getName().equalsIgnoreCase(player.getName()) || (p.getNicknameForGuild(channel.getGuild()) != null && p.getNicknameForGuild(channel.getGuild()).equalsIgnoreCase(player.getName()))){
					channel.getGuild().setMuteUser(p, shouldMute);
					if(shouldMute == false){
						muted.remove(p);
					}else{
						muted.put(player, p);
					}
				}
			}
        });
	}
	
	public static void unMuteAll(){
		for(Player p : ((HashMap<Player, IUser>)muted.clone()).keySet()){
			setMuted(p, false);
		}
	}
	
	public static void setStatus(String what){
		discordbot.changePresence(StatusType.ONLINE, ActivityType.WATCHING, what);
	}
	
	public static boolean isLoggedIn(){
		return discordbot.isLoggedIn();
	}
	
	public static String getVersion(){
		return Discord4J.VERSION;
	}
	

}
