package toyko.ramune.takenokoessential.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import toyko.ramune.takenokoessential.menu.Item;
import toyko.ramune.takenokoessential.menu.MenuHandler;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        try {
            String itemName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            if (!itemName.equals(Item.getCompass().getItemMeta().getDisplayName())) {
                return;
            }
            player.openInventory(MenuHandler.getMenu(player));
        } catch (Exception ignored) {
        }
    }
}
