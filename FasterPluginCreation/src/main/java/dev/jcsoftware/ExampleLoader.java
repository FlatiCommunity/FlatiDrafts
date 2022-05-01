package dev.jcsoftware;

import org.reflections.*;
import org.bukkit.event.*;
import java.lang.reflect.*;
import org.bukkit.plugin.java.*;
import dev.jcsoftware.commands.impl.*;

public class ExampleLoader extends JavaPlugin {

    @Override
    public void onEnable() {
        String packageName = getClass().getPackage().getName();

        new Reflections(packageName + ".listeners").getSubTypesOf(Listener.class).forEach(clazz -> {
            try {
                Listener listener = clazz
                        .getDeclaredConstructor()
                        .newInstance();
                getServer().getPluginManager().registerEvents(listener, this);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        new Reflections(packageName + ".commands").getSubTypesOf(PluginCommand.class).forEach(clazz -> {
            try {
                PluginCommand pluginCommand = clazz
                        .getDeclaredConstructor()
                        .newInstance();
                RegisterCommand.reg(this, pluginCommand, new String[] {"test"}, "skam", "/test");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        // todo: unregister commands
    }
}
