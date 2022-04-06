package framework.branches

import Thiever
import data.Configs
import framework.leaves.DropItems
import org.powbot.api.Area
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class NeedToDrop(script: Thiever): Branch<Thiever>(script, "Need to drop?") {
    override val failedComponent: TreeComponent<Thiever> = NeedToBank(script)
    override val successComponent: TreeComponent<Thiever> = DropItems(script)

    override fun validate(): Boolean {
        if(Inventory.isFull() && !Configs.bank) {
            return true
        }
        if(Configs.dropItems && (Inventory.isFull() || Configs.pickOneDropOne!!)) {
            for (i in Inventory.stream().list()) {
                if(Configs.dropItemArray!!.contains(i.name())) {
                    return true
                }
            }
        }

        return false
    }

}