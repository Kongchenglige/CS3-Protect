package cn.iasoc.cs3protect;

import org.bukkit.plugin.java.JavaPlugin;

public final class CS3_Protect extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getLogger().info("CS3_Protect has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CS3_Protect has been disabled!");
    }
}
