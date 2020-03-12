package com.kkomi.treeisland.plugin.bag.model

import com.kkomi.treeisland.plugin.bag.model.entity.Bag

interface BagDataSource {

    fun getBag(name: String): Bag?

    fun getBagList(): List<Bag>

    fun addBag(bag: Bag)

    fun editBag(bag: Bag)

    fun removeBag(name: String)

    fun reloadBag()

    fun saveBag(bag: Bag)
}