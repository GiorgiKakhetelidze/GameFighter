import java.io.File
import java.io.FileNotFoundException
import java.lang.Character
import java.util.*

fun main() {

    val character = Character(500.0, Dragon.TYPE, 100)

    try {
        val impl = EnemyBaseImpl(Scanner(File("src/Enemies")))
        impl.enter(character)
    } catch (ex: FileNotFoundException) {
        println("Enemies file not found")
    } catch (ex: CharacterDeathException) {
        println(ex.message)
    }

    //character.showStats()
}