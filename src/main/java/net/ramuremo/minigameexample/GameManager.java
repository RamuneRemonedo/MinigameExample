package net.ramuremo.minigameexample;

import net.kyori.adventure.text.Component;
import net.ramuremo.minigameexample.listener.ExplosionStickListener;
import net.ramuremo.minigameexample.listener.ShowLeftTeamPlayersListener;
import net.ramuremo.minigameexample.listener.SpectatorListener;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public final class GameManager {

    public final MiniGameExample plugin;
    public final Team redTeam, blueTeam;

    private int time = 0;

    public GameManager(MiniGameExample plugin) {
        this.plugin = plugin;
        
        final Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        
        this.redTeam = (sb.getTeam("red") == null) ? (sb.registerNewTeam("red")) : (sb.getTeam("red"));
        this.blueTeam = (sb.getTeam("blue") == null) ? (sb.registerNewTeam("blue")) : (sb.getTeam("blue"));

        // リスナー登録
        Util.registerListeners(
                plugin,
                new SpectatorListener(this),
                new ShowLeftTeamPlayersListener(this),
                new ExplosionStickListener()
        );
    }

    public void start(int time) {
        // 時間リセット
        this.time = time;

        // タイマースタート
        runTimer();
    }

    public void stop() {
        Bukkit.broadcast(Component.text("ゲームしゅーりょー！ 勝敗: " + getResult().getStatus()));
    }

    private void runTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (time < 0 || !getResult().equals(Result.NOT_WON)) {
                    stop();
                    cancel();
                }
                time--;
            }
        }.runTaskTimerAsynchronously(plugin, 20, 20);
    }

    private Result getResult() {
        final Set<String> redEntries = redTeam.getEntries(), blueEntries = blueTeam.getEntries();

        if (redEntries.isEmpty() && blueEntries.isEmpty()) {
            return Result.FAIR;
        } else if (redEntries.isEmpty()) {
            return Result.BLUE;
        } else if (blueEntries.isEmpty()) {
            return Result.RED;
        }
        return Result.NOT_WON;
    }

    private enum Result {
        RED("赤チーム"),
        BLUE("青チーム"),
        FAIR("引き分け"),
        NOT_WON("未確定");

        private final String status;

        Result(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
