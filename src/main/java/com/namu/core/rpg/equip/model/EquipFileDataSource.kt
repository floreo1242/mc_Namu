package com.namu.core.rpg.equip.model

import com.kkomi.devlibrary.FileDataSource
import com.namu.core.rpg.equip.model.entity.Equip
import java.io.File

class EquipFileDataSource(
        dataFolder : File,
        classType: Class<Equip>
) : EquipDataSource, FileDataSource<Equip>(dataFolder, classType){

        override fun editEquip(equip: Equip) {
                setValue(equip.uuid, equip)
                saveFile(equip.uuid, equip)
        }

        override fun getEquip(uuid: String): Equip? {
                return getValue(uuid)
        }

        override fun saveEquip(equip: Equip) {

        }

}