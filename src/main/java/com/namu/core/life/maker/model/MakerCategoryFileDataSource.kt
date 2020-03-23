package com.namu.core.life.maker.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.life.maker.model.MakerCategoryDataSource
import com.namu.core.life.maker.model.entity.MakerCategory
import java.io.File

class MakerCategoryFileDataSource(
        dataFolder: File,
        classType: Class<MakerCategory>
) : MakerCategoryDataSource, FileDataSource<MakerCategory>(dataFolder, classType) {

    override fun getMakerCategory(name: String): MakerCategory? {
        return getValue(name)
    }

    override fun getMakerCategoryList(): List<MakerCategory> {
        return getValueList()
    }

    override fun createMakerCategory(makerCategory: MakerCategory) {
        setValue(makerCategory.name, makerCategory)
        saveFile(makerCategory.name, makerCategory)
    }

    override fun editMakerCategory(makerCategory: MakerCategory) {
        setValue(makerCategory.name, makerCategory)
        saveFile(makerCategory.name, makerCategory)
    }

    override fun removeMakerCategory(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun saveMakerCategory(makerCategory: MakerCategory) {
        saveFile(makerCategory.name, makerCategory)
    }

    override fun reloadMakerCategory() {
        loadFiles()
    }

}