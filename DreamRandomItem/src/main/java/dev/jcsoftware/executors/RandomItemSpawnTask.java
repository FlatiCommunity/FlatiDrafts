package dev.jcsoftware.executors;

import org.bukkit.*;
import java.util.concurrent.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.*;

public class RandomItemSpawnTask extends BukkitRunnable {

    private final Material[] options;

    public RandomItemSpawnTask(Material[] options) {
        this.options = options;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Material randomMaterial = options[ThreadLocalRandom.current().nextInt(options.length - 1)];
            for(int i = 0; i < player.getInventory().getSize(); i++) {
                player.getWorld().dropItem(player.getLocation(), new ItemStack(randomMaterial, randomMaterial.getMaxStackSize()));
            }
        });

        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "\u279C ITEM DROP HAS ARRIVED!");
    }

}