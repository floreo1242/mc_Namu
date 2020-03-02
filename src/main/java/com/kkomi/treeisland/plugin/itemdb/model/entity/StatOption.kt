package com.kkomi.treeisland.plugin.itemdb.model.entity

enum class   StatOption(
        val strName: String,
        val isPer: Boolean
) {
    STRENGTH("힘", false),   // 공격력
    DEXTERITY("민첩", false), // 크리티컬 확률
    INTELLIGENCE("지능", false), // 마나 소모량 줄임
    VITALITY("방어", false), // 데미지 감소
    AGILITY("회피", false), // 데미지 회피

    MIN_DAMAGE("최소 공격력", false),
    MAX_DAMAGE("최대 공격력", false),

    HEALTH("체력", false),
    WALK_SPEED("이동속도", true),

    BONUS_GOLD("골드 획득량", true),
    BONUS_XP("경험치 획득량", true)
}