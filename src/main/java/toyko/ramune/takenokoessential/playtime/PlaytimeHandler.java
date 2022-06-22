package toyko.ramune.takenokoessential.playtime;

import org.bukkit.entity.Player;
import toyko.ramune.takenokoessential.database.SQL;

public class PlaytimeHandler {

    public static int getPlaytime(Player player) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "playtime")) {
            initializePlaytimeColumn(player);
        }
        return (int) SQL.get("playtime", "uuid", "=", player.getUniqueId().toString(), "playtime");
    }

    public static void setPlaytime(Player player, int playtime) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "playtime")) {
            initializePlaytimeColumn(player);
        }
        SQL.upsert("playtime", playtime, "uuid", player.getUniqueId().toString(), "playtime");
    }

    private static void initializePlaytimeColumn(Player player) {
        if (!SQL.exists("uuid", player.getUniqueId().toString(), "playtime")) {
            SQL.insertData("uuid, name, playtime", "'" + player.getUniqueId().toString() + "', '" + player.getName() + "', 0", "playtime");
        }
    }
}
