package toyko.ramune.takenokoessential.prefix;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import toyko.ramune.takenokoessential.TakenokoEssential;
import toyko.ramune.takenokoessential.vote.VoteHandler;

public class PrefixHandler {

    public static void applyPrefix(Player player) {
        Prefix prefix = getPrefix(player);
        if (prefix.getPrefixName() == null) {
            return;
        }
        if (prefix.getVotePrefix() == null) {
            player.setDisplayName(ChatColor.GRAY + "[" + prefix.getPrefixName() +  ChatColor.GRAY + "] " + ChatColor.GRAY + "[なし" + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.GRAY + "[" + prefix.getPrefixName() +  ChatColor.GRAY + "] " + ChatColor.RESET + player.getName());
        } else {
            player.setDisplayName(ChatColor.GRAY + "[" + prefix.getPrefixName() + ChatColor.GRAY + "] " + ChatColor.GRAY + "[" + prefix.getVotePrefix()+ ChatColor.GRAY + "] " + ChatColor.RESET + player.getName());
            player.setPlayerListName(ChatColor.GRAY + "[" + prefix.getVotePrefix() + ChatColor.GRAY + "] " + ChatColor.RESET + player.getName());
        }
    }

    public static Prefix getPrefix(Player player) {
        String votePrefix = null;
        final int voteCount = VoteHandler.getVoteCount(player);
        if (voteCount >= 5) {
            votePrefix = ChatColor.GOLD + "期待の新人の";
        }
        if (voteCount >= 10) {
            votePrefix = ChatColor.GOLD + "新世代の新人の";
        }
        if (voteCount >= 20) {
            votePrefix = ChatColor.BLUE + "次世代の新人の";
        }
        if (voteCount >= 25) {
            votePrefix = ChatColor.YELLOW + "ついにベテランの";
        }
        if (voteCount >= 30) {
            votePrefix = ChatColor.AQUA + "スーパーベテランの";
        }
        if (voteCount >= 40) {
            votePrefix = ChatColor.GOLD + "ハイパーベテランの";
        }
        if (voteCount >= 45) {
            votePrefix = ChatColor.GREEN + "広大な心の";
        }
        if (voteCount >= 50) {
            votePrefix = ChatColor.YELLOW + "暇人の";
        }
        if (voteCount >= 60) {
            votePrefix = ChatColor.AQUA + "すごい暇人の";
        }
        if (voteCount >= 70) {
            votePrefix = ChatColor.GOLD + "もしかして人気者の";
        }
        if (voteCount >= 80) {
            votePrefix = ChatColor.GREEN + "サーバー内の有名人の";
        }
        if (voteCount >= 90) {
            votePrefix = ChatColor.WHITE + "紙の";
        }
        if (voteCount >= 100) {
            votePrefix = ChatColor.YELLOW + "✧ ＼\\ ٩( '神' )و /／  ✧の";
        }

        return new Prefix(
                TakenokoEssential.getLuckperms().getGroupManager().getGroup(TakenokoEssential.getLuckperms().getUserManager().getUser(player.getUniqueId()).getPrimaryGroup()).getDisplayName()
                , votePrefix);
    }
}
