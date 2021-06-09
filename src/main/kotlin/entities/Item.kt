package entities

import attributes.EntityTile
import attributes.ItemIcon
import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.GraphicalTile
import org.hexworks.zircon.api.data.Tile

interface Item: EntityType

val GameEntity<Item>.tile: Tile
    get() = findAttribute(EntityTile::class).get().tile

val GameEntity<Item>.iconTile: GraphicalTile
    get() = findAttribute(ItemIcon::class).get().iconTile

object Sword : BaseEntityType(
    name = "Sword",
), Weapon