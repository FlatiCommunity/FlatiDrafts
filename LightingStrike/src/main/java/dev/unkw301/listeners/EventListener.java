package dev.unkw301.listeners;

import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;

public class EventListener implements Listener {

    @EventHandler
    public void onBlitz(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(p.getItemInHand() == null) return;
        if(p.getItemInHand().getType() != Material.BLAZE_ROD) return;
        if(e.getAction() != Action.LEFT_CLICK_AIR | e.getAction() != Action.LEFT_CLICK_BLOCK) return;

        Block block = p.getTargetBlock((Set<Material>) null, // do not remove set<material>
                500);
        block.getWorld().strikeLightning(block.getLocation());

        e.setCancelled(true);
    }

}
