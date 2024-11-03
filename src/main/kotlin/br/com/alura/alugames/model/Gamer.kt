package br.com.alura.alugames.model

import IsValidDate
import org.example.br.com.alura.alugames.model.Game
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var name:String,
                 var email:String){
    var dateBirth:String? = null
    var username:String? = null
        set(value) {
            field = value
            if (idInternal.isNullOrBlank()){
                createIdInternal()
            }
        }
    var idInternal:String? = null
        private set

    var searchedGames = mutableListOf<Game?>()

    constructor(name: String,
                email: String,
                dateBirth:String,
                username:String
             ):
            this(name,email){
                 this.dateBirth = dateBirth
                 this.username = username
                 createIdInternal()
             }

    init {
        if (name.isNullOrBlank()){
            throw IllegalArgumentException("Invalid name")
        }
        this.email = checkEmail()
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', dateBirth=$dateBirth, user=$username, idInternal=$idInternal)"
    }

    fun createIdInternal(){
        val number = Random.nextInt(10000)
        val tag = String.format("%04d",number)

        idInternal= "$username#$tag"
    }

    fun checkEmail():String{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)){
            return email
        }else{
            throw IllegalArgumentException("Invalid email")
        }
    }


    companion object {
        fun createGamer(read: Scanner): Gamer {
            println("Welcome to AluGames! Let's register. Enter your name:")
            val name = read.nextLine()
            println("Enter your email:")
            val email = read.nextLine()
            println("Want to complete your registration with username and date of birth? (Y/N)")
            val option = read.nextLine()

            if (option.equals("Y",true)) {
                var dateBirth: String
                do {
                    println("Enter your date of birth (DD/MM/YYYY):")
                      dateBirth = read.nextLine()

                    if (!dateBirth.IsValidDate()) {
                        println("Invalid date of birth! Please try again.")
                    }

                } while (!dateBirth.IsValidDate())
                println("Enter your username:")
                val username = read.nextLine()
                return Gamer(name,email,dateBirth,username)
            }
                return Gamer(name,email)
        }
    }
}
