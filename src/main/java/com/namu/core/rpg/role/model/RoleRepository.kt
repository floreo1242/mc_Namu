package com.namu.core.rpg.role.model

import com.namu.core.MainCore
import com.namu.core.rpg.role.model.entity.Role
import java.io.File

object RoleRepository {

    private val roleDataSource = RoleFileDataSource(File(MainCore.rolePlugin.dataFolder.path + "/roles"), Role::class.java)

    init {
        if (getRoleList().isEmpty()) {
            createRole(Role("모험가", "pain"))
        }
    }

    fun getRole(name: String): Role? {
        return roleDataSource.getRole(name)
    }

    fun getRoleList(): List<Role> {
        return roleDataSource.getRoleList()
    }

    fun createRole(role: Role) {
        roleDataSource.createRole(role)
    }

    fun saveRole(role: Role) {
        roleDataSource.saveRole(role)
    }

    fun removeRole(name: String) {
        roleDataSource.removeRole(name)
    }

    fun reloadRole() {
        roleDataSource.reloadRole()
    }

}