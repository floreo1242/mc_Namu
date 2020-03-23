package com.namu.core.rpg.role.model

import com.namu.core.rpg.role.model.entity.Role

interface RoleDataSource {

    fun getRole(name: String): Role?

    fun getRoleList(): List<Role>

    fun createRole(role: Role)

    fun editRole(role: Role)

    fun removeRole(name: String)

    fun reloadRole()

    fun saveRole(role: Role)

}