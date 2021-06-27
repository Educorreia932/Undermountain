package builders

import attributes.CreatureAttributes
import attributes.classes.Fighter
import attributes.classes.PlayerClass
import attributes.races.Human
import attributes.races.PlayerRace
import builders.EntityFactory.newPlayer
import entities.Player
import extensions.GameEntity

class CharacterBuilder {
    private lateinit var playerRace: PlayerRace
    private lateinit var playerClass: PlayerClass
    private lateinit var creatureAttributes: CreatureAttributes
    
    fun withRace(): CharacterBuilder {
        playerRace = Human()
        
        return this
    }
    
    fun withClass(): CharacterBuilder{
        playerClass = Fighter()
        
        return this
    }
    
    fun withAttributes(): CharacterBuilder {
        creatureAttributes = CreatureAttributes()
        
        return this
    }
    
    fun build(): GameEntity<Player> = newPlayer(
        playerRace = playerRace,
        playerClass = playerClass,
        creatureAttributes = creatureAttributes
    )
}