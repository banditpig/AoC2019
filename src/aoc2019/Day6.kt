package aoc2019

import util.TreeNode
import util.readFileLines
import kotlin.math.min

fun parseInput(input: List<String>): List<Pair<String, String>> {
    fun makePair(s: List<String>): Pair<String, String> {
        return Pair(s[0], s[1])
    }
    return input.map { it -> makePair(it.split(")")) }
}

fun <T> MutableSet<T>.insert(item: T): MutableSet<T> {
    this.add(item)
    return this
}


fun sumNodes(node:TreeNode<String>):Int{
    var stack = mutableListOf<TreeNode<String>>()
    var sum = 0;
    if (node == null) sum
    stack.add(node)
    while (stack.isNotEmpty()){
        var n = stack.removeAt(0)
        //Note common code here and in findNode :)
        sum += n.depth
        stack.addAll(n.children)
    }
    return sum
}
fun findNode(node:TreeNode<String>, target: String):TreeNode<String>{
    var stack = mutableListOf<TreeNode<String>>()

    stack.add(node)
    while (stack.isNotEmpty()){
        var n = stack.removeAt(0)

        if (n.value == target) return n
        stack.addAll(n.children)
    }
    return node
}


fun buildTree(node: TreeNode<String>, pairs: MutableList<Pair<String, String>>){

        fun findCandidatesToAdd(parentTarget: String, pairList: List<Pair<String, String>>): List<Pair<String, String>>{
            return pairList.filter { (p, c) -> p == parentTarget}
        }

    if (pairs.isEmpty()) return
    val parentTarget = node.value
    val toAdd = findCandidatesToAdd(parentTarget, pairs)
    pairs.removeAll(toAdd)
    toAdd.map { (p,c) -> c }.forEach { c -> node.addChild(TreeNode<String>(c)) }

    node.children.forEach { childNode -> buildTree(childNode, pairs) }

}
tailrec fun findStartPair(pairs:List<Pair<String, String>>):Pair<String, String>{
   val p = pairs[0]
    if (p.first == "COM") return p
    return findStartPair(pairs.drop(1))
}
fun makeRoot(pairs:MutableList<Pair<String, String>>):TreeNode<String>{
    val startPair = findStartPair(pairs)
    pairs.remove(startPair)

    var root = TreeNode<String>(startPair.first)
    root.parent = null
    root.addChild(TreeNode<String>(startPair.second))
    return root
}
fun lowestCommonAncestor(node1:TreeNode<String>, node2:TreeNode<String>):TreeNode<String>{
    val v1 = pathToNode(node1).reversed()
    val v2 = pathToNode(node2).reversed()
    val last = min(v1.size, v2.size) - 1
    var common = node1

    for (i in (0..last)) {
        if (v1[i] == v2[i]){
            common = v1[i]
        }else{
            break
        }
    }

    return common
}
fun pathToNode(node:TreeNode<String>):List<TreeNode<String>>{


    tailrec fun pathToNode1(node:TreeNode<String>, list:MutableList<TreeNode<String>>){
        list.add(node)
        if (node.parent == null) return
        pathToNode1(node.parent!!, list)
    }

    var list = mutableListOf<TreeNode<String>>()
    pathToNode1(node, list)
    return list
}
fun main() {

    val inp = readFileLines(("resources/Day6.txt"))
    val pairs = parseInput(inp).toMutableList()


    val root = makeRoot(pairs)
    buildTree(root, pairs)
    println("Part 1")
    println(sumNodes(root))


    val v1 = findNode( root, "YOU").parent
    val v2 = findNode( root, "SAN").parent

    if (v1 != null && v2 != null) {
        //distance to v1 plus distance to v2 minus twice length
        //of the LCA path
        val lca = lowestCommonAncestor(v1,v2)
        val lcaPathLen  = pathToNode(lca).size
        val v1Len = pathToNode(v1).size
        val v2Len = pathToNode(v2).size
        val res = v1Len + v2Len - 2 * lcaPathLen
        println("Part 2.")
        println(res)
    }


}






