package leetcode;//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治算法 
// 👍 4061 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        int[] num1 = new int[]{1, 3};
        int[] num2 = new int[]{2};
        double aa = solution4.findMedianSortedArrays(num1, num2);
        System.out.printf(String.valueOf(aa));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1size = nums1.length;
        int nums2size = nums2.length;
        boolean odd = ((nums1size + nums2size) % 2 == 0);
        int middle = odd ? Math.round((nums1size + nums2size) / 2) : Math.round((nums1size + nums2size) / 2) + 1;


        int[] order = new int[nums1size + nums2size];
        int orderi = 0;
        int i = 0, j = 0;
        while ((i + j) != (middle + 1)) {
            if (nums1[i] >= nums2[j]) {
                order[orderi] = nums2[j];
                if (j == nums2size - 1) {
                    i++;
                } else {
                    j++;
                }
            } else {
                order[orderi] = nums1[i];
                if (i == nums1size - 1) {
                    j++;
                } else {
                    i++;
                }
            }
            orderi++;
        }
        if (odd) {
            return (Double.valueOf(order[middle]) + Double.valueOf(order[middle - 1])) / 2;
        } else {
            return order[i + j - 1];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
