package com.kkomi.treeisland.plugin.itemdb.model.entity

enum class StatOption(
        val strName: String,
        val isPer: Boolean
) {
    STRENGTH("근력", false),
    STAMINA("체력", false),
    MIND("정신", false),
    NATURE("맷집", false),
    MIN_DAMAGE("최소 공격력", false),
    MAX_DAMAGE("최대 공격력", false),
    STATIC_DAMAGE("고정 대미지", false),
    DEFENSE("방어력", false),
    MAX_HP("최대 체력", false),
    MAX_MP("최대 마나", false),
    CRITICAL_CHANCE("크리티컬 확률", true),// Double
    CRITICAL_DAMAGE("크리티컬 대미지", true),// Double
    WALK_SPEED("이동속도", true), // Double
    ATTACK_SPEED("공격속도", true),
    PLUS_GOLD("골드 획득량", true)
}