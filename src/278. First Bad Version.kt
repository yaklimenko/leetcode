fun main () {


    println(firstBadVersion(1))
    //println(firstBadVersion(2000))
}

fun firstBadVersion(n: Int) : Int {
    var lastGood = 1
    var firstBad = n

    if (isBadVersion(lastGood)) {
        return lastGood
    }

    if (!isBadVersion(firstBad)) {
        return -1
    }

    while (true) {

        if (firstBad - lastGood == 1) {
            return firstBad
        }

        val cursor = lastGood + ((firstBad - lastGood) / 2)

        if (isBadVersion(cursor)) {
            firstBad = cursor
        } else {
            lastGood = cursor
        }
    }
}

private fun isBadVersion(version: Int): Boolean {
    println("checking $version")
    return version >= 1

}

