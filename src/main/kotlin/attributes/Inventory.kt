package attributes

import extensions.GameItem
import org.hexworks.amethyst.api.base.BaseAttribute

class Inventory : BaseAttribute() {
    private val currentItems = mutableListOf<GameItem>()
    
    val items: List<GameItem>
        get() = currentItems.toList()
    
    val size: Int
        get() = currentItems.count()
    
    fun addItem(item: GameItem) {
        currentItems.add(item)
    }

    fun removeItem(item: GameItem) {
        currentItems.remove(item)
    }
}