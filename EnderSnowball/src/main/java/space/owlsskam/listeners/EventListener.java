package space.owlsskam.listeners;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;

public class EventListener implements Listener {

    @EventHandler
    public void onUseSnowball(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();
        if(projectile.getShooter() == null) return;

        if(projectile.getShooter() instanceof Player){
            Player player = (Player) projectile.getShooter();
            if(player == null) return;
            if(projectile.getType() != EntityType.SNOWBALL) return;

            player.teleport(projectile);
        }
    }

}
