package com.kkomi.treeisland.plugin.talkscript.model

import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript

interface TalkScriptDataSource {

    // SELECT
    fun getTalkScript(name: String): TalkScript?

    // SELECT
    fun getTalkScriptList(): List<TalkScript>

    // INSERT
    fun addTalkScript(talkScript: TalkScript)

    // UPDATE
    fun setTalkScript(talkScript: TalkScript)

    // DELETE
    fun removeTalkScript(name: String)

}