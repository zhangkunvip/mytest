package 算法;

/**
 * PROJECT_name: mytest
 * package: 算法
 * creat_user: zhangkun19
 * creat_date: 2019/08/29
 * creat_time: 10:04
 * describe: TODO
 **/
public class 递归N阶乘 {
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
        int x = 5;
        int ret = doRecursive(x);
        System.out.println(ret);
    }

    private static int doRecursive(int x) {
        if (x == 1) {
            return 1;
        }
        return x * doRecursive(x - 1);
    }


}
