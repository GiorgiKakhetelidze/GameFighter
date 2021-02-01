class Zombie(private val attackStrength: Int, override var heatPoint: Int) : IEnemy {

    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward = Reward.ZOMBIE

    companion object {
        const val TYPE = "ZOMBIE"
    }
}
