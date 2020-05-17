package com.namu.core.utility.itemdb.model.entity

enum class StatType(
        val strName: String,
        val isPer: Boolean,
        val isStat: Boolean
) {
    STRENGTH("힘", false, true),
    HEALTH("체력", false, true),
    MANA("마나", false, true),
    WALK_SPEED("이동속도", true, true),
    CRITICAL_CHANCE("크리티컬 확률", false, true),
    HAND_DEXTERITY("손재주", false, true),

//    MIN_DAMAGE("최소 공격력", false),
//    MAX_DAMAGE("최대 공격력", false),

    BONUS_GOLD("골드 획득량", true, false),
    BONUS_XP("경험치 획득량", true, false)
}