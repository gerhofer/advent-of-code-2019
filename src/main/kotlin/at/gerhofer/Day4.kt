package at.gerhofer

object Day4 {

    fun getCountOfValidNumbersInRange(fromValue: Int, toValue: Int) : Int {
        return (fromValue..toValue)
                .filter { isValid(it) }
                .count()
    }

    fun isValid(number: Int) : Boolean {
        var num = number
        var previousDigit = -1
        var double = false
        while (num != 0) {
            val currentDigit = num % 10
            if (currentDigit > previousDigit && previousDigit != -1) {
                return false
            }
            if (currentDigit == previousDigit) {
                double = true
            }
            previousDigit = currentDigit
            num /= 10
        }
        return double
    }

    fun isValid2(number: Int): Boolean {
        var num = number
        var previousDigit = -1
        var double = false

        while (num != 0) {
            val currentDigit = num % 10
            if (currentDigit > previousDigit && previousDigit != -1) {
                return false
            }
            if (currentDigit == (num / 10 % 10) && currentDigit != (num / 100 % 10) && (previousDigit != currentDigit || previousDigit == -1)) {
                double = true
            }
            previousDigit = currentDigit
            num /= 10
        }
        return double
    }

    fun getValidNumbersInRange2(fromValue: Int, toValue: Int) : List<Int> {
        return (fromValue..toValue)
                .filter { isValid2(it) }
    }

    fun getCountOfValidNumbersInRange2(fromValue: Int, toValue: Int) : Int {
        return (fromValue..toValue)
                .filter { isValid2(it) }
                .count()
    }

}