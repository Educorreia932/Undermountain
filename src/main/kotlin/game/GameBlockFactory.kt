package game

object GameBlockFactory {
    fun floor() = GameBlock(GameTileRepository.FLOOR)

    fun wall() = GameBlock(GameTileRepository.WALL)
}