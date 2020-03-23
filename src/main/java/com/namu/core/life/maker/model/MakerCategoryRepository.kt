package com.namu.core.life.maker.model

import com.namu.core.MainCore
import com.namu.core.life.maker.model.entity.MakerCategory
import java.io.File

object MakerCategoryRepository {

    private val makerCategoryDataSource = MakerCategoryFileDataSource(File(MainCore.makerPlugin.dataFolder.path + "/category"), MakerCategory::class.java)

    fun getMakerCategory(name: String): MakerCategory? {
        return makerCategoryDataSource.getMakerCategory(name)
    }

    fun getMakerCategoryList(): List<MakerCategory> {
        return makerCategoryDataSource.getMakerCategoryList()
    }

    fun createMakerCategory(makerCategory: MakerCategory) {
        makerCategoryDataSource.createMakerCategory(makerCategory)
    }

    fun editMakerCategory(makerCategory: MakerCategory) {
        makerCategoryDataSource.editMakerCategory(makerCategory)
    }
    
    fun saveMakerCategory(makerCategory: MakerCategory) {
        makerCategoryDataSource.saveMakerCategory(makerCategory)
    }            

    fun removeMakerCategory(name: String) {
        makerCategoryDataSource.removeMakerCategory(name)
    }

    fun reloadMakerCategory() {
        makerCategoryDataSource.reloadMakerCategory()
    }

}