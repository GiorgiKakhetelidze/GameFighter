class Mutant(private val attackStrength: Int, private val heatPoint: Int) : IEnemy {
    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward = Reward.MUTANT_ARMOR

    override fun heatPoint(): Int = heatPoint

    companion object {
        const val TYPE = "MUTANT"
    }
}