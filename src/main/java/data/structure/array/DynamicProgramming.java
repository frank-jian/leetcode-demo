package data.structure.array;

/**
 * @description: <p>动态规划相关</p>
 * @author: terui
 * @date: 2020/9/20 10:27 下午
 */
public class DynamicProgramming {

    /**
     * #70爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ **注意：**给定 n 是一个正整数。
     * 输入： 2   输出： 2  解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     */
    public int climbStairs(int n) {
        int x = 0;
        int y = 0;
        int z = 1;
        for (int i = 0; i < n; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return z;
    }

    public static void main(String[] args) {
        int count = new DynamicProgramming().climbStairs(4);
        System.out.println(count);
    }
}
