package net.snapecraft.bedwarsfix;

import net.snapecraft.bedwarsfix.events.PlayerJoin;
import net.snapecraft.bedwarsfix.events.PlayerSpectator;
import org.bukkit.plugin.java.JavaPlugin;

public class BedwarsFix extends JavaPlugin {
    static BedwarsFix instance;
    public void onEnable() {
        instance = this;
        getConfig().addDefault("spectator.gmchange", false);
        getConfig().addDefault("join.forceingame", true);
        getConfig().addDefault("join.nogamerunning", "Es läuft aktuell kein Spiel!");
        getConfig().addDefault("join.nogamerunning", "Alle Spiele sind voll!");
        getConfig().options().copyDefaults(true);


        if(getConfig().getBoolean("spectator.gmchange"))
            getServer().getPluginManager().registerEvents(new PlayerSpectator(), this);
        if(getConfig().getBoolean("join.forceingame"))
            getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        System.out.println("Bedwars Fix Loaded");
    }

    public static BedwarsFix getInstance() {
        return instance;
    }
}
