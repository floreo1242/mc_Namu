package com.namu.core.utility.itemdb.model

enum class EquipmentType(
        val index: Int,
        val korName : String
) {

    /**
     * 귀걸이 10 투구 14  무기  외형
     * 목걸이  상의  견갑  겉날개
     * 반지   하의  탈것  탈것 외형
     * 반지   신발  의자  의자 외형
     */

    EAR_RING(10,"귀걸이"),
    NECK_RING(19,"목걸이"),
    RING(28,"반지"), // or 37
    RING_SNACK(37,"팔찌"), // or 37

    HELMET(14,"모자"),
    CHEST_PLATE(23,"상의"),
    LEGGINGS(32,"하의"),
    BOOTS(41,"신발"),

    WEAPON(15,"무기"),
    GunyGab(24,"견갑"),

    WEAPON_OUT_POLY(16,"무기 외형"),
    WING(25,"날개")

}