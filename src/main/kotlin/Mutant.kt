class Mutant(private val attackStrength: Int, private val heatPoint: Int) : IEnemy {
    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward {
        return Reward.MUTANT_ARMOR
    }

    override fun heatPoint(): Int {
        return heatPoint
    }

    companion object {
        const val TYPE = "MUTANT"
    }
}