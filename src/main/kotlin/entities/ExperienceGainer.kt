package entities

import attributes.Experience
import extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType

interface ExperienceGainer: EntityType

val GameEntity<ExperienceGainer>.experience: Experience
    get() = findAttribute(Experience::class).get()

