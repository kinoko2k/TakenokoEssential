package toyko.ramune.takenokoessential.menu;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import toyko.ramune.takenokoessential.database.SQL;
import toyko.ramune.takenokoessential.database.SQLDate;

import java.util.HashMap;
import java.util.UUID;

public class LoginBonusMenu {
    private static final HashMap<Integer, ItemStack> continuousLoginReward = new HashMap<Integer, ItemStack>() {{
        put(2, new ItemStack(Material.BREAD, 5));
        put(3, new ItemStack(Material.IRON_HOE));
        put(4, new ItemStack(Material.IRON_SHOVEL));
        put(5, new ItemStack(Material.IRON_PICKAXE));
        put(10, new ItemStack(Material.JUKEBOX));
        put(20, new ItemStack(Material.DIAMOND_HELMET));
    }};

    private static void initializeData(Player player) {
        UUID uuid = player.getUniqueId();

        if (!SQL.exists("uuid", uuid.toString(), "login")) {
            SQL.insertData("uuid, name, last_login, continuous_login, total_login, earn_total_login_reward"
                    , "'" + uuid.toString() + "', '" + player.getName() + "', " + new SQLDate().getDateNow() + ", 0, 0, 0"
                    , "login");
        }
    }

    public static SQLDate getLastLogin(Player player) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        return new SQLDate((String) SQL.get("last_login", "uuid", "=", uuid.toString(), "login"));
    }

    public static void setLastLogin(Player player, SQLDate sqlDate) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        SQL.upsert("last_login", sqlDate.getDate(), "uuid", uuid.toString(), "login");
    }

    public static int getTotalLoginCount(Player player) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        return (int) SQL.get("total_login", "uuid", "=", uuid.toString(), "login");
    }

    public static void setTotalLoginCount(Player player, int totalLoginCount) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        SQL.upsert("total_login", totalLoginCount, "uuid",  uuid.toString(), "login");
    }

    public static int getContinuousLoginCount(Player player) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        return (int) SQL.get("continuous_login", "uuid", "=", uuid.toString(), "login");
    }

    public static void setContinuousLoginCount(Player player, int continuousLoginCount) {
        UUID uuid = player.getUniqueId();

        initializeData(player);
        SQL.upsert("continuous_login", continuousLoginCount, "uuid", uuid.toString(), "login");
    }
    public static void updateLogin(Player player) {
        // TODO: 25/06/2022  
        UUID uuid = player.getUniqueId();

        initializeData(player);
        // totalLogin

        // continuousLogin
    }

    private static void giveTotalReward() {

    }

    private static void spawnFireworks(Location loc, int amount){
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }
}
