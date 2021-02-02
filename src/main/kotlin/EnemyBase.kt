import java.util.*

class EnemyBase(scanner: Scanner) : IEnemyBase {

    private val enemies = ArrayList<IEnemy>()

    init {
        while (scanner.hasNext()) {
            when (scanner.next()) {
                Dragon.TYPE -> add(Dragon(scanner.nextInt(), scanner.nextInt()))
                Mutant.TYPE -> add(Mutant(scanner.nextInt(), scanner.nextInt()))
                Zombie.TYPE -> add(Zombie(scanner.nextInt(), scanner.nextInt()))
            }
        }
    }

    override fun enter(character: Character) {
        println("Fight started")
        easyWay(character)

        for (enemy in enemies) {
            character.fight(enemy)
            character.enemyKilled += 1
            character.needShop(DurabilityShop(), enemy);
        }

        println("Our Hero killed all enemies")
    }

    private fun easyWay(character: Character) {
        for (i in 0 until character.weaknessTypeList.size)
            enemies.sortBy { it.type() == character.weaknessTypeList[i] }
    }

    fun filterWithWeakness(character: Character): List<IEnemy> {
        for (i in 0 until character.weaknessTypeList.size)
            enemies.filter {
                it.type() == character.weaknessTypeList[i]
            }

        return enemies
    }

    override fun add(enemy: IEnemy): Boolean = enemies.add(enemy)

    override fun delete(enemy: IEnemy): Boolean = enemies.remove(enemy)
}