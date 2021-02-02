import java.util.*

class Character(
    private var heatPoint: Double,
    private val attackStrength: Int,
    weaknessEnemyTypeFirst: String,
    weaknessEnemyTypeSecond: String
) {
    private var resistance = 1.0
    private var gold = 0
    private val startingHeatPoint = heatPoint
    val weaknessTypeList = ArrayList<String>()
    var enemyKilled = 0

    init {
        if (weaknessEnemyTypeFirst != weaknessEnemyTypeSecond) {
            weaknessTypeList.add(weaknessEnemyTypeFirst)
            weaknessTypeList.add(weaknessEnemyTypeSecond)
        } else
            weaknessTypeList.add(weaknessEnemyTypeFirst)
    }

    //Only IShop can access private fields
    fun getResistance(shop: IShop) = resistance

    fun setResistance(shop: IShop, resistance: Double) {
        this.resistance += resistance
    }

    fun getGold(shop: IShop) = gold

    fun setGold(shop: IShop, gold: Int) {
        this.gold += gold
    }

    fun getheatPoint(shop: IShop) = heatPoint

    fun setheatPoint(shop: IShop, heatPoint: Double) {
        this.heatPoint += heatPoint
    }

    fun getStartingHeatPoint(shop: IShop) = startingHeatPoint

    //Updated: fighting both side
    fun fight(enemy: IEnemy) {

        while (enemy.heatPoint > 0 && heatPoint > 0) {
            makeDamage(enemy)
            receiveDamage(enemy)
        }
    }

    private fun receiveDamage(enemy: IEnemy) {
        if (enemy is IFlyingEnemy)
            enemy.flyAttackBonus()

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

    private fun enterShop(shop: IShop) {
        val scn = Scanner(System.`in`)
        var input = scn.next().toLowerCase()

        while (input != "y" && input != "n") {
            println("Please enter y or n")
            input = scn.next().toLowerCase()
        }

        if (input == "y") {
            shop.showMenu(this)
            shop.buyItem(shop.request(), this)
        }
    }

    fun needShop(shop: IShop, enemy: IEnemy) {
        if (this.gold < shop.getMinimumPrice())
            println("you killed enemy ${enemy.type()} and got reward ${enemy.reward()} going to another enemy")
        else {
            println(
                "you killed enemy ${enemy.type()} and got reward ${enemy.reward()}, you have enough " +
                        "gold(minimum) to rest and visit shop y/n ?"
            )
            enterShop(shop)
        }
    }
}