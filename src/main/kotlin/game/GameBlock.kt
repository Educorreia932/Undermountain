package game

import game.GameTileRepository.EMPTY
import game.GameTileRepository.FLOOR
import game.GameTileRepository.WALL
import kotlinx.collections.immutable.persistentMapOf
import org.hexworks.zircon.api.data.BlockTileType
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.data.base.BaseBlock

class GameBlock(content: Tile = FLOOR) : BaseBlock<Tile>(
    emptyTile = EMPTY,
    tiles = persistentMapOf(BlockTileType.CONTENT to content)
) {
    val isFloor: Boolean
        get() = content == FLOOR

    val isWall: Boolean
        get() = content == WALL
}