package com.kkomi.treeisland.plugin.quest.model.entity

enum class QuestAction(
        val description: String
) {
    BLOCK_BREAK("%s 블럭을 %d 만큼 부수세요."),
    BLOCK_PLACE("%s 블럭을 %d 만큼 설치하세요."),
    KILL_ENTITY("%s 을(를) %d 만큼 사냥하세요."),
    FARMING_ITEM("%s 을(를) %d 만큼 구하세요."),
    MOVE_LOCATION("퀘스트 설명의 좌표로 이동하세요")
}