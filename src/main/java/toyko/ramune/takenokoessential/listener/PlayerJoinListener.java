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
        if (TakenokoEssential.getConfigFile().CREATIVE_SERVER_MODE_ENABLE) {
            player.sendMessage(ChatColor.AQUA + "[クリエイティブサーバーでの遊び方]");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "1.自分の保護を作成してから建築をしよう! /menuから保護の設定ができるよ");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "2.自分の建築の場所を/sethomeを使って移動を楽にしよう! /sethomeで登録すると、遠くからでも移動ができるよ!");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "3.他の人とも建築が一緒にできるよ! 他のプレイヤーを自分の保護に追加しよう!");
            player.sendMessage(ChatColor.LIGHT_PURPLE + "4.プレイ時間によって、保護できる範囲のブロック数が増えるよ!");
        }
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
