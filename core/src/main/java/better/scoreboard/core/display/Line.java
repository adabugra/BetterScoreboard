package better.scoreboard.core.display;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.placeholder.PlaceholderManager;
import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Line {

    private final static Pattern TRANSLATE = Pattern.compile("(?i)&[0-9A-FK-ORX]");

    private final BetterScoreboard plugin;
    private final String text;
    private final Set<String> placeholders;

    public Line(BetterScoreboard plugin, @Nullable String text) {
        this.plugin = plugin;

        if (text == null) {
            this.text = null;
            placeholders = Collections.emptySet();
            return;
        }

        this.text = text;
        placeholders = PlaceholderManager.separatePlaceholders(text);
    }

    public @Nullable String getText(User user) {
        String text = this.text;
        for (String placeholder : placeholders) text = text.replaceAll(placeholder, PlaceholderManager.setPlaceholder(plugin, user, placeholder));
        return translateColors(text);
    }

    private static String translateColors(String text) {
        if (text == null) return null;

        Matcher matcher = TRANSLATE.matcher(text);
        StringBuffer output = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            String replacement = "§" + match.substring(1);
            matcher.appendReplacement(output, replacement);
        }

        matcher.appendTail(output);
        return output.toString();
    }
}
