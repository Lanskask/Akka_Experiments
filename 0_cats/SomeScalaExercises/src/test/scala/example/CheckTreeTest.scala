package example

import org.scalatest.funsuite.AnyFunSuite

class CheckTreeTest extends AnyFunSuite {
  val testData = Seq(
    (
      (TreeNode(1, None, None), TreeNode(1, None, None)), true
    ),
    (
      (TreeNode(1, None, None), TreeNode(2, None, None)), false
    ),
    (
      (
        TreeNode(1, Some(TreeNode(2, None, None)), None),
        TreeNode(1, None, Some(TreeNode(2, None, None)))
      ),
      false
    ),
    (
      (
        TreeNode(1, Some(TreeNode(2, None, None)), None),
        TreeNode(1, Some(TreeNode(1, Some(TreeNode(2, None, None)), None)), Some(TreeNode(2, None, None)))
      ),
      false
    ),
    (
      (
        TreeNode(1, Some(TreeNode(1, Some(TreeNode(2, None, None)), None)), Some(TreeNode(2, None, None))),
        TreeNode(1, Some(TreeNode(2, None, None)), None)
      ),
      false
    )
  )

  test("Test isSameTree") {
    for {
      (input, answer) <- testData
    } yield assert(CheckTree.isSameTree(Some(input._1), Some(input._2)) == answer)
  }

  test("isSameTree2") {
    for {
      (input, answer) <- testData
    } yield assert(CheckTree.isSameTree2(Some(input._1), Some(input._2)) == answer)
  }
}
