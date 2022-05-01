package dev.jcsoftware.commands.impl;

import java.util.*;
import org.bukkit.*;
import java.lang.reflect.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.jetbrains.annotations.*;

public class RegisterCommand extends Command implements PluginIdentifiableCommand {

    protected Plugin plugin;
    protected final CommandExecutor owner;
    protected final Object registeredWith;

    public RegisterCommand(String[] aliases, String desc, String usage, CommandExecutor owner, Object registeredWith,
                           Plugin plugin) {
        super(aliases[0], desc, usage, Arrays.asList(aliases));
        this.owner = owner;
        this.plugin = plugin;
        this.registeredWith = registeredWith;
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        return this.owner.onCommand(sender, this, label, args);
    }

    public Object getRegisteredWith() {
        return this.registeredWith;
    }

    public static void reg(Plugin plugin, CommandExecutor executor, String[] aliases, String desc, String usage) {
        try {
            RegisterCommand reg = new RegisterCommand(aliases, desc, usage, executor, new Object(), plugin);
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(plugin.getDescription().getName(), reg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}