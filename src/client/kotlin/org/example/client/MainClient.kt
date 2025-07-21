package org.example.client

import net.fabricmc.api.ClientModInitializer
import org.example.Main

object MainClient : ClientModInitializer {
    override fun onInitializeClient() {
        Main.logger.info("Mod initialized on client!")
    }
}