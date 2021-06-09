package attributes.classes

import org.hexworks.amethyst.api.base.BaseAttribute

abstract class Class: BaseAttribute() {
    abstract val name: String
    abstract val description: String
    abstract val hitDie: Int
}