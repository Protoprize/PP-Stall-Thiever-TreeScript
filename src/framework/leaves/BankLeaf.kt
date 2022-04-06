package framework.leaves

import Thiever
import data.Configs
import org.powbot.api.Condition
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.script.tree.Leaf

class OpenBank(script: Thiever): Leaf<Thiever>(script, "Handling bank") {
    override fun execute() {
        if(!Bank.open()) {
            Movement.moveToBank(false)
        }  else {
            Condition.wait { Bank.opened() }
        }
    }
}

class DepositItems(script: Thiever): Leaf<Thiever>(script, "Handling items") {
    override fun execute() {
        if(Bank.depositInventory()) {
            Condition.wait { Inventory.isEmpty() }
        }
    }
}