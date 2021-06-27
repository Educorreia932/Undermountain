package builders

import attributes.Abilities
import attributes.classes.PlayerClass
import attributes.races.PlayerRace
import builders.EntityFactory.newPlayer
import entities.Player
import extensions.GameEntity

class CharacterBuilder {
    private lateinit var playerRace: PlayerRace
    private lateinit var playerClass: PlayerClass
    private lateinit var abilities: Abilities

    fun withRace(playerRace: PlayerRace): CharacterBuilder {
        this.playerRace = playerRace

        return this
    }

    fun withClass(playerClass: PlayerClass): CharacterBuilder {
        this.playerClass = playerClass

        return this
    }

    fun withAttributes(
        strength: Int,
        dexterity: Int,
        constitution: Int,
        wisdom: Int,
        intelligence: Int,
        charisma: Int
    ): CharacterBuilder {
        abilities = Abilities.create(
            strength,
            dexterity,
            constitution,
            wisdom,
            intelligence,
            charisma
        )
        
        abilities.applyBonuses(playerRace.abilityBonuses)
        
        return this
    }

    fun build(): GameEntity<Player> = newPlayer(
        playerRace = playerRace,
        playerClass = playerClass,
        abilities = abilities
    )
}