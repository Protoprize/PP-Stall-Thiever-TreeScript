package framework.leaves

import Thiever
import data.Configs
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf


class RunToStall(script: Thiever): Leaf<Thiever>(script, "Running to stall") {
    override fun execute() {
        Movement.walkTo(Configs.startTile)
    }
}

class Steal(script: Thiever): Leaf<Thiever>(script, "Stealing") {
    override fun execute() {
        val stall: GameObject = Objects.stream().at(Configs.stall!![0].tile.derive(1,1)).action("Steal-from").first()
        script.log.info(stall.name)
        if(stall.valid() && stall.interact("Steal-from")) {
            if(Condition.wait { Players.local().animation() != -1 }) {
                Condition.wait { Players.local().animation() == -1 }
            }
        }
    }
}