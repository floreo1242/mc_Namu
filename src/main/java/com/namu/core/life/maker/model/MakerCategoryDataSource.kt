package com.namu.core.life.maker.model

import com.namu.core.life.maker.model.entity.MakerCategory

interface MakerCategoryDataSource {

    fun getMakerCategory(name: String): MakerCategory?

    fun getMakerCategoryList(): List<MakerCategory>

    fun createMakerCategory(makerCategory: MakerCategory)

    fun editMakerCategory(makerCategory: MakerCategory)

    fun removeMakerCategory(name: String)

    fun saveMakerCategory(makerCategory: MakerCategory)

    fun reloadMakerCategory()

}