package toyko.ramune.takenokoessential.vote;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.database.SQL;
import toyko.ramune.takenokoessential.menu.MenuHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.UUID;

public class VoteHandler {

    public static int getVoteCount(@Nonnull Player player) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "vote")) {
            initializeVoteColumn(player);
        }
        return (int) SQL.get("count", "uuid", "=", player.getUniqueId().toString(), "vote");
    }

    public static int getVoteCount(@Nonnull UUID uuid) {
        if (!SQL.exists("uuid", uuid.toString(), "vote")) {
            initializeVoteColumn(Bukkit.getPlayer(uuid));
        }
        return (int) SQL.get("count", "uuid", "=", uuid.toString(), "vote");
    }

    public static void setVoteCount(@Nonnull Player player, int count) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "vote")) {
            initializeVoteColumn(player);
        }
        SQL.upsert("count", count, "uuid", player.getUniqueId().toString(), "vote");
    }

    public static void setVoteCount(@Nonnull String uuid, int count) {
        if (!SQL.exists("uuid", uuid, "vote")) {
            Player player = Bukkit.getPlayer(UUID.fromString(uuid));
            initializeVoteColumn(player);
        }
        SQL.upsert("count", count, "uuid", uuid, "vote");
    }

    public static int getClaimedCount(@Nonnull Player player) {
        if (!SQL.exists("uuid",player.getUniqueId().toString(), "vote")) {
            initializeVoteColumn(player);
        }
        return (int) SQL.get("claimed", "uuid", "=", player.getUniqueId().toString(), "vote");
    }

    public static void setClaimedCount(@Nonnull Player player, int claimedCount) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "vote")) {
            initializeVoteColumn(player);
        }
        SQL.upsert("claimed", claimedCount, "uuid", player.getUniqueId().toString(), "vote");
    }

    public static void getReward(@Nonnull Player player) {
        if (getVoteCount(player) > getClaimedCount(player)) {
            ItemStack item = new ItemStack(Material.IRON_PICKAXE);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            if (!TakenokoEssential.getConfigFile().VOTE_REWARD_ENABLE) {
                player.sendMessage(ChatColor.RED + "おおっと、ここでは投票報酬を受け取れないみたいだよ" + ChatColor.LIGHT_PURPLE + "\nサバイバルサーバーで受け取ろう!!");
                player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1, 0);
                player.closeInventory();
                return;
            }
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "鉄のピッケル 投票ありがとう!");
            lore.add(ChatColor.GOLD + "投票ありがとう!!");
            lore.add(ChatColor.GOLD + "明日も投票よろしくね!");
            meta.setLore(lore);
            item.setItemMeta(meta);
            if (player.getInventory().firstEmpty() == -1) {
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1, 0);
                player.sendMessage(ChatColor.RED + "おおっと、受け取るためにはインベントリに空きが無いと受け取れないよ\n必ずインベントリに空きを作ってから、受け取りに来てね");
                return;
            }
            player.getInventory().addItem(item);
            setClaimedCount(player, getClaimedCount(player) + 1);
            player.sendMessage(ChatColor.GREEN + "投票ありがとう!! 報酬の鉄のピッケルをどうぞ");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.closeInventory();
            player.openInventory(MenuHandler.getMenu(player));
        } else {
            player.sendMessage(ChatColor.RED + "獲得できる報酬はありません。 明日、また投票してみてね");
            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1, 0);
            player.closeInventory();
        }
    }

    private static void initializeVoteColumn(Player player) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "vote")) {
            SQL.insertData("uuid, name, count, claimed", "'" + player.getUniqueId().toString() + "', '" + player.getName() + "', 0, 0", "vote");
        }
    }
}
