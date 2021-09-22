package extensions

import entities.CombatItem
import entities.EquipmentHolder
import entities.Item
import entities.ItemHolder
import entities.Spell
import game.GameContext
import org.hexworks.amethyst.api.Message
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType

typealias GameEntity<T> = Entity<T, GameContext>

typealias AnyGameEntity = GameEntity<EntityType>

typealias GameMessage = Message<GameContext>

typealias GameItem = GameEntity<Item>

typealias GameItemHolder = GameEntity<ItemHolder>

typealias GameCombatItem = GameEntity<CombatItem>

typealias GameEquipmentHolder = GameEntity<EquipmentHolder>

typealias GameSpell = GameEntity<Spell>
