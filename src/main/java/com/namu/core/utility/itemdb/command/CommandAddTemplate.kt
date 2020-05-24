package com.namu.core.utility.itemdb.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.utility.itemdb.model.EquipmentType
import com.namu.core.utility.itemdb.model.CustomItemRepository
import com.namu.core.utility.itemdb.model.entity.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandAddTemplate : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = ""

    override val usage: String = ""

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        val equipmentList = listOf(
                CustomItem(
                        code = "낡은가죽모자",
                        description = listOf(
                                "&f낡은 가죽으로 만들어져 방어력은 살짝 낮지만 가볍다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.HELMET, listOf(StatOption(StatType.HEALTH, 10))),
                        material = Material.LEATHER_HELMET,
                        name = "&f낡은 가죽 모자",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "낡은가죽상의",
                        description = listOf(
                                "&f낡은 가죽으로 만들어져 방어력은 살짝 낮지만 가볍다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.CHEST_PLATE, listOf(StatOption(StatType.HEALTH, 10))),
                        material = Material.LEATHER_CHESTPLATE,
                        name = "&f낡은 가죽 상의",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "낡은가죽하의",
                        description = listOf(
                                "&f낡은 가죽으로 만들어져 방어력은 살짝 낮지만 가볍다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.LEGGINGS, listOf(StatOption(StatType.HEALTH, 10))),
                        material = Material.LEATHER_LEGGINGS,
                        name = "&f낡은 가죽 하의",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "낡은가죽신발",
                        description = listOf(
                                "&f낡은 가죽으로 만들어져 방어력은 살짝 낮지만 가볍다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.BOOTS, listOf(StatOption(StatType.WALK_SPEED, 10))),
                        material = Material.LEATHER_BOOTS,
                        name = "&f낡은 가죽 신발",
                        consumptionOption = null
                )

                ,
                CustomItem(
                        code = "벌집귀걸이",
                        description = listOf(
                                "&f달달한 냄새가 은은하게 풍겨온다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.EAR_RING, listOf(StatOption(StatType.MANA, 350))),
                        material = Material.HONEYCOMB,
                        name = "&6벌집 귀걸이",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "슬라임반지",
                        description = listOf(
                                "&f손가락에 찰싹 붙어 잘 빠지지 않는다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.RING, listOf(StatOption(StatType.HAND_DEXTERITY, 10))),
                        material = Material.SLIME_BALL,
                        name = "&a슬라임 반지",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "신비한실목걸이",
                        description = listOf(
                                "&f연약한 실처럼 보이지만 끊어지지 않는 실로 만들어진 목걸이다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.NECK_RING, listOf(StatOption(StatType.MANA, 120))),
                        material = Material.STRING,
                        name = "&f신비한 실 목걸이",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "풀잎팔찌",
                        description = listOf(
                                "&f풀잎으로 만들어진 팔찌이다.",
                                "&f행운을 가져다 준다는 전설이 내려온다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.RING_SNACK, listOf(StatOption(StatType.MANA, 710))),
                        material = Material.LARGE_FERN,
                        name = "&a풀잎 팔찌",
                        consumptionOption = null
                )

                ,
                CustomItem(
                        code = "수련자의검",
                        description = listOf(
                                "&f돌로 만들어진 수련자의 검이다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.WEAPON, listOf(StatOption(StatType.STRENGTH, 10))),
                        material = Material.STONE_SWORD,
                        name = "&6수련자의 검",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "엑스칼리버",
                        description = listOf(
                                "&f전설로 내려저 오는 엑스칼리버이다.",
                                "&f잠자는 관리자도 부를 수 있다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.WEAPON_OUT_POLY, listOf(StatOption(StatType.STRENGTH, 10))),
                        material = Material.GOLDEN_SWORD,
                        name = "&e엑스칼리버",
                        consumptionOption = null
                )

                ,
                CustomItem(
                        code = "거북이견갑",
                        description = listOf(
                                "&f거북이 등껍질로 만들어서 단단하다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.GunyGab, listOf(StatOption(StatType.HEALTH, 10))),
                        material = Material.SCUTE,
                        name = "&a거북이 견갑",
                        consumptionOption = null
                ),
                CustomItem(
                        code = "다크윙날개",
                        description = listOf(
                                "&f다크윙의 날개를 재료로 가공된 날개이다."
                        ),
                        equipmentOption = EquipmentOption(0, EquipmentType.WING, listOf(StatOption(StatType.HEALTH, 10))),
                        material = Material.ELYTRA,
                        name = "&7다크윙날개",
                        consumptionOption = null
                )
        )

        equipmentList
                .forEach {
                    CustomItemRepository.createCustomItem(it)
                    if (sender is Player) {
                        sender.inventory.addItem(it.toItemStack())
                    }
                }
        sender.sendInfoMessage("아이템을 생성하였습니다.")
        return true
    }

}