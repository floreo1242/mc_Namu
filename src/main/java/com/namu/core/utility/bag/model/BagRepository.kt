package com.namu.core.utility.bag.model

import com.namu.core.utility.bag.model.entity.Bag
import com.namu.core.MainCore

object BagRepository {

    private val bagDataSource = BagFileDataSource(MainCore.bagPlugin.dataFolder, Bag::class.java)

    fun getBag(uuid: String): Bag? {
        return bagDataSource.getBag(uuid)
    }

    fun getBagList(): List<Bag> {
        return bagDataSource.getBagList()
    }

    fun createBag(bag: Bag) {
        bagDataSource.createBag(bag)
    }

    fun editBag(bag: Bag) {
        bagDataSource.editBag(bag)
    }

    fun removeBag(name: String) {
        bagDataSource.removeBag(name)
    }

    fun saveBag(bag: Bag) {
        bagDataSource.saveBag(bag)
    }

    fun reloadBag() {
        bagDataSource.reloadBag()
    }

}