package dev.jcsoftware.helper;

import lombok.*;
import java.util.*;
import java.lang.reflect.*;
import com.mojang.authlib.*;
import org.bukkit.entity.*;
import com.mojang.authlib.properties.*;

public class NMSHelper {

    @Getter private static final NMSHelper instance = new NMSHelper();
    private NMSHelper() {}

    public Object getHandle(Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return player.getClass().getMethod("getHandle").invoke(player);
    }

    public GameProfile getGameProfile(Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object handle = getHandle(player);

        return (GameProfile) handle.getClass()
                .getSuperclass()
                .getDeclaredMethod("getProfile")
                .invoke(handle);
    }

    public Property getTextureProperty(GameProfile profile) {
        Optional<Property> texturesProperty = profile.getProperties().get("textures").stream().findFirst();
        return texturesProperty.orElse(null);
    }
}
