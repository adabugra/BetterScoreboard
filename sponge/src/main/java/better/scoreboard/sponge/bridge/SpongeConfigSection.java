package better.scoreboard.sponge.bridge;

import better.scoreboard.core.bridge.ConfigSection;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpongeConfigSection implements ConfigSection {

    private final ConfigSection parent;
    private final CommentedConfigurationNode section;

    public SpongeConfigSection(CommentedConfigurationNode section) {
        this.parent = null;
        this.section = section;
    }

    public SpongeConfigSection(ConfigSection parent, CommentedConfigurationNode section) {
        this.parent = parent;
        this.section = section;
    }

    @Override
    public Collection<String> getChildren() {
        List<String> nodes = new ArrayList<>();
        for (ConfigurationNode node : section.childrenList()) nodes.add((String) node.key());
        return nodes;
    }

    @Override
    public @Nullable ConfigSection getConfigSection(String node) {
        if (!hasNode(node)) return null;
        return new SpongeConfigSection(this, section.node(node));
    }

    @Override
    public <E> List<E> getList(Class<E> classType, String node) {
        try {
            return section.node(node).getList(classType);
        } catch (SerializationException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String getName() {
        return (String) section.key();
    }

    @Override
    public <E> E getObject(Class<E> classType, String node, E defaultValue) {
        try {
            E obj = section.node(node).get(classType);
            return obj == null ? defaultValue : obj;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    public ConfigSection getParent() {
        return parent;
    }

    @Override
    public boolean hasNode(String node) {
        return section.node(node).raw() != null;
    }
}
