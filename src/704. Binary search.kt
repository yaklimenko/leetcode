package leetcode

fun main(args: Array<String>) {
    val zu = intArrayOf(2)
    println(search(zu, 2))
}

fun search(nums: IntArray, target: Int): Int {
    var start = 0
    var end = nums.size - 1

    while (true) {
        val cursor = start + ((end - start) / 2)
        val current = nums[cursor]
        if (current == target) {
            return cursor
        }

        if (start == end) {
            return -1
        }

        if (current < target) {
            start = cursor + 1
        }

        if (current > target) {
            end = cursor - 1
        }

        if (end < start) {
            return -1
        }
    }
    // 1 2 3 5 6 7 8 9
}
