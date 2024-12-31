package better.scoreboard.core.trigger;

import better.scoreboard.core.trigger.impl.DefaultTrigger;

import java.util.HashMap;
import java.util.Map;

public class TriggerManager {

    private final static Map<String, Trigger> TRIGGER_MAP = new HashMap<>();

    /**
     * Register a Trigger to be associated with the given name.
     */
    public static void registerTrigger(String name, Trigger trigger) {
        TRIGGER_MAP.put(name.toLowerCase(), trigger);
    }

    /**
     * Retrieve a copy of the Trigger associated with the given name.
     */
    public static Trigger retrieveTrigger(String name) {
        if (name == null) return new DefaultTrigger();
        Trigger trigger = TRIGGER_MAP.get(name.toLowerCase());
        if (trigger == null) return new DefaultTrigger();
        return trigger.clone();
    }
}
