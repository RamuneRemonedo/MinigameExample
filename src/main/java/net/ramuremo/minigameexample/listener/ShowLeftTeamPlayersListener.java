package net.ramuremo.minigameexample.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.ramuremo.minigameexample.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class ShowLeftTeamPlayersListener implements Listener {

    private final GameManager manager;

    public ShowLeftTeamPlayersListener(GameManager manager) {
        this.manager = manager;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player player = event.getPlayer();

        if (manager.redTeam.hasPlayer(player) || manager.blueTeam.hasPlayer(player)) {
            Bukkit.broadcast(
                    Component.text("残りの赤チーム: " + manager.redTeam.getSize())
                            .color(TextColor.color(0xFF0000))
            );
            Bukkit.broadcast(
                    Component.text("残りの青チーム: " + manager.blueTeam.getSize())
                            .color(TextColor.color(0xFF))
            );
        }
    }
}
