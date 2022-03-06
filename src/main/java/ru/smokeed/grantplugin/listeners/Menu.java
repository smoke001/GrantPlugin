package ru.smokeed.grantplugin.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

/**
 * author smokeed_
 * project GrantPlugin
 */

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.smokeed.grantplugin.Main;
import ru.smokeed.grantplugin.util.ChatUtil;

import java.util.ArrayList;
import java.util.List;

    public class Menu implements Listener {

        public static void applyInventory(Player player) {

            int slots = Main.getInstance().getConfig().getInt("INVENTORY.SIZE");
            String name = Main.getInstance().getConfig().getString("INVENTORY.TITLE");

            Inventory inv = Bukkit.createInventory(null, slots, ChatUtil.prefixed(name));

            for (String section : Main.getInstance().getConfig().getConfigurationSection("RANKS").getKeys(false)) {
                String material = Main.getInstance().getConfig().getString("RANKS." + section + ".MATERIAL");
                String displayname = Main.getInstance().getConfig().getString("RANKS." + section + ".DISPLAY-NAME");
                int slot = Main.getInstance().getConfig().getInt("RANKS." + section + ".SLOT");
                int data = Main.getInstance().getConfig().getInt("RANKS." + section + ".DATA");
                List<String> lore = new ArrayList<String>();
                for (String list : Main.getInstance().getConfig().getStringList("RANKS." + section + ".LORE")) {
                    lore.add(ChatUtil.prefixed(list));
                }


                ItemStack stack = new ItemStack(Material.valueOf(material), 1, (byte) data);
                ItemMeta meta = stack.getItemMeta();
                meta.setDisplayName(ChatUtil.prefixed(displayname));
                meta.setLore(lore);
                stack.setItemMeta(meta);

                inv.setItem(slot, stack);

            }
            player.openInventory(inv);
        }
        @EventHandler
        public void onClick(InventoryClickEvent e) {
            Player player = (Player) e.getWhoClicked();
            String name = Main.getInstance().getConfig().getString("INVENTORY.TITLE");


            if (e.getWhoClicked() instanceof Player) {
                if (e.getClickedInventory() == null) return;
                if (e.getClickedInventory().getTitle().equals(ChatUtil.prefixed(name))) {
                    if (e.getCurrentItem() != null) {
                        e.setCancelled(true);
                    }
                    for (String section : Main.getInstance().getConfig().getConfigurationSection("RANKS").getKeys(false)) {

                        String material = Main.getInstance().getConfig().getString("RANKS." + section + ".MATERIAL");
                        String displayname = Main.getInstance().getConfig().getString("RANKS." + section + ".DISPLAY-NAME");
                        ItemStack item = e.getCurrentItem();


                        if (item != null && item.getType().equals(Material.valueOf(material)) && item.getItemMeta().getDisplayName().equals(ChatUtil.prefixed(displayname))) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Main.getInstance().getConfig().getString("RANKS." + section + ".COMMAND").replaceAll("%player%",
                                    Main.getInstance().p.getName()));
                        }

                    }
                    player.closeInventory();
                    player.sendMessage(ChatUtil.prefixed(Main.getInstance().getConfig().getString("MESSAGES.NEW-RANK")).replaceAll("%player%", Main.getInstance().p.getName()));
                }
            }
        }

    }