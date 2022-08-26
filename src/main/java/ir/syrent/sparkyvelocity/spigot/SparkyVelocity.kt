package ir.syrent.sparkyvelocity.spigot

import ir.syrent.sparkyvelocity.spigot.listener.SparkyViolationListener
import org.bukkit.plugin.java.JavaPlugin

class SparkyVelocity : JavaPlugin() {

    override fun onEnable() {
        instance = this
        this.server.messenger.registerOutgoingPluginChannel(this, VELOCITYSPARKY_CHANNEL)
        registerListeners()
    }

    override fun onDisable() {
        this.server.messenger.unregisterOutgoingPluginChannel(this)
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(SparkyViolationListener(), this)
    }

    companion object {
        lateinit var instance: SparkyVelocity
            private set
        const val VELOCITYSPARKY_CHANNEL = "velocitysparky:main"
    }
}