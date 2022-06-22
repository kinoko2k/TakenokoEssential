package toyko.ramune.takenokoessential.prefix;

import org.checkerframework.checker.nullness.qual.Nullable;

public class Prefix {

    private final String prefixName;
    private final String votePrefix;

    public Prefix(String prefixName, String votePrefix) {
        this.prefixName = prefixName;
        this.votePrefix = votePrefix;
    }

    public String getPrefixName() {
        return prefixName;
    }

    @Nullable
    public String getVotePrefix() {
        return votePrefix;
    }
}
