package better.scoreboard.spigot.bridge;

import better.scoreboard.core.bridge.ConfigSection;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpigotConfigSection implements ConfigSection {

    private final ConfigSection parent;
    private final ConfigurationSection section;

    public SpigotConfigSection(ConfigurationSection section) {
        this.parent = null;
        this.section = section;
    }

    public SpigotConfigSection(ConfigSection parent, ConfigurationSection section) {
        this.parent = parent;
        this.section = section;
    }

    @Override
    public Collection<String> getChildren() {
        return section.getKeys(false);
    }

    @Override
    public @Nullable ConfigSection getConfigSection(String node) {
        ConfigurationSection child = section.getConfigurationSection(node);
        if (child == null) return null;
        return new SpigotConfigSection(this, child);
    }

    @Override
    public <E> List<E> getList(Class<E> classType, String node) {
        List<E> list = new ArrayList<>();
        for (Object e : section.getList(node, list)) {
            list.add((E) e);
        }
        return list;
    }

    @Override
    public String getName() {
        return section.getName();
    }

    @Override
    public <E> E getObject(Class<E> classType, String node) {
        return section.getObject(node, classType);
    }

    @Override
    public ConfigSection getParent() {
        return parent;
    }
}
