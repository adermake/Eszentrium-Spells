package esze.utils;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R1.IChatBaseComponent;
import net.minecraft.server.v1_16_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_16_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_16_R1.PlayerConnection;

public class TabList {
	
	String header;
	String footer;
	
	public TabList(String header, String footer) {
		this.header = header;
		this.footer = footer;
	}
   
    public void send(Player player) {
        CraftPlayer craftplayer = (CraftPlayer)player;
        PlayerConnection connection =
          craftplayer.getHandle().playerConnection;
          IChatBaseComponent hj = ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{text: '" + header + "'}"));
          IChatBaseComponent fj = ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{text: '" + footer + "'}"));
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try
        {
          Field headerField = packet.getClass().getDeclaredField("a");
          headerField.setAccessible(true);
          headerField.set(packet, hj);
          headerField.setAccessible(!headerField.isAccessible());
         
          Field footerField = packet.getClass().getDeclaredField("b");
          footerField.setAccessible(true);
          footerField.set(packet, fj);
          footerField.setAccessible(!footerField.isAccessible());
        }
        catch (Exception localException) {}
        connection.sendPacket(packet);
        //DISABLED = NEW CLOUDSYSTEM
      }
    
    
    public static void setPlayerlistHeader(Player player, String header) {
        CraftPlayer cplayer = (CraftPlayer) player;
        PlayerConnection connection = cplayer.getHandle().playerConnection;
       
        IChatBaseComponent top = ChatSerializer.a("{text: '" + header + "'}");
       
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
       
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, top);
            headerField.setAccessible(!headerField.isAccessible());
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }
}
