package 算法;

/**
 * PROJECT_name: mytest
 * package: 算法
 * creat_user: zhangkun19
 * creat_date: 2019/08/29
 * creat_time: 10:04
 * describe: TODO
 **/
public class 动态规划 {
//    举例：
//    输入:
//    arr = [
//            [1,3,1],
//            [1,5,1],
//            [4,2,1]
//            ]
//    输出: 7
//    解释: 因为路径 1→3→1→1→1 的总和最小。

    public static void main(String[] args) {

//        int[][] aaa = [            [1, 3, 1],            [1, 5, 1],            [4, 2, 1]            ];
        int[][] dp = new int[4][3];
        dp[0] = new int[]{1, 3, 1};
        dp[1] = new int[]{1, 5, 5};
        dp[2] = new int[]{2, 2, 1};
        dp[3] = new int[]{2, 2, 2};
//        System.out.println(dp.length);
//        System.out.println(dp[0].length);

        int ret = doDp(dp, 4, 3);
        System.out.println(ret);

    }

    private static int doDp(int[][] dp, int xx, int yy) {
//        int m = dp.length;
//        int n = dp[0].length;
        if (xx == 0 || yy == 0) {
            System.out.println("error~0");
            return 0;
        }
        if (xx == 1) {
            int ret = 0;
            for (int i = 0; i < yy; i++) {
                ret += dp[0][i];
            }
            System.out.println("m=1:" + ret);
            return ret;
        }
        if (yy == 1) {
            int ret = 0;
            for (int i = 0; i < xx; i++) {
                ret += dp[i][0];
            }
            System.out.println("n=1:" + ret);
            return ret;
        }

        return Math.min(doDp(dp, xx - 1, yy) + dp[xx - 1][yy - 1], doDp(dp, xx, yy - 1) + dp[xx - 1][yy - 1]);
    }

}
