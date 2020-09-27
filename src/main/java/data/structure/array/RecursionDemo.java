package data.structure.array;

import java.util.HashMap;
import java.util.Map;

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
            this.val = val;
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

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * @param n
     * @return
     */
    int constant = 1000000007;

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a = fib(n - 1) % constant;
        int b = fib(n - 2) % constant;
        return (a + b) % constant;
    }

    public int fib2(int n) {
        return fib(n, new HashMap<>());
    }

    /**
     * 非递归方法
     */
    public int fib3(int n) {
        int first = 0;
        int seconds = 1;
        while (n > 0) {
            int temp = (first + seconds) % constant;
            first = seconds % constant;
            seconds = temp % constant;
            n--;
        }
        return first;
    }


    private int fib(int n, Map<Integer, Integer> cache) {
        if (n < 2) {
            return n;
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int a = fib(n - 1, cache) % constant;
        cache.put(n - 1, a);
        int b = fib(n - 2, cache) % constant;
        cache.put(n - 2, b);

        int sum = (a + b) % constant;
        cache.put(n, sum);
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new RecursionDemo().fib(5));
    }
}
