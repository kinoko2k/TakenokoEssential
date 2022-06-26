package toyko.ramune.takenokoessential.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import toyko.ramune.takenokoessential.TakenokoEssential;

public class PlayerTabManager {

    private static BukkitTask playerTabTask;

    public static void startShowTask() {
        if (playerTabTask != null) {
            try {
                playerTabTask.cancel();
            } catch (Exception ignored) {
            }
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(TakenokoEssential.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(PlayerTabManager::showPlayerList);
        }, 5, 5);
    }

    private static void showPlayerList(Player player) {
        player.setPlayerListHeader(
                ChatColor.GRAY + ChatColor.BOLD.toString() + "☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵" +
                        "\n" +
                        "\n" + ChatColor.GREEN + ChatColor.BOLD.toString() + "たけのこサーバー" +
                        "\n" + ChatColor.GRAY + Bukkit.getServer().getMotd() + "サーバー" +
                        "\n" + ChatColor.AQUA + String.valueOf(Math.round(Bukkit.getServer().getTPS()[0])) + ChatColor.GRAY + " ティック毎秒  " + player.getPing() + "ms " + ChatColor.GRAY + "レイテンシ" +
                        "\n" +
                        "\n" + ChatColor.AQUA + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + " オンラインプレイヤー" +
                        "\n");
        player.setPlayerListFooter(
                "\n" +
                        "\n" + ChatColor.AQUA + ChatColor.BOLD.toString() + "Webサイト" + ChatColor.GRAY + ": " + ChatColor.WHITE + "takenoko.ramune.tokyo" +
                        "\n" + ChatColor.AQUA + ChatColor.BOLD.toString() + "Twitter" + ChatColor.GRAY + ": " + ChatColor.WHITE + "@takenokoserver" +
                        "\n" +
                        "\n" + ChatColor.GRAY + ChatColor.BOLD.toString() + "☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵");
    }
}
