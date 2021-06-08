package extensions

import attributes.types.Item
import attributes.types.ItemHolder
import game.GameContext
import org.hexworks.amethyst.api.Message
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

typealias GameEntity<T> = Entity<T, GameContext>

typealias AnyGameEntity = GameEntity<EntityType>

typealias GameMessage = Message<GameContext>

typealias GameItem = GameEntity<Item>

typealias GameItemHolder = GameEntity<ItemHolder>
