package 算法;

import java.util.HashMap;

/**
 * PROJECT_name: mytest
 * package: 算法
 * creat_user: zhangkun19
 * creat_date: 2020/03/01
 * creat_time: 13:23
 * describe: TODO
 * http://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652082022&idx=2&sn=d785c0d1664b428e2548b42b69862562&chksm=f1748483c6030d9537f63198f50600de0cb553c8a0961a63bf6fd16c624ad99367621f4b3de8&scene=0&xtrack=1#rd
 **/

//一只兔子旁边有100根萝卜， 兔子想把它们搬回家， 离家50米， 一次最多能搬运50根， 每走一米吃一根， 问：兔子最多能搬运多少根萝卜回家。
public class 硬币动态规划 {
    public static  void main(String[] args)  throws Throwable {
        int amount = 11;
        int[] coins = {1,2,5};
        int result = exchange(amount, coins);
        System.out.println("result = " + result);
    }
    //递归
    private static int exchange(int amount, int[] coins) {

        // 说明零钱刚好凑完
        if (amount == 0) {
            return 0;
        }

        // 说明没有满足的条件
        if (amount < 0) {
            return -1;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subMin = exchange(amount - coins[i], coins);
            if (subMin == -1) continue;
            result = Math.min(subMin + 1, result);
        }

        // 说明没有符合问题的解
        if (result == Integer.MAX_VALUE) {
            return -1;
        }

        return result;
    }

    //递归+备忘录
    // 保存中间结果
    private static HashMap<Integer, Integer> map = new HashMap();

    // 带备忘录的递归求解
    private static int exchangeRecursive(int amount, int[] coins) {
        if (map.get(amount) != null) {
            return map.get(amount);
        }
        // 说明零钱已经凑完
        if (amount == 0) {
            return 0;
        }

        // 说明没有满足的条件
        if (amount < 0) {
            return -1;
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subMin = exchangeRecursive(amount - coins[i], coins);
            if (subMin == -1) continue;
            result = Math.min(subMin + 1, result);
        }

        // 说明没有符合问题的解
        if (result == Integer.MAX_VALUE) {
            return -1;
        }

        map.put(amount, result);
        return result;
    }


    //自底向上
    // 动态规划求解
    private static int exchangeDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化每个值为 amount+1，这样当最终求得的 dp[amount] 为 amount+1 时，说明问题无解
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = amount + 1;
        }

        // 0 硬币本来就没有，所以设置成 0
        dp[0] = 0;

        for (int i = 0; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i- coins[j]], dp[i]) + 1;
                }
            }
        }

        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }
}
