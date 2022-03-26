/**
 * https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
 */

fun main() {
    val sol = Solution1347()
    val s = "anagram"
    val t = "zzzzzzz"
    println(sol.minSteps(s, t))
}

class Solution1347 {

    fun minSteps(s: String, t: String): Int {

        val sMap: HashMap<Char, Int> = hashMapOf()
        s.forEach { sMap[it] = sMap.getOrDefault(it, 0) + 1 }

        t.forEach { ch -> sMap[ch]?.let { count ->
                if (count == 1) {
                    sMap.remove(ch)
                } else {
                    sMap[ch] = count - 1
                }
            }
        }

        val iterator = sMap.iterator()
        var res = 0
        while(iterator.hasNext()) {
            val entry = iterator.next()
            res += entry.value
        }
        return res
    }
}


