package ir.syrent.velocitysparky.spigot

import ir.syrent.velocitysparky.spigot.listener.SparkyViolationListener
import org.bukkit.plugin.java.JavaPlugin

class VelocitySparkySpigot : JavaPlugin() {

    override fun onEnable() {
        instance = this
        this.server.messenger.registerOutgoingPluginChannel(this, VELOCITYSPARKY_CHANNEL)
        registerListeners()
    }

    override fun onDisable() {
        this.server.messenger.unregisterOutgoingPluginChannel(this);
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(SparkyViolationListener(), this)
    }

    companion object {
        lateinit var instance: VelocitySparkySpigot
            private set
        val VELOCITYSPARKY_CHANNEL = "velocitysparky:main"
    }
}