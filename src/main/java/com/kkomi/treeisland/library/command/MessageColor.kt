package com.kkomi.treeisland.library.command

object MessageColor {
    fun color(s: String): String {
        return replaceColorCode('&', '§', s)
    }

    fun strip(s: String): String {
        return replaceColorCode('§', '&', s)
    }

    private fun replaceColorCode(from: Char, to: Char, s: String): String {
        var i = s.indexOf(from)

        if (i == -1)
            return s

        val b = s.toCharArray()
        val length = b.size - 1

        while (i < length) {
            if (b[i] == from && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = to
                b[i + 1] = Character.toLowerCase(b[i + 1])
            }

            i++
        }

        return String(b)
    }
}