package net.snapecraft.bedwarsfix.events;


import io.github.bedwarsrel.*;
import io.github.bedwarsrel.events.BedwarsPlayerKilledEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerSpectator implements Listener {

    @EventHandler
    public void onPlayerDeath(BedwarsPlayerKilledEvent e) {
        if(isSpectator(e.getPlayer())) {
            e.getPlayer().setGameMode(GameMode.SPECTATOR);
            e.getPlayer().setFlying(true);
        }
    }

    public static boolean isSpectator(Player p) {
        return BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p).isSpectator(p);
    }
}
