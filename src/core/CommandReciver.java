package core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandReciver implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equals("spell")) {
            ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(args[0]);
            is.setItemMeta(im);
            
            
            player.getInventory().addItem(is);
            return true;
            }
        }
		
		return false;
		
	}

}
