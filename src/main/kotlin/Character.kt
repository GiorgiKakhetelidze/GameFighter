import java.util.*

class Character(private var heatPoint: Double, private val weaknessEnemyType: String, private val attackStrength: Int) {

    private var resistance = 1.0
    private var gold = 0
    private val startingHeatPoint = heatPoint
    var enemyKilled = 0

    fun fight(enemy: IEnemy) {
        heatPoint = if (enemy.type() == weaknessEnemyType)
            heatPoint - enemy.attackStrength() * 2 / resistance
        else
            heatPoint - enemy.attackStrength() / resistance

        finishFight(enemy)
    }

    private fun finishFight(enemy: IEnemy) {
        when {
            heatPoint <= 0 -> throw CharacterDeathException("Our beloved hero has been fallen")
            enemy.reward() == Reward.DRAGON_BAFF -> {
                heatPoint += 100
                gold += 30
            }
            else -> {
                resistance += 0.1
                gold += 20
            }
        }
    }

    private fun enterShop(shop: DurabilityShop) {
        val scn = Scanner(System.`in`)
        var input = scn.next().toLowerCase()

        while (input != "y" && input != "n") {
            println("Please enter y or n")
            input = scn.next().toLowerCase()
        }

        if (input == "y") {
            shop.showMenu(this)
            buyItem(shop.request(), shop)
        }

    }

    fun needShop(shop: DurabilityShop, enemy: IEnemy) {
        if (this.gold < shop.heatPointPrice)
            println("you killed enemy ${enemy.type()} and got reward ${enemy.reward()} going to another enemy")
        else {
            println(
                "you killed enemy ${enemy.type()} and got reward ${enemy.reward()}, you have enough " +
                        "gold(minimum) to rest and visit shop y/n ?"
            )
            enterShop(shop)
        }
    }


    private fun buyItem(num: Int, shop: DurabilityShop) {
        when {
            num == 1 && this.gold >= shop.resistancePrice -> {
                this.resistance += 0.1
                this.gold -= 100
                println("You bought 0.1 resistance for ${shop.resistancePrice} gold, going to fight.")
            }
            num == 2 && this.gold >= shop.heatPointPrice -> {
                this.heatPoint += 50
                this.gold -= 60
                println("You bought 50 heatPoint for ${shop.heatPointPrice} gold, going to fight.")
            }
            num == 3 && this.gold >= shop.recoverPrice -> {
                this.heatPoint =
                    this.startingHeatPoint
                this.gold -= 200
                println("You bought recover full health for ${shop.recoverPrice} gold, going to fight.")
            }
            else -> println("You have not enough gold, going to another enemy.")
        }
    }

    fun showStats() {
        println("Gold: $gold \n Armor: $resistance \n HeatPoint: $heatPoint Enemies killed: $enemyKilled")
    }
}