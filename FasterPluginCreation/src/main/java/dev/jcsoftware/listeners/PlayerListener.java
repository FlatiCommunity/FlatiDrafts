package dev.jcsoftware.listeners;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.RED + "User " + e.getPlayer().getName() + " joined to server!");
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.RED + "User " + e.getPlayer().getName() + " joined to server!");
    }

}
