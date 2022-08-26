package ir.syrent.sparkyvelocity.spigot.listener

import ac.sparky.api.SparkyAPI
import ac.sparky.api.events.SparkyViolationEvent
import com.google.common.io.ByteStreams
import com.google.gson.Gson
import com.google.gson.JsonObject
import ir.syrent.sparkyvelocity.spigot.SparkyVelocity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

@Suppress("UnstableApiUsage")
class SparkyViolationListener : Listener {

    @EventHandler
    private fun onSparkyFlag(event: SparkyViolationEvent) {
        val customCheckName = event.customCheckName
        val checkType = event.checkType
        val violation = event.violation
        val isExperimental = event.isExperimental
        val player = event.player

        val violationData = JsonObject()
        violationData.addProperty("username", player.name)
        violationData.addProperty("name", customCheckName)
        violationData.addProperty("type", checkType)
        violationData.addProperty("violation", violation)
        violationData.addProperty("is_experimental", isExperimental)
        violationData.addProperty("version", SparkyAPI.getVersion())

        val byteArrayDataOutput = ByteStreams.newDataOutput()
        byteArrayDataOutput.writeUTF(Gson().toJson(violationData))

        player.sendPluginMessage(SparkyVelocity.instance, SparkyVelocity.VELOCITYSPARKY_CHANNEL, byteArrayDataOutput.toByteArray())
    }
}