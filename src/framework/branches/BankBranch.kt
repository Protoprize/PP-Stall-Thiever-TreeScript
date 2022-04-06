package framework.branches

import Thiever
import data.Configs
import framework.leaves.DepositItems
import framework.leaves.OpenBank
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class NeedToBank(script: Thiever): Branch<Thiever>(script, "Need to bank?") {
    override val failedComponent: TreeComponent<Thiever> = CanSteal(script)
    override val successComponent: TreeComponent<Thiever> = HandleBank(script)

    override fun validate(): Boolean {
        return Configs.bank && Inventory.isFull()
    }
}

class HandleBank(script: Thiever): Branch<Thiever>(script, "Handling bank") {
    override val failedComponent: TreeComponent<Thiever> = OpenBank(script)
    override val successComponent: TreeComponent<Thiever> = DepositItems(script)

    override fun validate(): Boolean {
        return Bank.opened()
    }
}