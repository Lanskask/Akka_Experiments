case class TreeNode[X](value: X, left: Option[TreeNode[X]], right: Option[TreeNode[X]])

def isSameTree[X](leftNode: Option[TreeNode[X]], rightNode: Option[TreeNode[X]]): Boolean = leftNode match {
  case Some(leftNode) => rightNode match {
    case Some(rightNode) => leftNode.value == rightNode.value
    case None => false
  }
  case None => rightNode match {
    case Some(rightNode) => false
    case None => true
  }
}
val simpleP = TreeNode(1, None, None)
val simpleQ = TreeNode(1, None, None)
println(1)
assert(isSameTree(Some(simpleP), Some(simpleQ)))
val p = TreeNode(1, Some(TreeNode(2, None, None)), None)
val q = TreeNode(1, None, Some(TreeNode(2, None, None)))
println(2)
assert(!isSameTree(Some(p), Some(q)))

//def isSameTree[X](leftNode: TreeNode[X], rightNode: TreeNode[X]): Boolean = leftNode match {
//  case leftNode if leftNode.value == rightNode.value => true
//  case None if rightNode == None => true
//
//}
