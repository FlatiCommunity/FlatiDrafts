package dev.jcsoftware.commands;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.attribute.*;
import dev.jcsoftware.commands.impl.CommandInfo;
import dev.jcsoftware.commands.impl.PluginCommand;

@CommandInfo(name = "heal", permission = "heal", requiresPlayers = true)
public class CommandHeal extends PluginCommand {
    
    @Override
    public void execute(Player player, String[] args) {
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.sendMessage(ChatColor.GOLD + "Healed!");
    }

}
