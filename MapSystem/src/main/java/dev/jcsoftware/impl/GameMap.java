package dev.jcsoftware.impl;

import org.bukkit.*;

public interface GameMap {

    boolean load();
    void unload();
    boolean restoreFromSource();

    boolean isLoaded();
    World getWorld();

}
