package systems

import extensions.position
import game.GameContext
import game.GameTileRepository
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D

object FogOfWar: BaseBehavior<GameContext>() {
    override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        val (world, _, _, player) = context
        
        world.findVisiblePositionsFor(player).forEach{ position -> 
            world.fetchBlockAt(position.toPosition3D(player.position.z))
                .map { block ->
                    block.top = GameTileRepository.EMPTY
                }
        }
        
        return true
    }
}