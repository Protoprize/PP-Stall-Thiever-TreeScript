package data

import org.powbot.api.Tile
import org.powbot.api.event.GameObjectActionEvent
import org.powbot.api.rt4.GameObject

class Configs {
    companion object {

        var pickOneDropOne: Boolean? = false
        var dropItemArray: Array<String>? = null
        var dropItemList: String = ""
        var dropItems: Boolean = false
        var bank: Boolean = false
        var stall: ArrayList<GameObjectActionEvent>? = null
        var startTile: Tile? = null
    }
}