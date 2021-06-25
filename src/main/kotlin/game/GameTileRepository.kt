package game

import game.GameColors.ACCENT_COLOR
import game.GameColors.AMBER
import game.GameColors.FLOOR_BACKGROUND
import game.GameColors.FLOOR_FOREGROUND
import game.GameColors.WALL_BACKGROUND
import game.GameColors.WALL_FOREGROUND
import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.data.CharacterTile
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.Symbols

object GameTileRepository {
    val EMPTY: CharacterTile = Tile.empty()

    val FLOOR: CharacterTile = Tile.newBuilder()
        .withCharacter(Symbols.INTERPUNCT)
        .withForegroundColor(FLOOR_FOREGROUND)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()

    val WALL: CharacterTile = Tile.newBuilder()
        .withCharacter('#')
        .withForegroundColor(WALL_FOREGROUND)
        .withBackgroundColor(WALL_BACKGROUND)
        .buildCharacterTile()

    val PLAYER = Tile.newBuilder()
        .withCharacter('@')
        .withForegroundColor(ACCENT_COLOR)
        .buildCharacterTile()

    val GOBLIN = Tile.newBuilder()
        .withCharacter('g')
        .withBackgroundColor(FLOOR_BACKGROUND)
        .withForegroundColor(AMBER)
        .buildCharacterTile()

    val SWORD = Tile.newBuilder()
        .withCharacter(')')
        .withForegroundColor(ANSITileColor.BRIGHT_WHITE)
        .withBackgroundColor(FLOOR_BACKGROUND)
        .buildCharacterTile()
}

