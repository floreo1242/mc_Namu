package com.kkomi.treeisland.plugin.itemdb.model.entity

enum class StatOption(
        val strName: String
) {
    BASIC_STAT("올스텟"),
    STRENGTH("근력"),
    MAGIC("마법력"),
    LUCK("행운"),
    STAMINA("체력"),
    MIN_DAMAGE("최소 공격력"),
    MAX_DAMAGE("최대 공격력"),
    PHYSICS_MIN_DAMAGE("물리 최소대미지"),// Double
    PHYSICS_MAX_DAMAGE("물리 최대대미지"),// Double
    MAGIC_MIN_DAMAGE("마법 최소대미지"),// Double
    MAGIC_MAX_DAMAGE("마법 최대대미지"),// Double
    CRITICAL_CHANCE("크리티컬 확률"),// Double
    CRITICAL_DAMAGE("크리티컬 대미지"),// Double
    PHYSICS_PENETRATE("물리 관통력"),// Double
    MAGIC_PENETRATE("마법 관통력"),// Double
    STATIC_DAMAGE("고정 대미지"),
    DEFENSE("방어력"),
    MAX_HP("최대 체력"),
    MAX_MP("최대 마나"),
    WALK_SPEED("이동속도"); // Double
}