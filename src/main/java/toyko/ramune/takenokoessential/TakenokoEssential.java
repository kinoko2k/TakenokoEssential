package toyko.ramune.takenokoessential;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import toyko.ramune.takenokoessential.announce.AnnounceManager;
import toyko.ramune.takenokoessential.command.CommandHandler;
import toyko.ramune.takenokoessential.config.Config;
import toyko.ramune.takenokoessential.database.DatabaseManager;
import toyko.ramune.takenokoessential.database.MySQL;
import toyko.ramune.takenokoessential.listener.ListenerHandler;
import toyko.ramune.takenokoessential.playtime.PlaytimeRunner;
import toyko.ramune.takenokoessential.scoreboard.PlayerTabManager;
import toyko.ramune.takenokoessential.scoreboard.SideBarManager;

public final class TakenokoEssential extends JavaPlugin {

    private static JavaPlugin plugin;
    private static Config config;
    private static LuckPerms luckperms;
    private static JDA jda;

    @Override
    public void onEnable() {
        plugin = this;
        initializeConfig();
        setupListener();

        setupJDA();
        setupLuckPerms();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        initializeDatabase();

        new AnnounceManager(this);
        new PlaytimeRunner().run();
        SideBarManager.startShowTask();
        PlayerTabManager.startShowTask();


        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        try {
            jda.shutdownNow();
            jda.awaitStatus(JDA.Status.SHUTDOWN);
        } catch (Exception ignored) {
        }
        getLogger().info("The plugin has been disabled.");
    }

    private void setupListener() {
        new ListenerHandler(this);
        new CommandHandler(this);
    }

    private void initializeConfig() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        config = new Config(getConfig());
    }

    private void setupJDA() {
        try {
            JDABuilder jdaBuilder = JDABuilder.createDefault(config.TOKEN);
            jda = jdaBuilder.build();
            jda.awaitReady();
            getLogger().info("Successfully login discord bot");
            getLogger().info("Login as " + jda.getSelfUser().getName() + " / " + jda.getSelfUser().getId());
        } catch (Exception e) {
            getLogger().warning("Couldn't login discord bot");
            getLogger().warning("Bot Token was wrong?");
        }
    }

    private void setupLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckperms = provider.getProvider();
            getLogger().info("Luckperms Connected!!");
        }
    }

    private void initializeDatabase() {
        MySQL.connect();
        DatabaseManager.createTables();
    }

    public static JavaPlugin getInstance() {
        return plugin;
    }

    public static Config getConfigFile() {
        return config;
    }

    public static LuckPerms getLuckperms() {
        return luckperms;
    }

    public static JDA getJDA() {
        return jda;
    }
}
