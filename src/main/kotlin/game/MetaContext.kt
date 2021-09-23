package game

import enums.GameState
import extensions.GameMessage

object MetaContext {
    var gameState: GameState = GameState.PLAYER_TURN
    var suspendedAction: GameMessage? = null
}