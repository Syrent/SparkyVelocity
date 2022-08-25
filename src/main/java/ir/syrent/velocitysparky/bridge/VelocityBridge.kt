package ir.syrent.velocitysparky.bridge

import com.google.common.io.ByteStreams
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.velocitypowered.api.proxy.Player
import ir.syrent.velocitysparky.VelocitySparky

class VelocityBridge {

    fun sendViolationData(player: Player, data: JsonObject) {
        val byteArrayDataOutput = ByteStreams.newDataOutput()
        val violationData = JsonObject()
        violationData.addProperty("username", data["username"].asString)
        violationData.addProperty("gamemode", data["gamemode"].asString)
        violationData.addProperty("name", data["name"].asString)
        violationData.addProperty("type", data["type"].asString)
        violationData.addProperty("violation", data["violation"].asString)
        byteArrayDataOutput.writeUTF(Gson().toJson(violationData))
        player.currentServer.get()
            .sendPluginMessage(VelocitySparky.VELOCITYSPARKY_CHANNEL, byteArrayDataOutput.toByteArray())
    }
}