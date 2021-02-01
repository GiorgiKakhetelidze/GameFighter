interface IEnemyBase {
    fun add(enemy: IEnemy): Boolean
    fun enter(character: Character)
    fun delete(enemy: IEnemy): Boolean
}