import java.util.*

class DurabilityShop : IShop {
    private val resistancePrice = 100
    private val heatPointPrice = 60
    private val recoverPrice = 200

    override fun showMenu(character: Character) {
        println("Welcome to the Shop")
        println("1 : Resistance 0.1 - Price : $resistancePrice Gold")
        println("2 : HeatPoint 50 - Price : $heatPointPrice Gold")
        println("3 : Recover full HeatPoint - Price : $recoverPrice Gold")
    }

    override fun getMinimumPrice(): Int = minOf(resistancePrice, heatPointPrice, recoverPrice)

    override fun request(): Int {
        val scanner = Scanner(System.`in`)
        print("Please Enter choice (From 1 to 3): ")
        return when (val choice = scanner.nextInt()) {
            in 1..3 -> choice
            else -> request()
        }
    }

    override fun buyItem(num: Int, character: Character) {
        when {
            num == 1 && character.getGold(this) >= this.resistancePrice -> {
                character.setResistance(this, 0.1)
                character.setGold(this, -100)
                println("You bought 0.1 resistance for ${this.resistancePrice} gold, going to fight.")
            }
            num == 2 && character.getGold(this) >= this.heatPointPrice -> {
                character.setheatPoint(this, 50.0)
                character.setGold(this, -60)
                println("You bought 50 heatPoint for ${this.heatPointPrice} gold, going to fight.")
            }
            num == 3 && character.getGold(this) >= this.recoverPrice -> {
                character.setheatPoint(this, character.getStartingHeatPoint(this))
                character.setGold(this, -200)
                println("You bought recover full health for ${this.recoverPrice} gold, going to fight.")
            }
            else -> println("You have not enough gold, going to another enemy.")
        }
    }
}