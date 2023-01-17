package max_subarray

fun main() {
    val input = intArrayOf(5, -4, 100, -50, 150, -1, 7, 8)
    println(Solution().maxSubArray(input))
}

class Solution {
    fun maxSubArray(nums: IntArray): Int {

        if (nums.size == 1) {
            return nums[0]
        }

        var curHigh = -10000
        var max = -10000

        for (cur in nums) {
            curHigh = kotlin.math.max(cur, cur + curHigh)

            if (curHigh > max) {
                max = curHigh
            }
        }


        return max
    }
}