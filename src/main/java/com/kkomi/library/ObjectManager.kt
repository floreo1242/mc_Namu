package com.kkomi.library

import com.google.common.collect.ImmutableList

import java.io.File
import java.util.*
import java.util.Collections.unmodifiableCollection
import java.util.Collections.unmodifiableSet
import java.util.logging.Logger

abstract class ObjectManager<T>(
        folder: File,
        protected var logger: Logger
) {

    val EXT = ".yml"

    val EXTlen = EXT.length

    /**
     * get Files Folder
     *
     * @return
     */
    var folder: File
        protected set

    protected lateinit var objectByName: TreeMap<String, T>

    init {
        this.folder = folder
        loadData()
    }

    abstract fun loadData()

    fun getFolderFiles() : List<File> {
        folder.mkdirs()
        val filesList = folder.listFiles { _, name -> name.endsWith(EXT) } ?: return listOf()
        return filesList.toList()
    }

    protected fun getObject(key: String): T? {
        return this.objectByName[key]
    }

    protected var keys: Set<String>? = null
        get() {
            val temp = field
            return if (temp == null) {
                field = unmodifiableSet(objectByName.keys)
                field!!
            } else {
                temp
            }
        }

    protected var objects: Collection<T>? = null
        get() {
            val temp = field
            return if (temp == null) {
                field = unmodifiableCollection(objectByName.values)
                field!!
            } else {
                temp
            }
        }


    protected var objectList: List<T>? = null
        get() {
            val temp = field
            return if (temp == null) {
                field = ImmutableList.copyOf(objectByName.values)
                field!!
            } else {
                temp
            }
        }

    fun justRemove(name: String) {
        this.objectByName.remove(name)
        this.objectList = null
    }

}
