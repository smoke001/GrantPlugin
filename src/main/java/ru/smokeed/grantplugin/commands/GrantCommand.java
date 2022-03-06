package ru.smokeed.grantplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.smokeed.grantplugin.Main;
import ru.smokeed.grantplugin.listeners.Menu;
import ru.smokeed.grantplugin.util.ChatUtil;

/**
 * author smokeed_
 * project GrantPlugin
 */

public class GrantCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatUtil.prefixed("&cThis is a player only command!"));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("grantplugin.menu")) {
            player.sendMessage(ChatUtil.prefixed("&cУ меня нет прав"));
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(ChatUtil.prefixed("&cИспользование: &e/grant <player>"));
            return false;
        }

        Main.getInstance().p = Bukkit.getPlayer(args[0]);

        if (Main.getInstance().p == null) {
            player.sendMessage(ChatUtil.prefixed("&cЭммм его нету на сервере чееееел"));
            return false;
        }

        Menu.applyInventory(player);
        return false;
    }
}