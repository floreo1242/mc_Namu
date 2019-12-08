package com.kkomi.treeisland.library.extension

import java.text.DecimalFormat

private val decimalFormat = DecimalFormat("#,##0")

fun Int.toMoneyFormat(): String {
    return decimalFormat.format(this)
}