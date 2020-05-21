package com.namu.core.utility.itemdb.model

enum class EquipmentType(
        val index: Int
) {

    /**
     * 귀걸이 10 투구 14  무기  외형
     * 목걸이  상의  견갑  겉날개
     * 반지   하의  탈것  탈것 외형
     * 반지   신발  의자  의자 외형
     */

    EAR_RING(10),
    NECK_RING(19),
    RING(28), // or 37
    RING_SUB(37), // or 37

    HELMET(14),
    CHEST_PLATE(23),
    LEGGINGS(32),
    BOOTS(41),

    WEAPON(15),
    GunyGab(24),

    WEAPON_OUT_POLY(16),
    WING(25)

}