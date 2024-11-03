package org.example.br.com.alura.alugames.main

import TransformAge
import br.com.alura.alugames.model.Gamer
import br.com.alura.alugames.service.ConsumoApi
import org.example.br.com.alura.alugames.model.Game
import org.example.br.com.alura.alugames.model.InfoGame
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val read = Scanner(System.`in`)
    val gamer = Gamer.createGamer(read)
    println("Registration successfully completed")
    println(gamer)
    println("Game age: " + gamer.dateBirth?.TransformAge())

    do{
        println("Enter a game code to search")
        val search = read.nextLine()
        var myInfoGame: InfoGame? = null

        val searchApi = ConsumoApi()
        val resultApi = runCatching {
            myInfoGame = searchApi.serchGame(search)
        }
        resultApi.onFailure {
            println("non-existent game, try another id")
        }

        resultApi.onSuccess {
            var myGame: Game? = null


            val result = runCatching {
                myGame = Game(
                    myInfoGame?.info?.title ?: "Untitled",
                    myInfoGame?.info?.thumb ?: "Without cover",
                    myInfoGame?.info?.description ?: "No description"
                )
            }



            result.onSuccess {
                println(myGame?.title)
                val read = Scanner(System.`in`)
                println("Want to enter a custom description? Y/N")
                val option = read.nextLine()
                if (option.equals("Y", true)) {
                    println("Enter a custom description: ")
                    val newDescription = read.nextLine()
                    myGame?.description = newDescription
                } else {
                    myGame?.description = myGame?.title
                }
                gamer.searchedGames.add(myGame)
            }
        }

        println("Want to look for a new game? Y/N")
        val resposta = read.nextLine()

    }while (resposta.equals("Y",true))

    println("Searched games:")

    /*val gamesFiltered = gamer.searchedGames.filter {
        it?.title?.contains("batman",true) ?: false
    }*/

    gamer.searchedGames.sortBy { it?.title }

    gamer.searchedGames.forEach{
        println("Title " + it?.title)
    }

    println("Want to delete any game from the original list? Y/N")
    val option = read.nextLine()

    if (option.equals("Y",true)){
        println("\n Enter the position of the game you want to delete: ")
        val position = read.nextInt()
        gamer.searchedGames.removeAt(position)
    }
    println("Updated list")
    println(gamer.searchedGames)



    println("Search successfully completed")
}