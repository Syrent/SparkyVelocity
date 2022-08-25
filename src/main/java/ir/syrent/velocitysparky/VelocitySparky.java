package ir.syrent.velocitysparky;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import ir.syrent.velocitysparky.listener.PluginMessageListener;
import org.slf4j.Logger;

@Plugin(
        id = "velocitysparky",
        name = "VelocitySparky",
        version = BuildConstants.VERSION,
        description = "Sync sparky logs between back-end servers",
        authors = {"Syrent"}
)
public class VelocitySparky {

    private ProxyServer server;
    private Logger logger;
    public static VelocitySparky instance;
    public static Gson GSON;
    public static final ChannelIdentifier VELOCITYSPARKY_CHANNEL = MinecraftChannelIdentifier.create("velocitysparky", "main");

    @Inject
    public VelocitySparky(ProxyServer server, Logger logger) {
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
