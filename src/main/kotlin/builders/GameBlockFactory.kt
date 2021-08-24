package builders

import game.GameBlock
import game.GameTileRepository

object GameBlockFactory {
    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock.createWith(EntityFactory.newWall())
}