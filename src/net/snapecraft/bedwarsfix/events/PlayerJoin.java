package net.snapecraft.bedwarsfix.events;

import io.github.bedwarsrel.events.BedwarsPlayerJoinEvent;
import io.github.bedwarsrel.events.BedwarsPlayerJoinedEvent;
import net.snapecraft.bedwarsfix.BedwarsFix;
import io.github.bedwarsrel.BedwarsRel;
import io.github.bedwarsrel.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PlayerJoin implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(BedwarsFix.getInstance(), new Runnable() {
            @Override
            public void run() {
                onJoined(e);
            }
        }, 25L);

    }
    public void onJoined(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(BedwarsRel.getInstance().getGameManager().getGameOfPlayer(p) == null) {
            if(BedwarsRel.getInstance().getGameManager().getGames().size() != 0) {
                Game target = null;
                for(Game g : BedwarsRel.getInstance().getGameManager().getGames()) {
                    if(g.getPlayerAmount() < g.getMaxPlayers()) {
                        target = g;
                        break;
                    }
                }
                if(target != null) {
                    BedwarsFix.getInstance().getServer().dispatchCommand(p,"bw join " + target.getName());
                } else {
                    p.kickPlayer(BedwarsFix.getInstance().getConfig().getString("join.allgamesfull"));
                }
            } else {
                p.kickPlayer(BedwarsFix.getInstance().getConfig().getString("join.nogamerunning"));
            }
        }
    }








}
