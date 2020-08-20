package data.structure.array;

import java.util.Arrays;

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
