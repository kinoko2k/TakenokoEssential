package toyko.ramune.takenokoessential.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private FileConfiguration config;

    public String  MYSQL_HOST, MYSQL_DATABASE, MYSQL_USERNAME, MYSQL_PASSWORD, TOKEN, VOTE_ANNOUNCE, SPAWN_LOCATION_WORLD;

    public int MYSQL_PORT, SPAWN_LOCATION_YAW, SPAWN_LOCATION_PITCH, PLAYTIME_LIMITER_LIMITED_PLAYTIME_MINUTES;

    public double SPAWN_LOCATION_X, SPAWN_LOCATION_Y, SPAWN_LOCATION_Z;

    public boolean ANNOUNCE_SERVER_RESTART, ANNOUNCE_SITTEMASITAKA ,ANNOUNCE_SERVER_RULE ,VOTE_REWARD_ENABLE, SPAWN_LOCATION_FORCE_TELEPORT, PLAYTIME_LIMITER_ENABLE;

    public Config(FileConfiguration config) {
        this.config = config;
        load();
    }

    public void load() {
        MYSQL_HOST                                  = config.getString("config.database.host");
        MYSQL_PORT                                  = config.getInt("config.database.port");
        MYSQL_DATABASE                              = config.getString("config.database.database");
        MYSQL_USERNAME                              = config.getString("config.database.username");
        MYSQL_PASSWORD                              = config.getString("config.database.password");

        TOKEN                                       = config.getString("config.discord.token");

        VOTE_REWARD_ENABLE                          = config.getBoolean("config.vote-reward.enable");
        VOTE_ANNOUNCE                               = config.getString("config.discord.vote-announce");

        ANNOUNCE_SERVER_RESTART                     = config.getBoolean("config.announce.server-restart");
        ANNOUNCE_SITTEMASITAKA                      = config.getBoolean("config.announce.sittemasitaka");
        ANNOUNCE_SERVER_RULE                        = config.getBoolean("config.announce.server-rule");

        PLAYTIME_LIMITER_ENABLE                     = config.getBoolean("config.playtime-limiter.enable");
        PLAYTIME_LIMITER_LIMITED_PLAYTIME_MINUTES   = config.getInt("config.playtime-limiter.limited-playtime-minutes");

        SPAWN_LOCATION_FORCE_TELEPORT               = config.getBoolean("config.spawn-location.force-teleport");
        SPAWN_LOCATION_WORLD                        = config.getString("config.spawn-location.world");
        SPAWN_LOCATION_X                            = config.getDouble("config.spawn-location.x");
        SPAWN_LOCATION_Y                            = config.getDouble("config.spawn-location.y");
        SPAWN_LOCATION_Z                            = config.getDouble("config.spawn-location.z");
        SPAWN_LOCATION_YAW                          = config.getInt("config.spawn-location.yaw");
        SPAWN_LOCATION_PITCH                        = config.getInt("config.spawn-location.pitch");
    }
}
