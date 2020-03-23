package com.namu.core.economy.auction.model

import com.namu.core.economy.auction.model.entity.Auction
import com.namu.core.economy.auction.model.entity.AuctionStuff
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class AuctionFileDataSource(
        dataFolder: File
) : AuctionDataSource {

    private var auction: Auction
    private var configFile = File(dataFolder, "auction.yml")
    private var configuration = YamlConfiguration.loadConfiguration(configFile)

    init {
        if (!configuration.getKeys(false).contains("data")) {
            configuration.set("data", Auction(mutableListOf()))
            configuration.save(configFile)
        }
        auction = configuration.get("data") as Auction
    }

    override fun getAuction(): Auction {
        return auction
    }

    override fun addAuctionStuff(auctionStuff: AuctionStuff) {
        auction.auctionStuffList.add(auctionStuff)
    }

    override fun removeAuctionStuff(uuid: String) {
        auction.auctionStuffList.removeIf { it.uuid == uuid }
    }

    override fun saveAuction() {
        configuration.set("data", auction)
        configuration.save(configFile)
    }

}