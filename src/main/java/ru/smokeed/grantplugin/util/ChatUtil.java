package ru.smokeed.grantplugin.util;

import org.bukkit.ChatColor;

/**
 * author smokeed_
 * project GrantPlugin
 */

public class ChatUtil {

    public static String prefixed(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
