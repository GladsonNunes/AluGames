import java.time.LocalDate
import java.time.Period
import java.time.Period.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.TransformAge(): Int{
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var age = 0
    val dateBirth = LocalDate.parse(this, formatter)
    val today = LocalDate.now()
    age = Period.between(dateBirth,today).years

    return age
}

fun String.IsValidDate(): Boolean {
    return try {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        LocalDate.parse(this, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}
