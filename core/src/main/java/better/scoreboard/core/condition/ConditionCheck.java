package better.scoreboard.core.condition;

public interface ConditionCheck {

    /**
     * Can be implemented as a lambda.
     * Example: ((leftText, rightText) -> leftText.equals(rightText))
     */
    boolean compareText(String leftText, String rightText);
}
