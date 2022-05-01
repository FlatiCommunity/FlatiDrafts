package dev.jcsoftware.manager;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import dev.jcsoftware.items.*;
import org.bukkit.inventory.*;
import java.util.concurrent.*;
import org.bukkit.configuration.*;
import org.bukkit.event.inventory.*;
import org.bukkit.configuration.file.*;

public class ChestManager implements Listener {

    private Set<Location> openedChests = new HashSet<>();
    private List<LootItem> lootItems = new ArrayList<>();

    public ChestManager(FileConfiguration lootConfig) {
        ConfigurationSection itemsSection = lootConfig.getConfigurationSection("lootItems");

        if(itemsSection == null) {
            Bukkit.getLogger().severe("Please setup your `lootItems` in the config.yml!");
            return;
        }

        itemsSection.getKeys(false).forEach(key -> {
            ConfigurationSection section = itemsSection.getConfigurationSection(key);
            lootItems.add(new LootItem(section));
        });
    }

    @EventHandler
    public void onChestOpen(InventoryOpenEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();

        if(holder instanceof Chest) {
            Chest chest = (Chest) holder;
            if(hasBeenOpened(chest.getLocation())) return;

            markAsOpened(chest.getLocation());
            fill(chest.getBlockInventory());
        } else if(holder instanceof DoubleChest) {
            DoubleChest chest = (DoubleChest) holder;
            if(hasBeenOpened(chest.getLocation())) return;

            markAsOpened(chest.getLocation());
            fill(chest.getInventory());
        }
    }

    public void fill(Inventory inventory) {
        inventory.clear();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<LootItem> used = new HashSet<>();

        for(int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++) {
            LootItem randomItem = lootItems.get(
                    random.nextInt(lootItems.size())
            );
           if(used.contains(randomItem)) continue;
           used.add(randomItem);

           if(randomItem.shouldFill(random)) {
               ItemStack itemStack = randomItem.make(random);
               inventory.setItem(slotIndex, itemStack);
           }
        }
    }

    public void markAsOpened(Location location) {
        openedChests.add(location);
    }

    public boolean hasBeenOpened(Location location) {
        return openedChests.contains(location);
    }

    public void resetChests() {
        openedChests.clear();
    }

}
