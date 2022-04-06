package framework.leaves

import Thiever
import data.Configs
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class DropItems(script: Thiever): Leaf<Thiever>(script, "Dropping items") {
    override fun execute() {
            for (i in Inventory.stream().list()) {
                if(Configs.dropItems) {
                    if(Configs.dropItemArray!!.contains(i.name())) {
                        Inventory.drop(i, false)
                    }
                } else {
                    Inventory.drop(i,false)
                }
            }
        Condition.wait { !canDrop() }
    }
}

fun canDrop(): Boolean {
    for (i in Inventory.stream().list()) {
        if(Configs.dropItems) {
            if(Configs.dropItemArray!!.contains(i.name())) {
                return true
            }
        } else {
            return false
        }
    }
    return false
}