package toyko.ramune.takenokoessential.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.menu.MenuHandler;
import toyko.ramune.takenokoessential.prefix.PrefixHandler;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PrefixHandler.applyPrefix(player);
        MenuHandler.giveCompassIfNotExist(player);
        player.sendMessage(ChatColor.GREEN + "サーバーを移動したいときは");
        player.sendMessage(ChatColor.GREEN + "/menuでサーバーメニューを開けるよ!");
        if (!TakenokoEssential.getConfigFile().SPAWN_LOCATION_FORCE_TELEPORT) {
            return;
        }
        try {
            Location location = new Location(Bukkit.getWorld(TakenokoEssential.getConfigFile().SPAWN_LOCATION_WORLD)
            , TakenokoEssential.getConfigFile().SPAWN_LOCATION_X
            , TakenokoEssential.getConfigFile().SPAWN_LOCATION_Y
            ,TakenokoEssential.getConfigFile().SPAWN_LOCATION_Z);
            player.teleport(location);
        } catch (Exception ignored) {
        }
        if (player.hasPlayedBefore()) {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + player.getName() + "がログインしました");
        } else {
            event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + player.getName() + "が初ログインしました");
        }
    }
}
