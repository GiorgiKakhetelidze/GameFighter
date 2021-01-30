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
            character.needShop(DurabilityShop(), enemy);
        }

        println("Our Hero killed all enemies")
    }

}