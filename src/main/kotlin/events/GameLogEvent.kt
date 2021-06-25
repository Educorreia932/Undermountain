package events

import org.hexworks.cobalt.events.api.Event

data class GameLogEvent(
    val text: String,
    override val emitter: Any
) : Event