/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 *
 * }
 */

fun main() {
    test3()
}

private fun test2() {
//    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//    Output: [8,9,9,9,0,0,0,1]
    val l1First =
        ListNode(9)
            .also {it -> it.next = ListNode(9)
                .also {it -> it.next = ListNode(9)
                    .also {it -> it.next = ListNode(9)
                        .also {it -> it.next = ListNode(9)
                            .also {it -> it.next = ListNode(9)
                                .also {it -> it.next = ListNode(9)
                                }}}}
                                }
            }

    val l2First = ListNode(9)
        .also { it -> it.next = ListNode(9)
            .also { it -> it.next = ListNode(9)
                .also { it -> it.next = ListNode(9)
                }}}

    val zu = Solution().addTwoNumbers(l1First, l2First)

    val ku = "dlfjksldkfj"

}

private fun test1() {
    //Input: l1 = [2,4,3], l2 = [5,6,4]
    val l1First =
        ListNode(2).also { node -> node.next = ListNode(4).also { nodeInner -> nodeInner.next = ListNode(3) } }
    val l2First =
        ListNode(5).also { node -> node.next = ListNode(6).also { nodeInner -> nodeInner.next = ListNode(4) } }

    val zu = Solution().addTwoNumbers(l1First, l2First)

    val ku = "dlfjksldkfj"
}

private fun test3() {
    //Input: l1 = [8,3,2], l2 = [9,2,1]
    val l1First =
        ListNode(8).also { node -> node.next = ListNode(3).also { nodeInner -> nodeInner.next = ListNode(2) } }
    val l2First =
        ListNode(9).also { node -> node.next = ListNode(2).also { nodeInner -> nodeInner.next = ListNode(1) } }

    val zu = Solution().addTwoNumbers(l1First, l2First)

    val ku = "dlfjksldkfj"
}

class ListNode(var `val`: Int) {
   var next: ListNode? = null
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) {
            return null
        }

        if (l1 == null) {
            return l2
        }

        if (l2 == null) {
            return l1
        }

        var result: ListNode? = null
        var resultFirstNode: ListNode? = null
        var ll1 = l1
        var ll2 = l2

        var haveMore = true
        var haveNextRegisterOne = false

        while (haveMore) {
            var newVal: Int = (ll1?.`val` ?: 0) + (ll2?.`val` ?: 0)
            if (haveNextRegisterOne) newVal++

            if (newVal >= 10) {
                haveNextRegisterOne = true
                newVal -= 10
            } else {
                haveNextRegisterOne = false
            }

            val newNode = ListNode(newVal)
            if (resultFirstNode == null) {
                resultFirstNode = newNode
            } else {
                result?.next = newNode
            }
            result = newNode

            ll1 = ll1?.next
            ll2 = ll2?.next

            haveMore = ll1 != null || ll2 != null || haveNextRegisterOne
        }

        return resultFirstNode
    }
}