import java.util.*

class DurabilityShop : IShop{
    val resistancePrice = 100
    val heatPointPrice = 60
    val recoverPrice = 200

    override fun showMenu(character: Character) {
        println("Welcome to the Shop")
        println("1 : Resistance 0.1 - Price : $resistancePrice Gold")
        println("2 : HeatPoint 50 - Price : $heatPointPrice Gold")
        println("3 : Recover full HeatPoint - Price : $recoverPrice Gold")
    }

    override fun request(): Int {
        val scanner = Scanner(System.`in`)
        print("Please Enter choice (From 1 to 3): ")
        return when (val choice = scanner.nextInt()) {
            in 1..3 -> choice
            else -> request()
        }
    }
}