package dev.jcsoftware.items;

import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.concurrent.*;
import dev.jcsoftware.utils.*;
import org.bukkit.enchantments.*;
import org.bukkit.configuration.*;
import org.bukkit.inventory.meta.*;

public class LootItem {

    private Material material;
    private String customName;
    private Map<Enchantment, Integer> enchantmentToLevelMap = new HashMap<>();
    private double chance;

    private int minAmount;
    private int maxAmount;

    public LootItem(ConfigurationSection section) {
        Material material;

        try{
            material = Material.valueOf(section.getString("material"));
        }catch(Exception e) {
            material = Material.AIR;
        }

        this.material = material;
        this.customName = section.getString("name");

        ConfigurationSection enchantmentsSection = section.getConfigurationSection("echantments");
        if(enchantmentsSection != null) {
            for(String enchantmentKey : enchantmentsSection.getKeys(false)) {
                Enchantment enchantment = Enchantment.getByKey( // WARNING: This code syntax MC version >1.14
                    NamespacedKey.minecraft(
                        enchantmentKey.toLowerCase()
                    )
                );

                if(enchantment != null) {
                    int level = enchantmentsSection.getInt(enchantmentKey);
                    enchantmentToLevelMap.put(enchantment, level);
                }
            }
        }

        this.chance = section.getDouble("chance");
        this.minAmount = section.getInt("minAmount");
        this.maxAmount = section.getInt("maxAmount");
    }

    public boolean shouldFill(Random random) {
        return random.nextDouble() < chance;
    }

    public ItemStack make(ThreadLocalRandom random) {
        int amount = random.nextInt(minAmount, maxAmount + 1);
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(customName != null) {
            itemMeta.setDisplayName(
                    ServerUtils.s(customName)
            );
        }

        if(!enchantmentToLevelMap.isEmpty()) {
            for(Map.Entry<Enchantment, Integer> enchantEntry : enchantmentToLevelMap.entrySet()) {
                itemMeta.addEnchant(
                        enchantEntry.getKey(),
                        enchantEntry.getValue(),
                        true
                );
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
