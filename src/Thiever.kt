import data.Configs
import framework.branches.NeedToDrop
import org.powbot.api.script.*
import org.powbot.api.script.tree.TreeComponent
import org.powbot.api.script.tree.TreeScript
import java.util.*
import java.util.regex.Pattern

@ScriptManifest(
    name = "PP Stall Thiever",
    version = "1.0",
    description = "Make sure to use spots where you won't be attacked.",
    singleTapRequired = true,
    category = ScriptCategory.Thieving
)

@ScriptConfiguration.List(
    [
        ScriptConfiguration(
            name = "Tile",
            description = "Choose your safe tile",
            optionType = OptionType.TILE
        ),
        ScriptConfiguration(name = "Stall", description = "Select your stall", optionType = OptionType.GAMEOBJECT_ACTIONS),
        ScriptConfiguration(name = "Bank", description = "Bank items?", optionType = OptionType.BOOLEAN),
        ScriptConfiguration(name = "Drop items", description = "Do you want to drop any items?", optionType = OptionType.BOOLEAN),
        ScriptConfiguration(
            name = "Drop item list",
            description = "Specify the exact names of items you want to drop, use a comma to separate them.",
            optionType = OptionType.STRING,
            defaultValue = "Bread,Cake,Tea",
            visible = false
        ),
        ScriptConfiguration(name = "Pick drop", description = "Pick one, drop one?", optionType = OptionType.BOOLEAN, visible = false),
    ]
)

class Thiever: TreeScript() {
    override val rootComponent: TreeComponent<*>
        get() = NeedToDrop(this)

    override fun onStart() {
        Configs.startTile = getOption("Tile")
        Configs.stall = getOption("Stall")
        Configs.bank = getOption("Bank")

        Configs.dropItems = getOption("Drop items")
        if (Configs.dropItems) {
            val tempList: String = getOption("Drop item list")
            Configs.dropItemArray = tempList.split(",").toTypedArray()
            Configs.pickOneDropOne = getOption("Pick drop")
        }
        Paint(this)
    }

    @ValueChanged(keyName = "Drop items")
    fun stanceChange(valueChanged: Boolean) {
        updateVisibility("Drop item list", valueChanged)
        updateVisibility("Pick drop", valueChanged)
    }
}
fun main() {
    Thiever().startScript()
}