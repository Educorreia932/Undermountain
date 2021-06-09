package extensions

import game.GameContext
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.isSuperclassOf

inline fun <reified T : EntityType> Iterable<AnyGameEntity>.filterType(): List<Entity<T, GameContext>> {
    return filter { T::class.isSuperclassOf(it.type::class) }.toList() as List<Entity<T, GameContext>>
}

inline fun <reified T : EntityType> AnyGameEntity.whenTypeIs(fn: (Entity<T, GameContext>) -> Unit) {
    if (this.type::class.isSubclassOf(T::class)) {
        fn(this as Entity<T, GameContext>)
    }
}