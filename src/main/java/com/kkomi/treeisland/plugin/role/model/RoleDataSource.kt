package com.kkomi.treeisland.plugin.role.model

import com.kkomi.treeisland.plugin.role.model.entity.Role

interface RoleDataSource {

    fun getRole(name: String): Role?

    fun getRoleList(): List<Role>

    fun addRole(role: Role)

    fun editRole(role: Role)

    fun removeRole(name: String)

    fun reloadRole()

}