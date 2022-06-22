package toyko.ramune.takenokoessential.listener;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent e) {
        Item droppedItem = e.getItemDrop();
        try {
            if (!droppedItem.getItemStack().getItemMeta().getDisplayName().equals(toyko.ramune.takenokoessential.menu.Item.getCompass().getItemMeta().getDisplayName())) {
                return;
            }
            e.getItemDrop().remove();
        } catch (Exception ignored) {
        }
    }
}
