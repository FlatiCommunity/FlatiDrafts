package dev.jcsoftware.utils;

import org.bukkit.*;
import org.json.simple.*;

public class MojangUtils {

    public interface GetTextureResponse{
        void handle(String texture, String signature);
    }

    public void getUUIDForPlayerName(String playerName, UUIDResponceCallback response) {
        get("https://api.mojang.com/users/profiles/minecraft/" + playerName, (uuidReply) -> {
            if(uuidReply == null) {
                response.handle(null);
                return;
            }

            String uuidString = uuidReply.getString("id");
            if(uuidString == null) {
                response.handle(null);
                return;
            }
            response.handle(uuidString);
        });
    }

    public void getTextureAndSignatureFromUUID(String uuidString, GetTextureResponse response) {
        get("https://sessionserver.mojang.com/session/minecraft/profile/" + uuidString + "?unsigned=false", (profileReply) -> {
            if (!profileReply.has("properties")) {
                response.handle(null, null);
                return;
            }

            JSONObject properties = profileReply.getJSONArray("properties").getJSONObject(0);
            String texture = properties.getString("value");
            String signature = properties.getString("signature");
            response.handle(texture, signature);
        });
    }

    private void get(String url, JSONResponseCallback callback) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

        });
    }
}
