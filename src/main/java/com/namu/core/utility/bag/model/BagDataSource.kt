package com.namu.core.utility.bag.model

import com.namu.core.utility.bag.model.entity.Bag

interface BagDataSource {

    fun getBag(uuid: String): Bag?

    fun getBagList(): List<Bag>

    fun createBag(bag: Bag)

    fun editBag(bag: Bag)

    fun removeBag(name: String)

    fun reloadBag()

    fun saveBag(bag: Bag)
}