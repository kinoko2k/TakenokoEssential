package toyko.ramune.takenokoessential.announce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import toyko.ramune.takenokoessential.TakenokoEssential;

public class AnnounceManager {

    public AnnounceManager(JavaPlugin plugin) {
        if (TakenokoEssential.getConfigFile().ANNOUNCE_SERVER_RESTART) {
            runServerRestartAnnounce(plugin);
        }
        if (TakenokoEssential.getConfigFile().ANNOUNCE_SITTEMASITAKA) {
            runSittemasitaka(plugin);
        }
    }
    private void runSittemasitaka(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                player.sendMessage(ChatColor.AQUA + "[サーバー] 知ってましたか? /sethomeで立っている位置を保存できて、/homeでテレポートできます!! 有効活用しよう!");
            }));
        }, 6000, 12000);
    }

    private void runServerRestartAnnounce(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                player.sendMessage(ChatColor.GREEN + "[サーバー] 毎日午前3時に自動バックアップが入ります。バックアップ中はサーバー停止するので注意してください");
            }));
        }, 12000, 12000);
    }
}
