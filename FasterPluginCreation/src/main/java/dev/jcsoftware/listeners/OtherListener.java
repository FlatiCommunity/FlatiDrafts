package dev.jcsoftware.listeners;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import org.bukkit.event.server.*;

public class OtherListener implements Listener {

    @EventHandler
    public void on(PluginDisableEvent e) {
        PluginDescriptionFile pluginDescriptionFile = e.getPlugin().getDescription();

        if(pluginDescriptionFile.getName().length() >= 5) {
            Bukkit.getConsoleSender().sendMessage(pluginDescriptionFile.getName());
        }
    }

}
