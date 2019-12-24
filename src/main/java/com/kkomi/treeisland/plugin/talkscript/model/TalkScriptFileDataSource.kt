package com.kkomi.treeisland.plugin.talkscript.model

import com.kkomi.treeisland.library.FileDataSource
import com.kkomi.treeisland.plugin.talkscript.model.entity.TalkScript
import java.io.File

class TalkScriptFileDataSource(
        dataFile: File
) : TalkScriptDataSource, FileDataSource<TalkScript>(dataFile, TalkScript::class.java) {

    fun reloadData() {
        loadFiles()
    }

    override fun getTalkScript(name: String): TalkScript? {
        return getValue(name)
    }

    override fun getTalkScriptList(): List<TalkScript> {
        return getValueList()
    }

    override fun addTalkScript(talkScript: TalkScript) {
        setValue(talkScript.name, talkScript)
        saveFile(talkScript.name, talkScript)
    }

    override fun setTalkScript(talkScript: TalkScript) {
        setValue(talkScript.name, talkScript)
        saveFile(talkScript.name, talkScript)
    }

    override fun removeTalkScript(name: String) {
        removeValue(name)
        deleteFile(name)
    }

}