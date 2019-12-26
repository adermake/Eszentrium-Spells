package esze.voice;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import esze.main.main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite.Channel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;


public class Discord {
	
	public static JDA jda;
	public static HashMap<Player, Member> muted = new HashMap<Player, Member>();
	public static VoiceChannel c;
	public static Guild g;
	static long gID = 429733093050679306L;
	static long rID = 1;
	public static void run(){

		try {
			jda = new JDABuilder(main.discord_TOKEN).setActivity(Activity.watching("people die")).build().awaitReady();
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
		}
		
		g = jda.getGuildById(429733093050679306L);
		c = jda.getVoiceChannelById(621375797953036328L);
		AudioManager manager = g.getAudioManager();
        manager.openAudioConnection(c);
		
		
	}
	public static void logout() {
		jda.shutdownNow();
	}
	public static void test(Channel c) {
		Bukkit.broadcastMessage(""+c.getId());
	}
	
	
	
	public static void unMuteAll(){
		for(Player p : ((HashMap<Player, Member>)muted.clone()).keySet()){
			setMuted(p, false);
		}
	}
	
	public static void setMuted(Player player, boolean shouldMute){
		for(Member member : g.getMembers()) {
			User p = member.getUser();
			if(p.getName().equalsIgnoreCase(player.getName()) || (member.getNickname() != null && member.getNickname().equalsIgnoreCase(player.getName()))){
				
				member.mute(shouldMute).complete();
				if(shouldMute == false){
					muted.remove(player);
				}else{
					muted.put(player, member);
				}
			}
		}
	}
	
	public static void setStatus(Activity a){
		jda.getPresence().setActivity(a);
	}
 	
	/*public static void setMuted(Player player, boolean shouldMute){
		for (Flux<Guild> g : discordbot.getGuilds()) {
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
	
	/*
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
	
*/
	
}
