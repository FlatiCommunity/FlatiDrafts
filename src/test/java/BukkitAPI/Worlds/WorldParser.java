import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Простенький креатор миров
 * При этом в `plugin.yml` придётся прописать `loadbefore`,
 *  ведь `load: STARTUP` не получится заюзать к слову.
 */

public class WorldParser extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().getStringList("worlds").forEach(world -> (new WorldCreator(world)).createWorld());
    }
}