fun main () {
    val input: IntArray = intArrayOf(0,0,3,4)
    val target = 0

    println(twoSum(input, target))
}

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var start = 0
    var end = numbers.size - 1

    while (true) {
        val result = numbers[start] + numbers[end]

        if (result == target) {
            return intArrayOf(start + 1, end + 1)
        }

        if (result > target) {
            end--
            while (numbers[end + 1] == numbers[end]) {
                end--
            }
        }

        if (result < target) {
            start++
            while (numbers[start - 1] == numbers[start]) {
                start++
            }
        }
    }
}