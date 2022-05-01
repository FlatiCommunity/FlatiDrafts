package dev.jcsoftware;

import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.plugin.java.*;
import dev.jcsoftware.manager.*;
import org.jetbrains.annotations.*;

public class ChestLoader extends JavaPlugin {

    private ChestManager chestManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        chestManager = new ChestManager(getConfig());

        getServer().getPluginManager().registerEvents(chestManager, this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("reset")) {
            chestManager.resetChests();
            s.sendMessage("Chests reset.");
        }

        return true;
    }

}
