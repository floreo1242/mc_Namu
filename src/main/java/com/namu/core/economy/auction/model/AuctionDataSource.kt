package com.namu.core.economy.auction.model

import com.namu.core.economy.auction.model.entity.Auction
import com.namu.core.economy.auction.model.entity.AuctionStuff

interface AuctionDataSource {

    fun getAuction(): Auction

    fun addAuctionStuff(auctionStuff: AuctionStuff)

    fun removeAuctionStuff(uuid: String)

    fun saveAuction()

}