class Character(var heatPoint: Double, private val weaknessEnemyType: String, private val attackStrength: Int) {

    var resistance = 1.0
    var gold = 0
    val startingHeatPoint = heatPoint
    var enemyKilled = 0

    fun fight(enemy: IEnemy) {
        heatPoint = if (enemy.type() == weaknessEnemyType)
            heatPoint - enemy.attackStrength() * 2 / resistance
        else
            heatPoint - enemy.attackStrength() / resistance

        finishFight(enemy)
    }

    private fun finishFight(enemy: IEnemy){
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

    fun showStats() {
        println("Gold: $gold \n Armor: $resistance \n HeatPoint: $heatPoint Enemies killed: $enemyKilled")
    }
}