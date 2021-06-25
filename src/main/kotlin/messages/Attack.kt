package messages

import entities.Combatant
import extensions.GameEntity
import game.GameContext

data class Attack(
    override val context: GameContext,
    override val source: GameEntity<Combatant>,
    override val target: GameEntity<Combatant>
) : EntityAction<Combatant, Combatant>
