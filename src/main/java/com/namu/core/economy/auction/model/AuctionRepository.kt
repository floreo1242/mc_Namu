package com.namu.core.economy.auction.model

import com.namu.core.MainCore
import com.namu.core.economy.auction.model.entity.Auction
import com.namu.core.economy.auction.model.entity.AuctionStuff
import java.io.File

object AuctionRepository {

    private val auctionDataSource = AuctionFileDataSource(File(MainCore.auctionPlugin.dataFolder.path + "/"))

    fun getAuction(): Auction {
        return auctionDataSource.getAuction()
    }

    fun addAuctionStuff(auctionStuff: AuctionStuff) {
        return auctionDataSource.addAuctionStuff(auctionStuff)
    }

    fun removeAuctionStuff(uuid: String) {
        return auctionDataSource.removeAuctionStuff(uuid)
    }

    fun saveAuction() {
        auctionDataSource.saveAuction()
    }

}