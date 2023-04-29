package net.ramuremo.minigameexample.listener;

import net.ramuremo.minigameexample.asset.ItemAsset;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

public final class ExplosionStickListener implements Listener {

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(ItemAsset.EXPLOSION_STICK.toItemStack());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();

        if (item == null) return;
        if (!ItemAsset.EXPLOSION_STICK.toItemStack().isSimilar(item)) return;

        final Block block = player.getTargetBlock(null, 100);

        block.getLocation().getWorld().createExplosion(block.getLocation(), 5);
    }
}
