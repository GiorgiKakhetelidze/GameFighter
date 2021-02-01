class Dragon(private var attackStrength: Int, override var heatPoint: Int) : IEnemy, IFlyingEnemy {
    override fun type(): String = TYPE

    override fun attackStrength(): Int = attackStrength

    override fun reward(): Reward = Reward.DRAGON_BAFF

    companion object {
        const val TYPE = "DRAGON"
    }

    override fun flyAttackBonus() {
        attackStrength += 5
    }
}