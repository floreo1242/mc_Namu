package com.kkomi.library.extension

fun String.replaceChatColorCode() : String {
    return replace("&","§")
}