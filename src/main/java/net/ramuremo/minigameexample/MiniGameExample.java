package net.ramuremo.minigameexample;

import org.bukkit.plugin.java.JavaPlugin;

public final class MiniGameExample extends JavaPlugin {

    @Override
    public void onEnable() {
        new GameManager(this);
        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("The plugin has been disabled.");
    }
}
