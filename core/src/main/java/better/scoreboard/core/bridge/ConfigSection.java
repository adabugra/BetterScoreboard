package better.scoreboard.core.bridge;

import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface ConfigSection {

    Collection<String> getChildren();

    @Nullable ConfigSection getConfigSection(String node);

    <E> List<E> getList(Class<E> classType, String node);

    String getName();

    <E> E getObject(Class<E> classType, String node);

    ConfigSection getParent();
}
