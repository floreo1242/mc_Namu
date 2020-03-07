package com.kkomi.treeisland.plugin.enhance.model

import com.kkomi.treeisland.plugin.itemdb.model.entity.StatOption

data class EnhanceItemMeta(
        var scrollLimit: Int,
        val scrollOptions: MutableList<Pair<StatOption, Int>>
)