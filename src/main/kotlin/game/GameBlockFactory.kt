package game

import builders.EntityFactory

object GameBlockFactory {
    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock.createWith(EntityFactory.newWall())
}