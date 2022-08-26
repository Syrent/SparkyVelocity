package ir.syrent.sparkyvelocity;

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
 * The code is not related to SparkyBungee at all, but it does the same thing
 *
 * The plugin receives the player data from back-end servers using SparkyAPI and plugin messaging {@link PluginMessageListener}
 * You need to install plugin on all your back-end servers and Velocity
 * You need to disable Sparky default notification to prevent message duplications
 */
@Plugin(
        id = "sparkyvelocity",
        name = "SparkyVelocity",
        version = BuildConstants.VERSION,
        description = "Sync sparky logs between back-end servers",
        authors = {"Syrent"}
)
public class SparkyVelocity {

    public static SparkyVelocity instance;
    private final ProxyServer server;
    private final Logger logger;

    /**
     * Create new minecraft channel identifier
     * The name should be same name that used in {@link ir.syrent.sparkyvelocity.spigot.SparkyVelocitySpigot}
     */
    public static final ChannelIdentifier SPARKYVELOCITY_CHANNEL = MinecraftChannelIdentifier.create("sparkyvelocity", "main");

    @Inject
    public SparkyVelocity(ProxyServer server, Logger logger) {
        instance = this;

        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        registerListeners();
    }

    private void registerListeners() {
        server.getEventManager().register(this, new PluginMessageListener());
        server.getChannelRegistrar().register(SPARKYVELOCITY_CHANNEL);
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }
}
