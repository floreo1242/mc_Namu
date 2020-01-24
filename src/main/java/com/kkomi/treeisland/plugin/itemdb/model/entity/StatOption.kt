package com.kkomi.treeisland.plugin.itemdb.model.entity

enum class StatOption(
        val strName: String,
        val isPer: Boolean
) {
    STRENGTH("힘", false),
    DEXTERITY("민첩", false),
    INTELLIGENCE("지능", false),
    DEFENSE("방어", false),
    AGILITY("회피", false),
    MIN_DAMAGE("최소 공격력", false),
    MAX_DAMAGE("최대 공격력", false),
    SKILL_DAMAGE("스킬 공격력", false),
    HEALTH("체력", false),
    MANA("마나", false),
    BONUS_GOLD("골드 획득량", true),
    BONUS_XP("경험치 획득량", true),
    WALK_SPEED("이동속도", true)
}