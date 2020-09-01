package data.structure.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            if (arr1[i] < arr1[i + 1]) {
                profit = profit + (arr1[i + 1] - arr1[i]);
            }
            i++;
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new ArrayDemo().threeSum(new int[]{-1, 0, 1, 2, -1, 4})));
    }

    /**
     * #189: 旋转数组(给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数)
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     *
     * @param arr  数组
     * @param step 向右移动位置
     * @return
     */
    public void rotateList(int[] arr, int step) {
        int move = step % arr.length;
        // 所有元素反转
        reverse(arr, 0, arr.length - 1);

        // 前k个元素反转
        reverse(arr, 0, move - 1);

        // len -k个元素反转
        reverse(arr, move, arr.length - 1);
    }

    /**
     * #27：移除元素(给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度)
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * @param arr 数组
     * @param val 移除元素的数值
     * @return 剩余数组的长度
     */
    public int removeElement(int[] arr, int val) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != val) {
                arr[j] = arr[i];
                j++;
            }
        }
        return j;
    }

    /**
     * #66 加1(给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一)
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 特别注意: 输入[9] 输出[10]
     *
     * @param digits 数组
     * @return
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * #1 两数之和(给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。)
     *
     * @param arr    数组
     * @param target 目标值
     * @return
     */
    public int[] twoSum(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * #15 三数之和(给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组)
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     *
     * @param arr
     * @return
     */
    public List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        if (arr == null || arr.length < 3) {
            return result;
        }
        // 排序
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // 若第1位大于0，按升序排序，三数之和不可能为0
            if (arr[i] > 0) {
                break;
            }

            // 两数相等跳过，避免出现相同的数字
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == 0) {
                    result.add(Arrays.asList(arr[i], arr[left], arr[right]));
                    left++;
                    right--;
                    // 去重
                    while (left < right && arr[left] == arr[left + 1]) left++;
                    // 去重-
                    while (left < right && arr[right] == arr[right - 1]) right--;
                } else if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 第一步: [1,2,3,4,5,6,7] 0、6
     * 1、len = 7;
     * 最后得到[7,6,5,4,3,2,1]
     * 第二步: 反转前k个元素, [7,6,5,4,3,2,1] 0、2
     * 得到[5,6,7,4,3,2,1]
     * 第三步: 反转len - k个元素 [5,6,7,4,3,2,1] 3 6
     * 得到:[7,6,5,1,2,3,4]
     *
     * @param arr   数组
     * @param start 起始位置
     * @param end   终止位置
     */
    private void reverse(int[] arr, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len / 2; i++) {
            int temp = arr[start + i];
            arr[start + i] = arr[end - i];
            arr[end - i] = temp;
        }
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
