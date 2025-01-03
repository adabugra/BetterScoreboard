package better.scoreboard.core.condition;

import java.util.HashMap;
import java.util.Map;

public class ConditionManager {

    private final static ConditionCheck defaultCondition = (((leftText, rightText) -> true));

    private final static Map<String, Condition> CONDITIONS = new HashMap<>();
    private final static Map<String, ConditionCheck> CONDITION_CHECKS = new HashMap<>();

    public static void addCondition(String name, Condition condition) {
        CONDITIONS.put(name, condition);
    }

    public static void clear() {
        CONDITIONS.clear();
    }

    public static Condition getCondition(String name) {
        return CONDITIONS.get(name.toLowerCase());
    }

    public static void registerConditionCheck(String name, ConditionCheck conditionCheck) {
        CONDITION_CHECKS.put(name, conditionCheck);
    }

    public static ConditionCheck retrieveConditionCheck(String name) {
        if (name == null) return defaultCondition;
        ConditionCheck conditionCheck = CONDITION_CHECKS.get(name.toLowerCase());
        if (conditionCheck == null) return defaultCondition;
        return conditionCheck;
    }
}
