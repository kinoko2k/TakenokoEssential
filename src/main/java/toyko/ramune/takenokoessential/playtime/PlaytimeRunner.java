package toyko.ramune.takenokoessential.playtime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import toyko.ramune.takenokoessential.TakenokoEssential;

public class PlaytimeRunner implements Runnable {

    @Override
    public void run() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(TakenokoEssential.getInstance(), this::addPlaytime, 20, 60 * 20);
    }

    private void addPlaytime() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlaytimeHandler.setPlaytime(player, PlaytimeHandler.getPlaytime(player) + 1);
        }
    }
}
