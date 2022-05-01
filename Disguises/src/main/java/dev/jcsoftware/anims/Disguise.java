package dev.jcsoftware.anims;

import lombok.Getter;
import org.bukkit.entity.*;
import com.mojang.authlib.*;
import dev.jcsoftware.helper.*;
import org.bukkit.plugin.java.*;

import java.lang.reflect.InvocationTargetException;

public class Disguise {

    @Getter private String name;
    @Getter private String texture;
    @Getter private String signature;

    private String originalName;
    private String originalTexture;
    private String originalSignature;

    public Disguise(String name, String texture, String signature) {
        this.name = name;
        this.texture = texture;
        this.signature = signature;
    }

    public boolean apply(JavaPlugin source, Player player) {
        GameProfile gameProfile = null;

        try{
            gameProfile = NMSHelper.getInstance().getGameProfile(player);
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if(gameProfile == null) {
            // todo: code...
        }

        return gameProfile != null;
    }

}
