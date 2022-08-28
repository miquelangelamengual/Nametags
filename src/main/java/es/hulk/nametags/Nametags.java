package es.hulk.nametags;

import dev.panda.rank.RankManager;
import es.hulk.nametags.listeners.NameTagApplyListener;
import es.hulk.nametags.utils.ConfigFile;
import lombok.Getter;
import me.colejedwards.nametag.Nametag;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public final class Nametags extends JavaPlugin {

    public static boolean isPlaceholderAPI = false;

    private Nametag nametag;
    private ConfigFile settingsConfig;
    private RankManager rankManager;

    @Override
    public void onEnable() {
        this.nametag = new Nametag(this);
        this.rankManager = new RankManager(this);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            isPlaceholderAPI = true;
        }

        this.settingsConfig = new ConfigFile(this, "settings");
        this.loadListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadListeners() {
        List<Listener> listeners = Collections.singletonList(new NameTagApplyListener(this));

        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
