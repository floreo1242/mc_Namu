package com.kkomi.treeisland.plugin.talkscript.model

import com.kkomi.treeisland.Treeisland
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript

object TalkScriptRepository {

    private var talkScriptDataSource: TalkScriptDataSource = TalkScriptFileDataSource(Treeisland.talkScriptPlugin.dataFolder)

    fun getTalkScript(name: String): TalkScript? {
        return talkScriptDataSource.getTalkScript(name)
    }

    fun editTalkScript(talkScript: TalkScript) {
        talkScriptDataSource.setTalkScript(talkScript)
    }

    fun addTalkScript(talkScript: TalkScript) {
        talkScriptDataSource.addTalkScript(talkScript)
    }

    fun removeTalkScript(name: String) {
        talkScriptDataSource.removeTalkScript(name)
    }

    fun reloadTalkScript() {
        if (talkScriptDataSource is TalkScriptFileDataSource) {
            (talkScriptDataSource as TalkScriptFileDataSource).reloadData()
        } else {
            println("TalkScriptDataSource is remote")
        }
    }

}