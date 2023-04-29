package net.ramuremo.minigameexample;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nonnull;

public final class Util {

    public static void registerListeners(@Nonnull Plugin plugin, @Nonnull Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
