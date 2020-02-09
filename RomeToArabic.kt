package lab1
import kotlin.collections.indexOf


interface Result {
    open fun getResult() : String
}

class RomeToArabic(roman: String) : Result {
    val roman: String = roman.toUpperCase()
    var arabic: Int = 0
    override fun getResult(): String {
        return toArabic().toString()
    }
    fun toArabic() : Any {

        var values = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
        var digits = values.keys
        for (q in 0 until roman.length) {
            if (roman[q] !in digits)
                return "INPUT ERROR NOT A ROMAN SYMBOL"
        }
        for (q in 0 until roman.length) {
            if (digits.indexOf(roman[q]) < digits.indexOf(roman[q]+1)) {
                arabic -= values.getValue(roman[q])// values[roman[q]]!!
            } else {
                arabic += values.getValue(roman[q])
            }
        }
        return arabic
    }
}

