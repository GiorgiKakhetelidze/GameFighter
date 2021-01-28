import java.util.*

class EnemyBaseImpl(scanner: Scanner) : IEnemyBase {

    private val enemies = ArrayList<IEnemy>()

    init {
        while (scanner.hasNext()) {
            if (scanner.next() == Dragon.TYPE)
                add(Dragon(scanner.nextInt(), scanner.nextInt()))
            else
                add(Mutant(scanner.nextInt(), scanner.nextInt()))
        }
    }

    override fun add(enemy: IEnemy) {
        enemies.add(enemy)
    }

    override fun enter(character: Character) {
        println("Fight started")

        for (enemy in enemies) {
            character.fight(enemy)
            character.enemyKilled += 1
            if (character.gold < Shop.heatPointPrice)
                println("you killed enemy ${enemy.type()} and got reward ${enemy.reward()} going to another enemy")
            else {
                println(
                    "you killed enemy ${enemy.type()} and got reward ${enemy.reward()}, you have enough " +
                            "gold(minimum) to rest and visit shop y/n ?"
                )
                enterShop(character)
            }
        }

        println("Our Hero killed all enemies")
    }

    private fun enterShop(character: Character) {

        val scn = Scanner(System.`in`)
        var input = scn.next().toLowerCase()

        while (input != "y" && input != "n") {
            println("Please enter y or n")
            input = scn.next().toLowerCase()
        }

        if (input == "y")
            Shop.showMenu(character)
    }
}