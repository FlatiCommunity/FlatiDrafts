package dev.jcsoftware;

import java.util.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.plugin.java.*;
import dev.jcsoftware.executors.*;
import org.jetbrains.annotations.*;

public class ExampleLoader extends JavaPlugin {

    private RandomItemSpawnTask randomItemSpawnTask;
    private Material[] itemOptions;
    private int delay;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        List<String> materialItems = getConfig().getStringList("material");
        if(materialItems.size() == 0 || !getConfig().getBoolean("useList", false)) {
            itemOptions = Material.values();
        }else{
            itemOptions = new Material[materialItems.size()];
            for(int i = 0; i < materialItems.size(); i++) {
                String materialKey = materialItems.get(i);
                try{
                    itemOptions[i] = Material.valueOf(materialKey);
                }catch(Exception ignored) {
                    getLogger().warning("Invalid material type " + materialKey + ", changed to SNOWBALL!");
                    itemOptions[i] = Material.SNOWBALL;
                }
            }
        }

        delay = getConfig().getInt("delayBetweenSpawns", 60);

    }

    @Override
    public void onDisable() {
        if(isStarted()) randomItemSpawnTask.cancel();

        Bukkit.getScheduler().cancelTasks(this);
    }

    public boolean isStarted() {
        return randomItemSpawnTask != null && !randomItemSpawnTask.isCancelled();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender s, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("start")) {
            if(isStarted()) {
                s.sendMessage(ChatColor.RED + "Random item drops was already started");
                return true;
            }

            randomItemSpawnTask = new RandomItemSpawnTask(itemOptions);
            randomItemSpawnTask.runTaskTimer(this, 0, 20L * delay);

            Bukkit.broadcastMessage(ChatColor.GREEN + "Random item drops started!");
            return true;
        }

        if(cmd.getLabel().equalsIgnoreCase("end")) {
            if(!isStarted()) {
                s.sendMessage(ChatColor.RED + "Random item drops hasn't started!");
                return true;
            }
            randomItemSpawnTask.cancel();

            Bukkit.broadcastMessage(ChatColor.GREEN + "Random item drops stopped.");
            return true;
        }

        return true;
    }

}
