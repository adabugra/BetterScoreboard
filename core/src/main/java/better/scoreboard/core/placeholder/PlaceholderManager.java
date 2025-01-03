package better.scoreboard.core.placeholder;

import better.scoreboard.core.BetterScoreboard;
import better.scoreboard.core.condition.ConditionManager;
import com.github.retrooper.packetevents.protocol.player.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderManager {

    private final static Map<String, Placeholder> PLACEHOLDER_MAP = new HashMap<>();
    private final static Pattern PLACEHOLDER_PATTERN = Pattern.compile("%([^%]*)%");

    private static DateTimeFormatter dateFormatter;

    static {
        registerPlaceholder("date", (user -> LocalDateTime.now().format(dateFormatter)));
        registerPlaceholder("username", User::getName);
    }

    /*
     * Setters.
     */

    public static void setDateFormatter(String format) {
        dateFormatter = DateTimeFormatter.ofPattern(format);
    }

    /*
     * Methods.
     */

    public static void registerPlaceholder(String name, Placeholder placeholder) {
        PLACEHOLDER_MAP.put("%" + name + "%", placeholder);
    }

    /**
     * Find all placeholders in a given piece of text, separating them into a set.
     */
    public static Set<String> separatePlaceholders(String text) {
        Set<String> separated = new HashSet<>();
        if (text == null) return separated;
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(text);
        while (matcher.find()) separated.add(matcher.group());
        return separated;
    }

    public static String setPlaceholder(BetterScoreboard plugin, User user, String text) {
        Placeholder placeholder = PLACEHOLDER_MAP.get(text);
        String finalText;

        if (placeholder != null) {
            finalText = placeholder.text(user);
        } else if (text.startsWith("%condition:")) {
            finalText = ConditionManager.getCondition(text.substring(11, text.length() - 1)).getText(user);
        } else {
            finalText = plugin.getPlaceholders().setPlaceholders(user, text);
        }

        return finalText;
        //return finalText == null ? "" : finalText;
    }
}
