package data.structure.array;

/**
 * @description: <p>递归Demo</p>
 * @author: terui
 * @date: 2020/9/21 9:51 下午
 */
public class RecursionDemo {
    int vas;

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = value;
        }
    }

    /**
     * #687 最长同值路径
     * 输入:
     * 5
     * / \
     * 4   5
     * / \   \
     * 1   1   5
     * 输出: 2
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        vas = 0;
        longestPath(root);
        return vas;
    }

    /**
     * 递归功能
     * <p>
     * 搜寻以node为起点的最长同值路径:要么是以node为起点的左子树，要么是以node为起点的右子树
     *
     * @param node
     * @return
     */
    private int longestPath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxLongestPath = 0;
        //node左子树的最长同值路径
        int left = longestPath(node.left);

        //node右子树的最长同值路径
        int right = longestPath(node.right);

        // 这种情况对于寻找最长同值路径长有帮助，对于搜索以root为路径起始点的最长路径没有帮助
        if ((node.left != null && node.val == node.left.val) && (node.right != null && node.val == node.right.val)) {
            vas = Math.max(left + right + 2, vas);
        }

        //从左右子树中选择最长的同值路径
        if (node.left != null && node.val == node.left.val) {
            maxLongestPath = left + 1;
        }

        // 右树和左树对比，取路径最大值
        if (node.right != null && node.val == node.right.val) {
            maxLongestPath = Math.max(maxLongestPath, right + 1);
        }

        //从ans与maxLongestPath中更新最大值
        vas = Math.max(maxLongestPath, vas);
        return maxLongestPath;
    }
}
