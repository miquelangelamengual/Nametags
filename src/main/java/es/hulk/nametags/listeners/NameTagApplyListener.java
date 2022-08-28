package es.hulk.nametags.listeners;

import dev.panda.rank.IRank;
import es.hulk.nametags.Nametags;
import me.colejedwards.nametag.PlayerNametag;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NameTagApplyListener implements Listener {

    private final Nametags plugin;

    public NameTagApplyListener(Nametags plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        IRank rank = plugin.getRankManager().getRank();
        ConfigurationSection section = plugin.getSettingsConfig().getConfiguration().getConfigurationSection("RANK_NAMETAG");

        if (plugin.getNametag().getNametagHandler().doesPlayerHaveNametag(player)) {
            plugin.getNametag().getNametagHandler().setNametag(player, new PlayerNametag("", player.getName(), ""));
        }

        if (section != null) {
            String rankNametag = section.getString(rank.getRankName(player.getUniqueId()));
            if (rankNametag != null) {
                PlayerNametag nametag = new PlayerNametag(rankNametag, player.getName(), "");
                plugin.getNametag().getNametagHandler().setNametag(player, nametag);
            }
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.getNametag().getNametagHandler().setNametag(player, new PlayerNametag("", player.getName(), ""));
    }
}
