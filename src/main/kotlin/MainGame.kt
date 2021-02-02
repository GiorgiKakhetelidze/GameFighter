import java.io.File
import java.io.FileNotFoundException
import java.util.*

fun main() {

    val character = Character(2000.0, 100, Dragon.TYPE, Mutant.TYPE)

    try {
        val impl = EnemyBase(Scanner(File("src/Enemies")))
        impl.enter(character)
    } catch (ex: FileNotFoundException) {
        println("Enemies file not found")
    } catch (ex: CharacterDeathException) {
        println(ex.message)
    }
}