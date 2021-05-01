import java.util.LinkedList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        Test2 solution = new Test2();
        char[] inorder = solution.toCharArray("IADJNHBEKOFLGCM");
        char[] preorder = solution.toCharArray("HNAIJDOBKECLFGM");

        TreeNode tree = solution.buildTree(preorder, inorder);
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

    public TreeNode buildTree(char[] preorder, char[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode build(char[] preorder, int preStart, int preEnd, char[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // current root is the start node of preorder array
        char rootVal = preorder[preStart];

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
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);

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