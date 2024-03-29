package toyko.ramune.takenokoessential.database;

public class DatabaseManager {

    public static void createTables() {
        if (!SQL.tableExists("vote")) {
            SQL.createTable("vote", "uuid TEXT NOT NULL, name TEXT NOT NULL, count INT DEFAULT 0, claimed INT DEFAULT 0");
        }
        if (!SQL.tableExists("login")) {
            SQL.createTable("login", "uuid TEXT NOT NULL, name TEXT NOT NULL, last_login DATE NOT NULL, continuous_login INT DEFAULT 0, total_login INT DEFAULT 0, earn_total_login_reward INT DEFAULT 0");
        }
        if (!SQL.tableExists("playtime")) {
            SQL.createTable("playtime", "uuid TEXT NOT NULL, name TEXT NOT NULL, playtime INT DEFAULT 0");
        }
    }
}
