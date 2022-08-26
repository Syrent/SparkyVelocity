package ir.syrent.sparkyvelocity.spigot

import ir.syrent.sparkyvelocity.spigot.listener.SparkyViolationListener
import org.bukkit.plugin.java.JavaPlugin

/**
 * The plugin spigot-side is responsible for sending player violation data to Velocity via SparkyAPI
 * whenever a player receive violation
 * @see SparkyViolationListener
 */
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
        /**
         * The name should be same name that used in Velocity main class
         * @see ir.syrent.sparkyvelocity.SparkyVelocity
         */
        const val VELOCITYSPARKY_CHANNEL = "velocitysparky:main"
    }
}