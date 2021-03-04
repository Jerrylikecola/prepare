package com.jerrylikecola.prepare.suanfa;

/**
 * @author xiaxiang
 * @date 2021/3/4 16:15
 * @description
 */

/**
 * 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *  
 * <p>
 * 进阶：
 * <p>
 * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 */
public class LengthOfLIS {

    /**
     * 时间复杂度为n^2的方法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n];
        // 遍历每个元素是否比之前的元素大，如果大就序列+1。并且继承与其比较的那个值的序列
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < n; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 1;
        for (int num:dp) {
            max=Math.max(num,max);
        }
        return max;
    }

    /**
     * 时间复杂度为nlogn的方法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int len = 1;
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[len] = nums[0];
        for (int i = 1; i < n; i++) {
            // 判断下一个元素是否比dp中的最后一个元素大
            if (nums[i] > dp[len]) {
                // 如果是则添加到dp的数组末端
                dp[++len] = nums[i];
            } else {
                // 如果不是则去寻找比该值大的第一个值并替换他，如果一个都找不到那就更新dp[1]
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (dp[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                // 找不到了，说明范围内的数都比num[i]大，那么比num大的第一个数就是原先游标的右边的值
                // 因为num[i]大于上一轮的mid，但却小于mid+1，所以mid+1是比num[i]大的第一个数
                dp[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
