class Mutant(private val attackStrength: Int, override var heatPoint: Int) : IEnemy {
    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward = Reward.MUTANT_ARMOR

    companion object {
        const val TYPE = "MUTANT"
    }
}