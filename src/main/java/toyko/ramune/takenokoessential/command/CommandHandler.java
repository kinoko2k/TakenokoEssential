package toyko.ramune.takenokoessential.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import toyko.ramune.takenokoessential.menu.MenuHandler;

public class CommandHandler implements CommandExecutor {

    public CommandHandler(JavaPlugin plugin) {
        plugin.getCommand("menu").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(MenuHandler.getMenu(player));
        return true;
    }
}
