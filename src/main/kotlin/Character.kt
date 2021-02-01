import java.util.*

class Character(
    private var heatPoint: Double,
    weaknessEnemyTypeFirst: String,
    weaknessEnemyTypeSecond: String,
    private val attackStrength: Int
) {

    val weaknessTypeList = ArrayList<String>()
    private var resistance = 1.0
    private var gold = 0
    private val startingHeatPoint = heatPoint
    var enemyKilled = 0

    init {
        if (weaknessEnemyTypeFirst != weaknessEnemyTypeSecond) {
            weaknessTypeList.add(weaknessEnemyTypeFirst)
            weaknessTypeList.add(weaknessEnemyTypeSecond)
        } else
            weaknessTypeList.add(weaknessEnemyTypeFirst)
    }

    //Updated: fighting both side
    fun fight(enemy: IEnemy) {

        while (enemy.heatPoint > 0 && heatPoint > 0) {
            makeDamage(enemy)
            receiveDamage(enemy)
        }
    }

    private fun receiveDamage(enemy: IEnemy) {
        heatPoint = if (weaknessTypeList.contains(enemy.type()))
            heatPoint - enemy.attackStrength() * 2 / resistance
        else
            heatPoint - enemy.attackStrength() / resistance

        if (heatPoint <= 0) throw CharacterDeathException("Our beloved hero has been fallen")
    }

    private fun makeDamage(enemy: IEnemy) {
        enemy.heatPoint -= this.attackStrength

        if (enemy.heatPoint <= 0)
            takeReward(enemy)
    }

    private fun takeReward(enemy: IEnemy) {
        when {
            enemy.reward() == Reward.DRAGON_BAFF -> {
                heatPoint += 100
                gold += 30
            }
            enemy.reward() == Reward.MUTANT_ARMOR -> {
                resistance += 0.1
                gold += 20
            }
            enemy.reward() == Reward.ZOMBIE -> gold += 40
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
}