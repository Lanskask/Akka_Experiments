package example

case class TreeNode[X](
                        value: X,
                        left: Option[TreeNode[X]],
                        right: Option[TreeNode[X]]
                      )

object CheckTree {
    def isSameTree2[X](
                        leftNode: Option[TreeNode[X]],
                        rightNode: Option[TreeNode[X]]
                      ): Boolean = leftNode match {
      case Some(leftNodeIn) =>
        rightNode match {
          case Some(rightNodeIn) =>
            leftNodeIn.value == rightNodeIn.value &&
              isSameTree2(leftNodeIn.left, rightNodeIn.left) &&
              isSameTree2(leftNodeIn.right, rightNodeIn.right)
          case None => false
        }
      case None =>
        rightNode match {
          case Some(_) => false
          case None => true
        }
    }


  def isSameTree[X](
                     leftNode: Option[TreeNode[X]],
                     rightNode: Option[TreeNode[X]]
                   ): Boolean = isSameTreeTailRec(leftNode, rightNode).result

  import scala.util.control.TailCalls._

  def isSameTreeTailRec[X](
                            leftNode: Option[TreeNode[X]],
                            rightNode: Option[TreeNode[X]]
                          ): TailRec[Boolean] = leftNode match {
    case None =>
      rightNode match {
        case Some(_) => done(false)
        case None => done(true)
      }
    case Some(TreeNode(leftItemValue, None, None)) =>
      rightNode match {
        case Some(TreeNode(rightItemValue, None, None)) => done(leftItemValue == rightItemValue)
        case None => done(false)
        case Some(_) => done(false)
      }
    case Some(leftNodeIn) =>
      rightNode match {
        case Some(rightNodeIn) =>
          tailcall {
            for {
              leftEqual <- isSameTreeTailRec(leftNodeIn.left, rightNodeIn.left)
              rightEqual <- isSameTreeTailRec(leftNodeIn.right, rightNodeIn.right)
            } yield leftEqual && rightEqual
          }
        case None => done(false)
      }
  }
}
