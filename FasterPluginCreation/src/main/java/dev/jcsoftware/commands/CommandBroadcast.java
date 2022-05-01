package dev.jcsoftware.commands;

import org.bukkit.*;
import org.bukkit.command.*;
import dev.jcsoftware.commands.impl.CommandInfo;
import dev.jcsoftware.commands.impl.PluginCommand;

@CommandInfo(name = "broadcast", requiresPlayers = false)
public class CommandBroadcast extends PluginCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Bukkit.broadcastMessage(stringArrayToString(args));
    }

    protected String stringArrayToString(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            stringBuilder.append(args[i]);
            if(i != args.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
