package org.example

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Main : ModInitializer {
    val logger: Logger = LoggerFactory.getLogger("modid")

    override fun onInitialize() {
        logger.info("Mod initialized!")
    }
}