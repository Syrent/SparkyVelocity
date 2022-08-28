package ir.syrent.sparkyvelocity.listener

import com.google.gson.JsonParser
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PluginMessageEvent
import ir.syrent.sparkyvelocity.SparkyVelocity
import net.kyori.adventure.text.minimessage.MiniMessage
import java.nio.charset.StandardCharsets

class PluginMessageListener {

    @Subscribe
    private fun onPluginMessage(event: PluginMessageEvent) {
        if (!event.identifier.equals(SparkyVelocity.SPARKYVELOCITY_CHANNEL)) return

        val rawMessage = String(event.data, StandardCharsets.UTF_8)
        val message = JsonParser.parseString(rawMessage.substring(2)).asJsonObject
        val players = SparkyVelocity.instance.server.allPlayers
        val gamemode = players.find { player -> player.username == message["username"].asString }?.currentServer?.get()?.serverInfo?.name ?: "UNKNOWN"

        for (player in players) {
            if (player.hasPermission("sparkyvelocity.notify")) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<hover:show_text:'<light_purple>■ <blue>Version: <aqua>${message["version"]}\n<light_purple>■ <blue>Gamemode: <aqua>${gamemode}\n\n<yellow>Click to change gamemode'><click:suggest_command:'/joinqueue ${gamemode}'><gradient:dark_red:gold><bold>ANTICHEAT</bold> » <gray><gold>${message["username"]}</gold> failed <gold>${(if (message["is_experimental"].asBoolean) "*" else "") + message["name"].asString}</gold> (<gold>${message["type"].asString}</gold>) <red>x${message["violation"].asString}</click></hover>"
                ))
            }
        }
    }
}