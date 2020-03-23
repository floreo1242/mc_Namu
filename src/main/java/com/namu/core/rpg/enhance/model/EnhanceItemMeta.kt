package com.namu.core.rpg.enhance.model

import com.namu.core.utility.itemdb.model.entity.StatType

data class EnhanceItemMeta(
        var scrollLimit: Int,
        val scrollOptions: MutableList<Pair<StatType, Int>>
)