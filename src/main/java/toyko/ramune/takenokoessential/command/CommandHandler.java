package toyko.ramune.takenokoessential.command;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import toyko.ramune.takenokoessential.menu.MenuHandler;

public class CommandHandler implements CommandExecutor {

    public CommandHandler(JavaPlugin plugin) {
        plugin.getCommand("menu").setExecutor(this);
        plugin.getCommand("hat").setExecutor(this);
    }

    // menu command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "menu": return onMenuCommand(sender, args);
            case "hat": return onHatCommand(sender, args);
        }
        return true;
    }

    private boolean onMenuCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(MenuHandler.getMenu(player));
        return true;
    }

    private boolean onHatCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (player.getInventory().getItemInMainHand().getAmount() != 1) {
            player.sendMessage(ChatColor.RED + "メインハンドのアイテムの個数は一個である必要があります");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1.2F);
            return true;
        }
        ItemStack helmetItem, mainHandItem;
        helmetItem = player.getInventory().getHelmet();
        mainHandItem = player.getInventory().getItemInMainHand();
        player.getInventory().setHelmet(mainHandItem);
        player.getInventory().setItemInMainHand(helmetItem);
        player.sendMessage(ChatColor.GREEN + "メインハンドのアイテムと頭のアイテムを入れ替えました");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 1.2F);
        return true;
    }
}
