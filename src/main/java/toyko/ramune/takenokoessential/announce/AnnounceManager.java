package toyko.ramune.takenokoessential.announce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import toyko.ramune.takenokoessential.TakenokoEssential;

import java.util.Random;

public class AnnounceManager {

    public AnnounceManager(JavaPlugin plugin) {
        if (TakenokoEssential.getConfigFile().ANNOUNCE_SERVER_RESTART) {
            runServerRestartAnnounce(plugin);
        }
        if (TakenokoEssential.getConfigFile().ANNOUNCE_SITTEMASITAKA) {
            runSittemasitaka(plugin);
        }
        if (TakenokoEssential.getConfigFile().ANNOUNCE_SERVER_RULE) {
            runServerRuleAnnounce(plugin);
        }
    }
    private void runSittemasitaka(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            if (new Random().nextInt(2) == 0) {
                Bukkit.getOnlinePlayers().forEach((player -> {
                    player.sendMessage(ChatColor.AQUA + "[サーバー] 知ってましたか? " + ChatColor.AQUA + ChatColor.BOLD.toString() + "/sethome" + ChatColor.AQUA + "で立っている位置を保存できて、" + ChatColor.AQUA + ChatColor.BOLD.toString() + "/home" + ChatColor.AQUA + "でテレポートできます!! 有効活用しよう!");
                }));
            } else {
                Bukkit.getOnlinePlayers().forEach((player -> {
                    player.sendMessage(ChatColor.AQUA + "[サーバー] 自分の建築が荒らされたときは、Discordサーバーでサポート用チケットを作成しよう! すぐに運営が対処してくれるよ!");
                }));
            }
        }, 6000, 12000);
    }

    private void runServerRestartAnnounce(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                player.sendMessage(ChatColor.GREEN + "[サーバー] 毎日午前3時に自動バックアップが入ります。バックアップ中はサーバー停止するので注意してください");
            }));
        }, 12000, 12000);
    }
    
    private void runServerRuleAnnounce(JavaPlugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            Bukkit.getOnlinePlayers().forEach((player -> {
                player.sendMessage(ChatColor.AQUA + "[サーバー] このサーバーでのルールは確認しましたか? 今すぐ確認しよう!" + 
                                   "\n " + ChatColor.YELLOW + "https://takenoko.ramune.tokyo/rule/");
            }));
        }, 18000, 12000);
    }
}
