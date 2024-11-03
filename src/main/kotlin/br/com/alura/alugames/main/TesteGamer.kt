import br.com.alura.alugames.model.Gamer

fun main(){
    val gamer1 = Gamer(
        name = "Glad",
        email = "meuemail@hotmal.com")

    val gamer2 = Gamer(
        name = "Glad2",
        email = "meuemail2@outlook.com",
        dateBirth = "01/01/1900",
        username = "Gladshow")



    gamer1.let {
        it.dateBirth = "01/01/2000"
        it.username = "Gladson"
    }.also {
        println(gamer1.idInternal)
    }
    println(gamer2)
    println(gamer1)
    gamer1.username = "teste2"
    println(gamer1)
}

