package 算法;


import java.util.HashMap;
import java.util.Map;

/**
 * PROJECT_name: mytest
 * package: 算法
 * creat_user: zhangkun19
 * creat_date: 2020/03/02
 * creat_time: 14:55
 * describe: TODO
 **/
//一只兔子旁边有100根萝卜， 兔子想把它们搬回家， 离家50米，
// 一次最多能搬运50根， 每走一米吃一根，
// 问：兔子最多能搬运多少根萝卜回家。
public class 兔子搬萝卜 {

    static int max2Get = 50;
    static int carrots = 100;
    static int distances = 50;

    public static void main(String[] args) {
        int temp = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int step = 1; step < (distances / 2); step++) {
            int result = once2Go(0, distances, step, carrots - 3 * step);
            map.put(step, result);
            int ret = Math.max(temp, result);
            if (ret != temp) {
//                map.put(step, ret);
                temp = ret;
            }

        }
        System.out.println(map.toString());
    }

    static int once2Go(int from, int to, int step, int carrotStock) {
        if (carrotStock < distances - (from + step)) {
            return -1;
        }
        if (carrotStock <= max2Get) {
            return carrotStock - (distances - (from + step));
        }
        return once2Go(from + step, to, step, carrotStock - 3 * step);
    }
//    public static void main(String[] args) {
//        int carrots = 100;
//        int distances = 50;
//        int carrtotStock[][] = new int[distances][2];
//        for (int i = 0; i <= distances; i++) {
//            carrtotStock[i][0] = 0;
//            carrtotStock[i][1] = 0;
//        }
//        carrtotStock[0][1] = carrots;
//        once2Go(carrtotStock);
//    }
//
//    static int once2Go(int[][] carrotStock) {
//        if (carrotStock[carrotStock.length][1] < 0) {
//            return -1;
//        }
//        int temp;
//        for(){
//
//        }
//    }
}
