import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Test solution = new Test();
        char[] inorder = solution.toCharArray("UCANDOITYEH");
        char[] postorder = solution.toCharArray("CUNATYIHEOD");

        TreeNode tree = solution.buildTree(inorder, postorder);
        List<List<Character>> list = solution.levelOrder(tree);
        System.out.println(list);

    }

    public class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(char val) {
            this.val = val;
        }

        TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public char[] toCharArray(String str) {

        // Creating array of string length
        char[] ch = new char[str.length()];

        // Copy character by character into array
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }

        return ch;
    }

    public TreeNode buildTree(char[] inorder, char[] postorder) {
        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode build(char[] postorder, int postStart, int postEnd, char[] inorder, int inStart, int inEnd) {

        if (postStart > postEnd) {
            return null;
        }

        // current root is the start node of postorder array
        char rootVal = postorder[postEnd];

        // find the index of current root in inorder array
        // can use hashmap to cache the location of the nodes
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);

        // recursively build the left subtree and right subtree
        root.left = build(postorder, postStart, postStart + leftSize - 1, inorder, inStart, index - 1);
        root.right = build(postorder, postStart + leftSize, postEnd - 1, inorder, index + 1, inEnd);

        return root;
    }

    public List<List<Character>> levelOrder(TreeNode root) {
        List<List<Character>> result = new LinkedList<>();
        helper(result, root, 0);
        return result;

    }

    private void helper(List<List<Character>> result, TreeNode node, int level) {
        // preorder traverse
        if (level == result.size())
            result.add(new LinkedList<>());
        if (node == null) {
            result.get(level).add('x');
            return;
        }

        result.get(level).add(node.val);
        helper(result, node.left, level + 1);
        helper(result, node.right, level + 1);
    }
}