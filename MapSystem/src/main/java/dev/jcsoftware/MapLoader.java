package dev.jcsoftware;

import java.io.*;
import org.bukkit.plugin.java.*;
import dev.jcsoftware.map.*;
import dev.jcsoftware.impl.*;
import dev.jcsoftware.commands.*;

public class MapLoader extends JavaPlugin {

    private GameMap map;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();

        File gameMapsFolder = new File(getDataFolder(), "gameMaps");
        if(!gameMapsFolder.exists()) {
            gameMapsFolder.mkdirs();
        }

        map = new LocalGameMap(gameMapsFolder, "Lighthouse", true);

        getCommand("reset").setExecutor(new CommandReset(map));
        getCommand("warp").setExecutor(new CommandWarp(map));
    }

    @Override
    public void onDisable() {
        map.unload();
    }

}
