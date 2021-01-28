import java.util.*

class Shop {
    companion object {
        private const val resistancePrice = 100
        const val heatPointPrice = 60
        private const val recoverPrice = 200

        fun showMenu(character: Character) {
            println("Welcome to the Shop")
            println("1 : Resistance 0.1 - Price : $resistancePrice Gold")
            println("2 : HeatPoint 50 - Price : $heatPointPrice Gold")
            println("3 : Recover full HeatPoint - Price : $recoverPrice Gold")
            println("please enter number from 1 to 3")
            buyItem(validateInput(), character)
        }

        private fun buyItem(num: Int, character: Character) {
            when {
                num == 1 && character.gold >= resistancePrice -> {
                    character.resistance += 0.1
                    character.gold -= 100
                    println("You bought 0.1 resistance for $resistancePrice gold, going to fight.")
                }
                num == 2 && character.gold >= heatPointPrice -> {
                    character.heatPoint += 50
                    character.gold -= 60
                    println("You bought 50 heatPoint for $heatPointPrice gold, going to fight.")
                }
                num == 3 && character.gold >= recoverPrice -> {
                    character.heatPoint =
                        character.startingHeatPoint
                    character.gold -= 200
                    println("You bought recover full health for $recoverPrice gold, going to fight.")
                }
                else -> println("You have not enough gold, going to another enemy.")
            }
        }

        private fun validateInput(): Int {

            val scanner = Scanner(System.`in`)
            var input = 0

            while (!scanner.hasNextInt() || input <= 0) {
                if (scanner.hasNextInt()) {
                    input = scanner.next().toInt()
                    if (input == 1 || input == 2 || input == 3)
                        break
                } else {
                    scanner.hasNextInt()
                    scanner.next()
                }

                println("Enter Int number 1 to 3 to buy item.")
            }

            return input

        }
    }

}