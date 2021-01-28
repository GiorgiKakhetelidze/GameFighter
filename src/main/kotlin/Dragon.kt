class Dragon(private val attackStrength: Int, private val heatPoint: Int) : IEnemy {
    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward {
        return Reward.DRAGON_BAFF
    }

    override fun heatPoint(): Int {
        return heatPoint
    }

    companion object {
        const val TYPE = "DRAGON"
    }
}