package esze.utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.DataWatcher;
import net.minecraft.server.v1_14_R1.DataWatcherRegistry;
import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_14_R1.PlayerInteractManager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftServer;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class CorpseUtils {
	
	private static ArrayList<Integer> allCorpses = new ArrayList<Integer>();
	
	public static ArrayList<Integer> getAllCorpseIDs(){
		return (ArrayList<Integer>)allCorpses.clone();
	}
	
	public static String getCorpseName(int cID){
		return ((CraftWorld)Bukkit.getWorld("world")).getHandle().getEntity(cID).getName();
	}
	
	public static int spawnCorpseForAll(Player player, Location loc){
		ArrayList<Player> players = new ArrayList<Player>();
		for(Player p : Bukkit.getOnlinePlayers()){
			players.add(p);
		}
		return spawnCorpseForPlayers(player, loc, players);
	}
	
	public static int spawnCorpseForPlayers(Player player, Location loc, List<Player> showTo){
		int cID = 0;
		Property textures = (Property) ((CraftPlayer) player).getHandle().getProfile().getProperties().get("textures").toArray()[0];
		String signature = textures.getSignature();
		String value = textures.getValue();

		GameProfile gameProfile = new GameProfile(player.getUniqueId(), player.getName());
		gameProfile.getProperties().put("textures", new Property("textures", value, signature));

		EntityPlayer entityPlayer = new EntityPlayer(
		((CraftServer) Bukkit.getServer()).getServer(),
		((CraftWorld) player.getWorld()).getHandle(), gameProfile, new PlayerInteractManager(((CraftWorld) player.getWorld()).getHandle()));
		entityPlayer.setPosition(loc.getX(), loc.getY(), loc.getZ());

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer));
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer));

		Location bed = loc.clone().add(1, 0, 0);
		entityPlayer.e(new BlockPosition(bed.getX(), bed.getY(), bed.getZ()));

		DataWatcher watcher = entityPlayer.getDataWatcher();
		Byte b = 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40; // each of the overlays (cape, jacket, sleeves, pants, hat)
		watcher.set(DataWatcherRegistry.a.a(15), (byte) b); // 15 is the value for Spigot 1.14 apparently (even though on wiki.vg it says it's 13, but it isn't)

		for(Player all : showTo){
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityMetadata(cID = entityPlayer.getId(), watcher, false));
		}
		allCorpses.add(cID);
		return cID;
	}
	
	public static void teleportCorpse(int cID, Location loc){
		((CraftWorld)loc.getWorld()).getHandle().getEntity(cID).locX = loc.getX();
		((CraftWorld)loc.getWorld()).getHandle().getEntity(cID).locY = loc.getY();
		((CraftWorld)loc.getWorld()).getHandle().getEntity(cID).locZ = loc.getZ();
		for(Player all : Bukkit.getOnlinePlayers()){
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(((CraftWorld)loc.getWorld()).getHandle().getEntity(cID)));
		}
	}
	
	
	public static void removeCorpseForPlayers(int cID, List<Player> removeFrom){
		for(Player all : removeFrom){
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(cID));
		}
	}
	
	
	public static void removeCorpseForAll(int cID){
		for(Player all : Bukkit.getOnlinePlayers()){
			((CraftPlayer) all).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(cID));
		}
	}
	
	
	
	

}