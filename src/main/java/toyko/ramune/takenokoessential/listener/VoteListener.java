package toyko.ramune.takenokoessential.listener;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.vote.VoteHandler;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class VoteListener implements Listener {

    @EventHandler
    public void onVoteEvent(VotifierEvent event) {
        Vote vote = event.getVote();
        String uuid = getUUID(vote.getUsername());
        if (uuid == null) {
            return;
        }
        try {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.magenta)
                    .setTitle(vote.getUsername() + "さんが" + vote.getServiceName() + "で「たけのこサーバー」に投票しました！")
                    .setDescription("ありがとうございます！！\n**投票ページリスト**\nMinecraft.jp : https://minecraft.jp/servers/takenoko.ramune.tokyo:45357\nMonocraft.net : https://monocraft.net/servers/AVPgLD1HMJ2mCjD5rFp4");
            TakenokoEssential.getJDA().getNewsChannelById(TakenokoEssential.getConfigFile().VOTE_ANNOUNCE).sendMessageEmbeds(builder.build()).queue();
        } catch (Exception ignored) {
        }
        try {
            Player player = Bukkit.getPlayer(UUID.fromString(uuid));
            player.sendMessage(ChatColor.GREEN + "[投票通知] このサーバーを投票してくれて、ありがとう!! 投票してくれたお礼に報酬をあげるよ。\nメニューから報酬を受け取ろう!");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            player.closeInventory();
        } catch (Exception ignored) {
        }
        VoteHandler.setVoteCount(uuid, VoteHandler.getVoteCount(UUID.fromString(uuid)) + 1);
    }

    private static String getUUID(String name) {
        String uuid = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
            uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
            uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
            in.close();
        } catch (Exception e) {
            System.out.println("Unable to get UUID of: " + name + "!");
            uuid = null;
        }
        return uuid;
    }
}
