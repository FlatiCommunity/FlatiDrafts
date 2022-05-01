package space.owlsskam;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;
import space.owlsskam.listeners.*;

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
