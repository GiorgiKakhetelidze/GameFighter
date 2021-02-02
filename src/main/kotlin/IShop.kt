interface IShop {
    fun showMenu(character: Character)
    fun request(): Int
    fun buyItem(num: Int, character: Character)
    fun getMinimumPrice() : Int
}