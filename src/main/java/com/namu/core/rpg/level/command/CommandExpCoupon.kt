package com.namu.core.rpg.level.command

import com.kkomi.devlibrary.command.ArgumentList
import com.kkomi.devlibrary.command.CommandComponent
import com.kkomi.devlibrary.extension.sendInfoMessage
import com.namu.core.rpg.level.model.entity.BonusCoupon
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandExpCoupon : CommandComponent() {

    override val argumentsLength: Int = 0

    override val description: String = "경험치 쿠폰을 발급합니다."

    override val usage: String = "<Magnification> <Duration>"

    override fun onCommand(sender: CommandSender, label: String, command: Command, componentLabel: String, args: ArgumentList): Boolean {
        (sender as Player).inventory.addItem(BonusCoupon(args.nextInt(),args.nextInt()).toItemStack())
        sender.sendInfoMessage("경험치 쿠폰을 발급하였습니다.")
        return true
    }

}