package data.structure.array;

import java.util.Arrays;
import java.util.Objects;

/**
 * @description: <p>数组系列算法</p>
 * @author: terui
 * @date: 2020/8/19 8:00 下午
 */
public class ArrayDemo {

    /**
     * #350
     * 给定两个数组(无序)，编写一个函数来计算它们的交集。
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * @param arr1 数组1
     * @param arr2 数组2
     */
    public int[] arrayMixed(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int minLen = Math.min(len1, len2);
        int resultIndex = 0;
        int[] result = new int[minLen];
        int[] targetArray = minLen == len1 ? arr1 : arr2;
        int[] compareArray = minLen == len1 ? arr2 : arr1;
        for (int index = 0; index < minLen; index++) {
            int value = targetArray[index];

            // 查到到相同的数值
            int equalIndex = getIndex(compareArray, value);

            if (equalIndex != -1) {
                // 移除对比数据元素
                compareArray = removeAndReturnArray(compareArray, equalIndex);

                // 交集元素
                result[resultIndex] = value;
                resultIndex++;
            }
        }
        return result;
    }

    /**
     * #350
     * 给定两个数组(有序)，编写一个函数来计算它们的交集。
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * @param arr1 数组1
     * @param arr2 数组2
     */
    public int[] arrayMixed2(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] > arr2[index2]) {
                index2++;
            } else if (arr1[index1] < arr2[index2]) {
                index1++;
            } else {
                arr1[index3] = arr1[index1];
                index1++;
                index2++;
                index3++;
            }
        }

        return Arrays.copyOfRange(arr1, 0, index3);
    }

    /**
     * #14 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，则返回""
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String samePrefixStr(String[] strs) {
        // 查找字符串中长度最新的字符串
        String minStr = getMinStr(strs);

        // 得到最小字符列表
        char[] chars = minStr.toCharArray();

        int index = 0;
        for (char c : chars) {
            boolean isSame = isSameChar(strs, chars[index], index);
            // 不同时，跳出
            if (!isSame) {
                break;
            }
            index++;
        }

        return index == 0 ? "" : minStr.substring(0, index);
    }

    /**
     * #14 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，则返回""
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String samePrefixStr2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String value = strs[0];
        for (int index = 1; index < strs.length; index++) {
            int j = 0;
            for (; j < value.length() && j < strs[index].length(); j++) {
                if (value.charAt(j) != strs[index].charAt(j)) {
                    break;
                }
            }
            value = value.substring(0, j);
            if (Objects.equals("", value)) {
                return "";
            }
        }
        return value;
    }

    /**
     * # 122 买卖股票的最佳时机(给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。)
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。注意你不能在买入股票前卖出股票。
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解题要点: 只能买卖1只股票，可以多次交易；
     *
     * @param arr1 数组
     * @return 收益
     */
    public int stockMaxProfit(int[] arr1) {
        if (arr1.length < 2) {
            return 0;
        }

        int i = 0;
        int profit = 0;
        while (i < arr1.length - 1) {
            if (arr1[i] < arr1[i+1]) {
                profit = profit + (arr1[i+1] - arr1[i]);
            }
            i++;
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(new ArrayDemo().stockMaxProfit(new int[]{7,1,5,3,6,4}));;
    }
    /**
     * 字符是否相同
     *
     * @param strs  字符串数组
     * @param c     字符
     * @param index 字符所在索引位
     * @return
     */
    private boolean isSameChar(String[] strs, char c, int index) {
        for (String str : strs) {
            if (str.charAt(index) != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取最小字符串
     *
     * @param strs 字符串数组
     * @return 最小字符串
     */
    private String getMinStr(String[] strs) {
        String minStr = strs.length > 0 ? strs[0] : "";
        for (int index = 1; index < strs.length; index++) {
            if (strs[index].length() < minStr.length()) {
                minStr = strs[index];
            }
        }
        return minStr;
    }

    private int getIndex(int[] arr, int targetValue) {
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == targetValue) {
                return index;
            }
        }
        return -1;
    }

    private int[] removeAndReturnArray(int[] arr, int targetIndex) {
        int[] result = new int[arr.length - 1];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != targetIndex) {
                result[k] = arr[i];
                k++;
            }
        }
        return result;
    }
}
