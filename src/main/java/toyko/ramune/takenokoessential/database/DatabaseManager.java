package toyko.ramune.takenokoessential.database;

import org.bukkit.entity.Player;

public class DatabaseManager {

    public static void createTables() {
        if (!SQL.tableExists("vote")) {
            SQL.createTable("vote", "uuid TEXT NOT NULL, name TEXT NOT NULL, count INT DEFAULT 0, claimed INT DEFAULT 0");
        }
        if (!SQL.tableExists("playtime")) {
            SQL.createTable("playtime", "uuid TEXT NOT NULL, name TEXT NOT NULL, playtime INT DEFAULT 0");
        }
    }
}
