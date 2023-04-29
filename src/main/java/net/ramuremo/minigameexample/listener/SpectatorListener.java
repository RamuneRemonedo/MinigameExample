package net.ramuremo.minigameexample.listener;

import net.ramuremo.minigameexample.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.annotation.Nonnull;

public final class SpectatorListener implements Listener {

    private final GameManager manager;

    public SpectatorListener(@Nonnull GameManager manager) {
        this.manager = manager;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getPlayer();

        if (manager.redTeam.hasPlayer(player)) {
            manager.redTeam.removePlayer(player);

            player.spigot().respawn();
            player.setGameMode(GameMode.SPECTATOR);
        } else if (manager.blueTeam.hasPlayer(player)) {
            manager.blueTeam.removePlayer(player);

            player.spigot().respawn();
            player.setGameMode(GameMode.SPECTATOR);
        }
    }
}
