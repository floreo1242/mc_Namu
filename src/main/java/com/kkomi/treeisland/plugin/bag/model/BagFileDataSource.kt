package com.kkomi.treeisland.plugin.bag.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.bag.model.entity.Bag
import java.io.File

class BagFileDataSource(
        dataFolder: File,
        classType: Class<Bag>
) : BagDataSource, FileDataSource<Bag>(dataFolder, classType) {

    override fun getBag(uuid: String): Bag? {
        return getValue(uuid)
    }

    override fun getBagList(): List<Bag> {
        return getValueList()
    }

    override fun addBag(bag: Bag) {
        setValue(bag.uuid, bag)
    }

    override fun editBag(bag: Bag) {
        setValue(bag.uuid, bag)
    }

    override fun saveBag(bag: Bag) {
        saveFile(bag.uuid, bag)
    }

    override fun removeBag(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadBag() {
        loadFiles()
    }

}