/**
 * https://leetcode.com/problems/two-sum/
 */

fun main() {
    val sol = Solution1()

    val arr = intArrayOf(3, 3)
    val res = sol.twoSum(arr, 6)
    for(num in res) {
        print("$num, ")
    }
    println()
}

class Solution1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        if (nums.size == 2) {
            return intArrayOf(0, 1)
        }
        val candidates: HashMap<Int, Int> = hashMapOf()
        for(i in nums.indices) {
            if (candidates.containsKey(target - nums[i])) {
                return intArrayOf(candidates[target - nums[i]]!!, i)
            }
            candidates[nums[i]] = i
        }

        throw java.lang.IllegalStateException("must not be here")
    }
}
