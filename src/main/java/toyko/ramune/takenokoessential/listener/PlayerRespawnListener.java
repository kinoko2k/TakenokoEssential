package toyko.ramune.takenokoessential.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import toyko.ramune.takenokoessential.menu.Item;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().contains(Item.getCompass())) {
            return;
        }
        player.getInventory().addItem(Item.getCompass());
    }
}
