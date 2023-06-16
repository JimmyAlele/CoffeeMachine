fun main() {
    val coffeeMachine = CoffeeMachine()

    while (true) {
        when (getAction()) {
            "buy" -> coffeeMachine.buy()
            "remaining" -> coffeeMachine.remaining()
            "fill" -> coffeeMachine.fill()
            "take" -> coffeeMachine.take()
            "exit" -> break
        }
    }
}

class CoffeeMachine (private var water: Int = 400,
                     private var milk: Int =540,
                     private var beans: Int = 120,
                     private var cups: Int = 9,
                     private var money: Int = 550) {

    fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        var choice = 0
        var selection = true
        when (readln()) {
            "1" -> choice = 0
            "2" -> choice = 1
            "3" -> choice = 2
            else -> selection = false
        }
        if (selection && enough(choice)) {
            water -= Coffees.values()[choice].water
            milk -= Coffees.values()[choice].milk
            beans -= Coffees.values()[choice].beans
            cups --
            money += Coffees.values()[choice].money
        }
    }

    fun take() {
        println("I gave you $money")
        money -= money
    }

    fun remaining() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
    }

    fun fill () {
        println("Write how many ml of water you want to add:")
        water += readln().toInt()
        println("Write how many ml of milk you want to add:")
        milk += readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        beans += readln().toInt()
        println("Write how many disposable cups you want to add:")
        cups += readln().toInt()
    }

    private fun enough (choice: Int): Boolean {
        when {
            water < Coffees.values()[choice].water -> {
                println("Sorry, not enough water!")
                return false
            }
            milk < Coffees.values()[choice].milk -> {
                println("Sorry, not enough milk!")
                return false
            }
            beans < Coffees.values()[choice].beans -> {
                println("Sorry, not enough coffee beans")
                return false
            }
            cups < 1 -> {
                println("Sorry, not enough cups")
                return false
            }
            else -> {
                println("I have enough resources, making you a coffee!")
                return true
            }
        }
    }
}

fun getAction (): String {
    println("Write action (buy, fill, take, remaining, exit):")
    return readln()
}
enum class Coffees(var water: Int, var milk: Int, var beans: Int, var money: Int) {
    ESPRESSO( 250, 0, 16, 4),
    LATTE( 350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}