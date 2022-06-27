package toyko.ramune.takenokoessential.listener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import toyko.ramune.takenokoessential.menu.MenuHandler;

public class ListenerHandler {

    public ListenerHandler(JavaPlugin plugin) {
        addListeners(plugin);
    }

    private void addListeners(JavaPlugin plugin) {
        PluginManager plm = plugin.getServer().getPluginManager();
        plm.registerEvents(new PlayerJoinListener(), plugin);
        plm.registerEvents(new MenuHandler(), plugin);
        plm.registerEvents(new VoteListener(), plugin);
        plm.registerEvents(new PlayerInteractListener(), plugin);
        plm.registerEvents(new PlayerRespawnListener(), plugin);
        plm.registerEvents(new PlayerDropItemListener(), plugin);
        plm.registerEvents(new PlayerQuitListener(), plugin);
        plm.registerEvents(new PlayerLoginListener(), plugin);
    }
}
