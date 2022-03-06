package ru.smokeed.grantplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import ru.smokeed.grantplugin.commands.GrantCommand;
import ru.smokeed.grantplugin.listeners.Menu;

/**
 * author smokeed_
 * project GrantPlugin
 */

public class Main extends JavaPlugin {

    private static Main instance;
    public Player p;

    @EventHandler
    public void onLoad() {
        instance = this;
    }

    public void onEnable() {

        this.saveDefaultConfig();

        this.getCommand("grant").setExecutor(new GrantCommand());
        Bukkit.getPluginManager().registerEvents(new Menu(), this);
    }

    public static Main getInstance() {
        return instance;
    }
}