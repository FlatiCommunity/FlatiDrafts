package dev.unkw301;

import org.bukkit.*;
import org.bukkit.event.*;
import dev.unkw301.listeners.*;
import org.bukkit.plugin.java.*;

public class ExampleLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

}
