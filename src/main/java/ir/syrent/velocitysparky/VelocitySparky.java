package ir.syrent.velocitysparky;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "VelocitySparky",
        name = "VelocitySparky",
        version = BuildConstants.VERSION,
        description = "Sync sparky logs between back-end servers",
        authors = {"Syrent"}
)
public class VelocitySparky {

    @Inject
    private Logger logger;

    @Subscribe

    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
