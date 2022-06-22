package toyko.ramune.takenokoessential.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import toyko.ramune.takenokoessential.vote.VoteHandler;

import java.util.ArrayList;

public class Item {

    public static ItemStack getCompass() {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        CompassMeta compassMeta = (CompassMeta) itemStack.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE + "サーバー移動や投票報酬の取得ができます。");
        lore.add(ChatColor.YELLOW.toString());
        lore.add(ChatColor.LIGHT_PURPLE + "メインハンドでの右クリックでメニューを開く");
        compassMeta.setDisplayName(ChatColor.GOLD + "サーバーメニュー ");
        compassMeta.setLodestone(new Location(Bukkit.getWorld("world"), 0, 0, 0));
        compassMeta.setLore(lore);
        itemStack.setItemMeta(compassMeta);
        return itemStack;
    }

    public static ItemStack getBackGround() {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.RED.toString());
        lore.add(ChatColor.LIGHT_PURPLE.toString());
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getProfile(Player player) {
        if (player == null) {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(ChatColor.AQUA + player.getName() + "のプロフィール");
            item.setItemMeta(itemMeta);
            return item;
        } else {
            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            itemMeta.setDisplayName(ChatColor.AQUA + player.getName() + "のプロフィール");
            lore.add(ChatColor.LIGHT_PURPLE + "投票した数: " + VoteHandler.getVoteCount(player));
            lore.add(ChatColor.LIGHT_PURPLE + "未受け取りの報酬の数: " + (VoteHandler.getVoteCount(player) - VoteHandler.getClaimedCount(player)));
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            return item;
        }
    }

    public static ItemStack getVote() {
        ItemStack item = new ItemStack(Material.JUKEBOX);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "投票");
        lore.add(ChatColor.LIGHT_PURPLE + "このサーバーを投票して報酬をもらおう!");
        lore.add(ChatColor.LIGHT_PURPLE + "報酬: 鉄のピッケル×1");
        lore.add(ChatColor.GOLD + " 投票先");
        lore.add(ChatColor.GOLD + " ・MonoCraft");
        lore.add(ChatColor.GOLD + " ・Minecraft.jp");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで投票先のURLを開く");
        lore.add(ChatColor.YELLOW + "右クリックで未受け取りの報酬を受け取る");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getLobbyServer() {
        ItemStack item = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "ロビーサーバーに移動");
        lore.add(ChatColor.LIGHT_PURPLE + "ロビーサーバーに移動します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで移動");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getSurvivalServer() {
        ItemStack item = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "サバイバルサーバーに移動");
        lore.add(ChatColor.LIGHT_PURPLE + "サバイバルサーバーに移動します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで移動");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getCreativeServer() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "クリエイティブサーバーに移動");
        lore.add(ChatColor.LIGHT_PURPLE + "クリエイティブサーバーに移動します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで移動");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getHomes() {
        ItemStack item = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "家メニュー");
        lore.add(ChatColor.LIGHT_PURPLE + "設定した家のメニューを開きます");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで開く");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    // creative

    public static ItemStack getCreateClaims() {
        ItemStack item = new ItemStack(Material.GOLDEN_SHOVEL);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "保護を作成する");
        lore.add(ChatColor.LIGHT_PURPLE + "自分の保護を作成します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで作成");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getDeleteClaims() {
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "保護を削除する");
        lore.add(ChatColor.LIGHT_PURPLE + "現在立っている位置の保護を削除します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで削除");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getCheckClaim() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta itemMeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "保護を確認する");
        lore.add(ChatColor.LIGHT_PURPLE + "現在立っている位置の保護を確認します");
        lore.add(ChatColor.YELLOW + "");
        lore.add(ChatColor.YELLOW + "右クリックで確認");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}
