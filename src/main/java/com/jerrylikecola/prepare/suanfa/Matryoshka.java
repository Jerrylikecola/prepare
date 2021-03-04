package com.jerrylikecola.prepare.suanfa;

/**
 * @author xiaxiang
 * @date 2021/3/4 15:34
 * @description
 */

import java.util.Arrays;

/**
 * 俄罗斯套娃问题
 *
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class Matryoshka {
    public static void main(String[] args) {
        int[][] envelopes = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};
        System.out.println(maxEnvelopes(envelopes));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        // 判断边界，如果数组中是空的或者只有一个元素，直接返回个数
        int length = envelopes.length;
        if (length < 2) {
            return length;
        }
        // 将数组先按一个纬度排序，相等情况下的反转原因是因为要构造一个最长升序序列
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int[] sort = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            sort[i] = envelopes[i][1];
        }
        return toLong(sort);
    }

    /**
     * 最长递增子序列,思路见 LengthOfLIS.class
     *
     * @param nums
     * @return
     */
    public static int toLong(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        int len = 1;
        dp[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (nums[i] > dp[mid]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
