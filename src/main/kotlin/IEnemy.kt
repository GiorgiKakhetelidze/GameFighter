interface IEnemy {
    fun type(): String
    fun attackStrength(): Int
    fun reward() : Reward
    fun heatPoint() : Int
}