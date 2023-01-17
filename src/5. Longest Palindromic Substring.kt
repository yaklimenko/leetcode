package longest_palindrome


fun main() {
    val input =
        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"

    println(PalindromeFinder(input).findPalindrome())
}

class Solution {
    fun longestPalindrome(s: String): String {
        return PalindromeFinder(s).findPalindrome()
    }
}

class PalindromeFinder(input: String) {

    private val positionsByChar: Map<Char, MutableList<Int>>
    private val inArray = input.toCharArray()

    private var palindrome: Palindrome = Palindrome(0, 0, inArray[0].toString())

    init {
        positionsByChar = HashMap(input.length)
        if (inArray.size > 2) {
            for (idx in inArray.indices) {
                positionsByChar[inArray[idx]]?.add(idx) ?: MutableList(1) { idx }.also {
                    positionsByChar[inArray[idx]] = it
                }
            }
        } else if (inArray.size == 2 && inArray[0] == inArray[1]) {
            palindrome = Palindrome(0, 1, input)
        }
    }

    fun findPalindrome(): String {
        if (positionsByChar.isEmpty()) {
            //println(palindrome)
            return palindrome.itself
        }
        for (idx in 0..inArray.size - 2) {
            positionsByChar[inArray[idx]]?.let {
                if (it.size > 1) {
                    val start = idx
                    it.reversed().forEach { end ->
                        if (end - start > 0 && !areIndicesAlreadyUsed(start, end)) {
                            tryFindPalindrome(start, end)
                        }
                    }
                }
            }
        }
        println("-===============-")
        return palindrome.itself
    }

    private fun areIndicesAlreadyUsed(start: Int, end: Int): Boolean {
        return start >= palindrome.start && end <= palindrome.end
    }

    private fun tryFindPalindrome(start: Int, end: Int) {
        var palStart = ""
        var st = start
        var en = end

        while (en - st > 0) {
            if (inArray[st] == inArray[en]) {
                palStart += inArray[st]
            } else {
                //nope
                return
            }
            st++
            en--
        }
        //rollback
        st--
        en++

        val middle = if (en - st == 2) inArray[st + 1] else ""
        val pal = palStart + middle + palStart.reversed()
        println(pal)
        if (pal.length > palindrome.itself.length) {
            //println(" RECORD")
            palindrome = Palindrome(start, end, pal)
            //palindrome = pal
        } else {
            //println()
        }
    }


    //make a map with key - char and value - list of positions
    //then start to find a palindrome using the map to find mirroring part


}

class Palindrome(
    val start: Int,
    val end: Int,
    val itself: String
)


