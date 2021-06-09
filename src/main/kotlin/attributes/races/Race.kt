package attributes.races

import org.hexworks.amethyst.api.base.BaseAttribute

abstract class Race: BaseAttribute() {
    abstract val name: String
}