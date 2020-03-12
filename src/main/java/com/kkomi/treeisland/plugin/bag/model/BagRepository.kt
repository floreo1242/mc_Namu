package com.kkomi.treeisland.plugin.bag.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.bag.model.entity.Bag
import java.io.File

object BagRepository {

    private val bagDataSource = BagFileDataSource(Treeisland.bagPlugin.dataFolder, Bag::class.java)

    fun getBag(uuid: String): Bag? {
        return bagDataSource.getBag(uuid)
    }

    fun getBagList(): List<Bag> {
        return bagDataSource.getBagList()
    }

    fun addBag(bag: Bag) {
        bagDataSource.addBag(bag)
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