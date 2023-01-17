package contains_duplicate

fun main() {
    val input = intArrayOf(1, -8765432, 0, -4, 5, 7, 7, 8, 9)
    println(Solution().containsDuplicate(input))
}

class Solution {
    fun containsDuplicate(nums: IntArray): Boolean {
        if (nums.size < 2) {
            return false
        }
        val numsSet = hashSetOf<Int>()
        val iterator = nums.iterator()
        while (iterator.hasNext()) {
            val cur = iterator.next()
            if (!numsSet.add(cur)) {
                return true
            }
        }
        return false
    }
}