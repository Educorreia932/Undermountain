package attributes

import extensions.GameItem
import org.hexworks.amethyst.api.base.BaseAttribute

class Inventory : BaseAttribute() {
    private val currentItems = mutableListOf<GameItem>()
    
    val items: List<GameItem>
        get() = currentItems.toList()
    
    val size: Int
        get() = currentItems.count()
    
    val isFull: Boolean
        get() = false

    fun addItem(item: GameItem): Boolean {                       
        return if (isFull.not()) {
            currentItems.add(item)
        } else false
    }

    fun removeItem(item: GameItem): Boolean {
        return currentItems.remove(item)
    }
}