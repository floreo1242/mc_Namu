package com.kkomi.treeisland.plugin.role.model

import com.kkomi.devlibrary.FileDataSource
import com.kkomi.treeisland.plugin.role.model.entity.Role
import java.io.File

class RoleFileDataSource(
        dataFolder: File,
        classType: Class<Role>
) : RoleDataSource, FileDataSource<Role>(dataFolder, classType) {

    override fun getRole(name: String): Role? {
        return getValue(name)
    }

    override fun getRoleList(): List<Role> {
        return getValueList()
    }

    override fun addRole(role: Role) {
        setValue(role.name, role)
        saveFile(role.name, role)
    }

    override fun editRole(role: Role) {
        setValue(role.name, role)
        saveFile(role.name, role)
    }

    override fun removeRole(name: String) {
        removeValue(name)
        deleteFile(name)
    }

    override fun reloadRole() {
        loadFiles()
    }

}