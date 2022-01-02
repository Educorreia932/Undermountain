package functions

import events.GameLogEvent
import org.hexworks.cobalt.databinding.api.Cobalt

fun logGameEvent(text: String, emitter: Any) {
    Cobalt.eventbus.publish(GameLogEvent(text, emitter))
}