package framework.branches

import Thiever
import data.Configs
import framework.leaves.RunToStall
import framework.leaves.Steal
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class CanSteal(script: Thiever): Branch<Thiever>(script, "Can steal?") {
    override val failedComponent: TreeComponent<Thiever> = RunToStall(script)
    override val successComponent: TreeComponent<Thiever> = Steal(script)

    override fun validate(): Boolean {
        return Configs.startTile == Players.local().tile()
    }
}