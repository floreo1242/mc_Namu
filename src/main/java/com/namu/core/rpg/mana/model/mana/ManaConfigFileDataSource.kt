package com.namu.core.rpg.mana.model.mana

import com.namu.core.rpg.mana.model.mana.entity.ManaConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ManaConfigFileDataSource(
        dataFolder: File
) : ManaConfigDataSource {

    private var config: ManaConfig? = null
    private val configFile = File(dataFolder, "config.yml")
    private val configuration = YamlConfiguration.loadConfiguration(configFile)

    override fun getManaConfig(): ManaConfig {
        if (config == null) reloadManaConfig()
        return config!!
    }

    override fun setManaConfig(manaConfig: ManaConfig) {
        configuration.set("config", manaConfig)
        configuration.save(configFile)
    }

    override fun reloadManaConfig() {
        if (!configuration.contains("config")) {
            configuration.set("config", ManaConfig(
                    mapOf(
                            1 to 100,
                            2 to 200,
                            3 to 300
                    )
            ))
            configuration.save(configFile)
        }
        config = configuration.get("config") as ManaConfig
    }

}