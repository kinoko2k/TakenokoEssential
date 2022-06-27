package toyko.ramune.takenokoessential.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.playtime.PlaytimeHandler;

public class PlayerLoginListener implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        int playTime = PlaytimeHandler.getPlaytime(player);
        if (!TakenokoEssential.getConfigFile().PLAYTIME_LIMITER_ENABLE) {
            return;
        }
        if (TakenokoEssential.getConfigFile().PLAYTIME_LIMITER_LIMITED_PLAYTIME_MINUTES >= playTime) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(ChatColor.RED + "このサーバーに参加するには" + TakenokoEssential.getConfigFile().PLAYTIME_LIMITER_LIMITED_PLAYTIME_MINUTES + "分以上のたけのこサーバー全体のプレイタイムが必要です。"
                    + "\n必要なプレイタイム  " + playTime + ChatColor.GRAY + "/" + ChatColor.RED + TakenokoEssential.getConfigFile().PLAYTIME_LIMITER_LIMITED_PLAYTIME_MINUTES);
        }
    }

}
