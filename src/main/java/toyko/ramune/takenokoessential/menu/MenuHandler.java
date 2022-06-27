package toyko.ramune.takenokoessential.menu;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.vote.VoteHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class MenuHandler implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTitle().equals("§b§lメニュー")) {
            return;
        }
        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) {
            return;
        }
        String displayName = event.getCurrentItem().getItemMeta().getDisplayName();
        if (displayName.equals(Item.getVote().getItemMeta().getDisplayName())) {
            TextComponent tcSurvival = new TextComponent("");
            tcSurvival.setText(ChatColor.BOLD.toString() + ChatColor.UNDERLINE + "ここをクリックでMonoCraftに移動する");
            tcSurvival.setColor(ChatColor.YELLOW);
            tcSurvival.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://monocraft.net/servers/AVPgLD1HMJ2mCjD5rFp4"));
            player.spigot().sendMessage(tcSurvival);
            TextComponent tcAmplified = new TextComponent("");
            tcAmplified.setText(ChatColor.BOLD.toString() + ChatColor.UNDERLINE + "ここをクリックでMinecraft.jpに移動する");
            tcAmplified.setColor(ChatColor.YELLOW);
            tcAmplified.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.jp/servers/takenoko.ramune.tokyo:45357"));
            player.spigot().sendMessage(tcAmplified);
            VoteHandler.getReward((Player) event.getWhoClicked());
        }else if (displayName.equals(Item.getLobbyServer().getItemMeta().getDisplayName())) {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                out.writeUTF("Connect");
                out.writeUTF("lobby");
                player.sendPluginMessage(TakenokoEssential.getInstance(), "BungeeCord", b.toByteArray());
                b.close();
                out.close();
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "サーバー接続にエラーが発生しました");
            }
            player.closeInventory();
        } else if (displayName.equals(Item.getSurvivalServer().getItemMeta().getDisplayName())) {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                out.writeUTF("Connect");
                out.writeUTF("survival");
                player.sendPluginMessage(TakenokoEssential.getInstance(), "BungeeCord", b.toByteArray());
                b.close();
                out.close();
            }
            catch (Exception e) {
                player.sendMessage(ChatColor.RED+"サーバー接続にエラーが発生しました");
            }
            player.closeInventory();
        } else if (displayName.equals(Item.getOneBlockServer().getItemMeta().getDisplayName())) {
            try {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                out.writeUTF("Connect");
                out.writeUTF("oneblock");
                player.sendPluginMessage(TakenokoEssential.getInstance(), "BungeeCord", b.toByteArray());
                b.close();
                out.close();
            }
            catch (Exception e) {
                player.sendMessage(ChatColor.RED+"サーバー接続にエラーが発生しました");
            }
            player.closeInventory();
        }
    }

    public static void giveCompassIfNotExist(@NonNull Player player)  {
        Inventory inventory = player.getInventory();
        boolean isItemExist = false;
        for (ItemStack itemStack : inventory.getContents()) {
            try {
                if (itemStack.getItemMeta().getDisplayName().equals(Item.getCompass().getItemMeta().getDisplayName())) {
                    isItemExist = true;
                }
            } catch (Exception ignored) {
            }
        }
        if (!isItemExist) {
            inventory.addItem(Item.getCompass());
        }
    }

    public static Inventory getMenu(@NonNull Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§b§lメニュー");
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Item.getBackGround());
        }
        inventory.setItem(10, Item.getProfile(player));
        inventory.setItem(12, Item.getVote());
        inventory.setItem(14, Item.getLobbyServer());
        inventory.setItem(15, Item.getSurvivalServer());
        inventory.setItem(16, Item.getOneBlockServer());
        return inventory;
    }
}
