package toyko.ramune.takenokoessential.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreboardBuilder {

    private String title, displayName;
    private DisplaySlot displaySlot;
    private final ArrayList<String> lines = new ArrayList<>();

    public ScoreboardBuilder(String title, DisplaySlot displaySlot, String displayName) {
        this.title = title;
        this.displaySlot = displaySlot;
        this.displayName = displayName;
    }

    public ScoreboardBuilder(Scoreboard scoreboard) {

    }

    public void addLine(String line) {
        lines.add(line);
    }

    public void addBlankLine() {
        lines.add("{BLANK_LINE}");
    }

    public Scoreboard build() {
        Collections.reverse(lines);
        final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewObjective(title, "dummy");
        final Objective ob = sb.getObjective(title);
        ob.setDisplayName(displayName);
        ob.setDisplaySlot(displaySlot);
        int blankCount = 1;
        int i = 0;
        for (String line : lines) {
            if (line.equals("{BLANK_LINE}")) {
                String blank = "";
                for (int n=0;n<blankCount;n++) {
                    blank = blank + ChatColor.RESET.toString();
                }
                blankCount++;
                ob.getScore(blank).setScore(i);
            } else {
                ob.getScore(line).setScore(i);
            }
            i++;
        }
        return sb;
    }
}
