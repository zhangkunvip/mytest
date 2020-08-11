package 力扣;

public class 分割等和子集 {
    public static boolean canPartition(int[] nums) {

        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;

        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            sum += nums[i];
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;

        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for (int i = 0; i < n; ++i) {
            for (int j = target; j >= nums[i]; --j) {
                dp[j] =dp[j] | dp[j - nums[i]];
//                dp[j] |= dp[j - nums[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        canPartition(new int[]{1, 5, 11, 5});
    }
}
