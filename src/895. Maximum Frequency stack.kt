import java.util.EmptyStackException
import java.util.Stack
import java.util.TreeMap

class FreqStack() {

    private val valueListsSortedByCount: TreeMap<Int, MutableList<Int>> = TreeMap()
    private val countsByValue: HashMap<Int, Int> = hashMapOf()
    private val values: Stack<Int> = Stack()

    fun push(`val`: Int) {
        values.push(`val`)
        val currentCounter = countsByValue[`val`]
        if (currentCounter == null) {
            valueListsSortedByCount.getOrCreateForCounter(1).add(`val`)
            countsByValue[`val`] = 1
        } else {
            valueListsSortedByCount[currentCounter]!!.remove(`val`)
            if (valueListsSortedByCount[currentCounter]?.isEmpty() == true) {
                valueListsSortedByCount.remove(currentCounter)
            }
            valueListsSortedByCount.getOrCreateForCounter(currentCounter + 1).add(`val`)
            countsByValue[`val`] = currentCounter + 1
        }

        //logMaps()
    }

    private fun logMaps() {
        val iterator = valueListsSortedByCount.iterator()
        println("valueListsSortedByCount contents: ")
        while (iterator.hasNext()) {
            val entry = iterator.next()
            print("${entry.key} : ")
            for (lItem in entry.value) {
                print("$lItem, ")
            }
            println(".")
        }

        val iter = countsByValue.iterator()
        println("countsByValue contents:")
        while (iter.hasNext()) {
            val entry = iter.next()
            println("\t${entry.key} : ${entry.value}}")
        }

    }

    private fun TreeMap<Int, MutableList<Int>>.getOrCreateForCounter(counter: Int): MutableList<Int> {
        if (!this.containsKey(counter)) {
            this[counter] = mutableListOf()
        }
        return this[counter]!!
    }

    fun pop(): Int {
        if(values.isEmpty()) {
            throw EmptyStackException()
        }
        val valuesToLook = valueListsSortedByCount.lastEntry().value
        var pos = values.size
        while (true) {
            pos--
            if (pos < 0) {
                break
            }
            val current = values[pos]
            if (valuesToLook.contains(current)) {
                values.removeAt(pos)
                decreaseValueCount(current)
                //logMaps()
                return current
            }
        }
        //logMaps()
        throw java.lang.IllegalStateException("value to pop was not found")
    }

    private fun decreaseValueCount(valToDecrease: Int) {
        val counter = countsByValue[valToDecrease]
            ?: throw java.lang.IllegalStateException("canno find counter by value $valToDecrease")
        if (counter == 1) {
            countsByValue.remove(valToDecrease)
        } else {
            countsByValue[valToDecrease] = counter - 1
        }

        valueListsSortedByCount[counter]?.remove(valToDecrease)
        if (valueListsSortedByCount[counter]?.isEmpty() == true) {
            valueListsSortedByCount.remove(counter)
        }
        if (counter > 1) {
            valueListsSortedByCount.getOrCreateForCounter(counter - 1).add(valToDecrease)
        }
    }

}

fun main() {
    val myStack = FreqStack()
    myStack.pop()
//    for (i in 0 .. 100) {
//        println("push 5")
//        myStack.push(5)
//        println("push 7")
//        myStack.push(7)
//        println("push 5")
//        myStack.push(5)
//        println("push 7")
//        myStack.push(7)
//        println("push 4")
//        myStack.push(4)
//        println("push 5")
//        myStack.push(5)
//
//
//        println("pop: ${myStack.pop()}")
//        println("pop: ${myStack.pop()}")
//        println("pop: ${myStack.pop()}")
//        println("pop: ${myStack.pop()}")
//        println("pop: ${myStack.pop()}")
//        println("pop: ${myStack.pop()}")
//    }

}

/**
 * Your FreqStack object will be instantiated and called as such:
 * var obj = FreqStack()
 * obj.push(`val`)
 * var param_2 = obj.pop()
 */