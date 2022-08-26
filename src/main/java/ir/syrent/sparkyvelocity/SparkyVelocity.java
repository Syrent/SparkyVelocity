package ir.syrent.sparkyvelocity;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import ir.syrent.sparkyvelocity.listener.PluginMessageListener;
import org.slf4j.Logger;


/**
 * Velocity version of SparkyBungee (https://github.com/AkramLZ/SparkyBungee)
 */
@Plugin(
        id = "velocitysparky",
        name = "VelocitySparky",
        version = BuildConstants.VERSION,
        description = "Sync sparky logs between back-end servers",
        authors = {"Syrent"}
)
public class SparkyVelocity {

    private final ProxyServer server;
    private final Logger logger;
    public static SparkyVelocity instance;
    public static Gson GSON;
    /**
     * Create new minecraft channel identifier
     * The name should be same name that used in {@link ir.syrent.sparkyvelocity.spigot.SparkyVelocity}
     */
    public static final ChannelIdentifier VELOCITYSPARKY_CHANNEL = MinecraftChannelIdentifier.create("velocitysparky", "main");

    @Inject
    public SparkyVelocity(ProxyServer server, Logger logger) {
        instance = this;
        GSON = new Gson();

        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        registerListeners();
    }

    private void registerListeners() {
        server.getEventManager().register(this, new PluginMessageListener());
        server.getChannelRegistrar().register(VELOCITYSPARKY_CHANNEL);
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }
}
