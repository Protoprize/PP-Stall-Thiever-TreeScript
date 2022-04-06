
import data.Configs
import org.powbot.api.Color
import org.powbot.api.rt4.walking.model.Skill
import org.powbot.api.script.paint.PaintBuilder

class Paint(val script: Thiever) {

    init {
        val paint = PaintBuilder()
            .addString("Status:") { script.lastLeaf.name }
            .trackSkill(Skill.Thieving)
            .backgroundColor(Color.BLACK_A)
        script.addPaint(paint.build())

    }
}