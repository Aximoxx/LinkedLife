package fr.Aximoxx.linkedLife;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static Main instance;
    private Manager manager;
    private Listener listener;

    @Override
    public void onEnable() {
        instance = this;

        manager = new Manager();
        listener = new Listener();
        Objects.requireNonNull(getCommand("linkedlife")).setExecutor(new Command());
        getServer().getPluginManager().registerEvents(new Listener(), this);
    }

    public static Main getInstance() {
        return instance;
    }
    public Manager getManager() {
        return manager;
    }
    public Listener getListener() {
        return listener;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
