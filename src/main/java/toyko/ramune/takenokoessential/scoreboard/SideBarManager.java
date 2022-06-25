package toyko.ramune.takenokoessential.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import toyko.ramune.takenokoessential.TakenokoEssential;

import java.util.ArrayList;
import java.util.Arrays;

public class SideBarManager {

    private static BukkitTask sideBarTask;
    private static final ArrayList<String> titles = new ArrayList<>(Arrays.asList(
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "こサ" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "のこサー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "けのこサーバ" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.DARK_GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.DARK_GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.DARK_GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "たけのこサーバー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "けのこサーバ" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "のこサー" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "こサ" + ChatColor.DARK_GREEN + " <",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "> " + ChatColor.GREEN + "" + ChatColor.DARK_GREEN + " <"
    ));
    private static ArrayList<String> lines = new ArrayList<>(Arrays.asList(
            ChatColor.GREEN + ChatColor.BOLD.toString() + "--------------------",
            ChatColor.GREEN + ChatColor.BOLD.toString() + "--------------------",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "--------------------",
            ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "--------------------"
    ));
    private static int titlePageCount = 0, linePageCount = 0;


    public static void startShowTask() {
        if (sideBarTask != null) {
            try {
                sideBarTask.cancel();
            } catch (Exception ignored) {
            }
        }
        sideBarTask = Bukkit.getScheduler().runTaskTimerAsynchronously(TakenokoEssential.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(SideBarManager::showSideBar);
            if (titlePageCount + 1 >= titles.size()) {
                titlePageCount = 0;
            } else {
                titlePageCount++;
            }
            if (linePageCount + 1 >= lines.size()) {
                linePageCount = 0;
            } else {
                linePageCount++;
            }
        }, 5, 5);
    }

    private static void showSideBar(Player player) {
        if (player.getUniqueId().toString().startsWith("00000000")) {
            return;
        }
        String title = titles.get(titlePageCount);
        String line = lines.get(linePageCount);
        ScoreboardBuilder sb = new ScoreboardBuilder("SideBar", DisplaySlot.SIDEBAR, title);

        sb.addLine(line);
        sb.addBlankLine();
        sb.addLine(ChatColor.GREEN + ChatColor.BOLD.toString() + "プレイヤー " + ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + ">");
        sb.addLine(ChatColor.AQUA + player.getName());
        sb.addLine(ChatColor.RED + "♡ " + ChatColor.GRAY + Math.floor(player.getHealth()) + ChatColor.DARK_GRAY + "/" + ChatColor.GRAY + Math.floor(player.getMaxHealth()));
        sb.addLine(player.getPing() + "ms " + ChatColor.GRAY + "レイテンシ");
        sb.addBlankLine();
        sb.addLine(ChatColor.GREEN + ChatColor.BOLD.toString() + "サーバー " + ChatColor.DARK_GREEN + ChatColor.BOLD + "> " + ChatColor.GREEN + ChatColor.BOLD.toString() + Bukkit.getServer().getMotd() + "サーバー");
        sb.addLine(String.valueOf(Math.round(Bukkit.getServer().getTPS()[0])) + ChatColor.GRAY + " ティック毎秒");
        sb.addLine(player.getLocation().getBlock().getBiome().toString().toLowerCase().replace("_", " ") + ChatColor.GRAY + "バイオーム");
        sb.addBlankLine();
        sb.addLine(ChatColor.GREEN + ChatColor.BOLD.toString() + "ヒント " + ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + ">");
        sb.addLine(ChatColor.BOLD + "/menu" + ChatColor.GRAY + "でメニューを表示");
        sb.addBlankLine();
        Bukkit.getScheduler().runTask(TakenokoEssential.getInstance(), () -> player.setScoreboard(sb.build()));
    }
}
