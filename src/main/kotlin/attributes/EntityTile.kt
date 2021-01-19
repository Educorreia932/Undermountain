package attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.zircon.api.data.Tile

data class EntityTile(val tile: Tile = Tile.empty()) : BaseAttribute()
