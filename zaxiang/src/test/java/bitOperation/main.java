package bitOperation;

/**
 * PROJECT_name: github
 * package: com.bitOperation
 * creat_user: zhangkun19
 * creat_date: 2019/07/19
 * creat_time: 15:53
 * describe: TODO
 **/
public class main {
//    https://blog.csdn.net/mengzhengjie/article/details/80611422

    //正负数：：： 1、补码
//
//   在总结按位运算前，有必要先介绍下补码的知识，我们知道当将一个十进制正整数转换为二进制数的时候，
//   只需要通过除2取余的方法即可，但是怎么将一个十进制的负整数转换为二进制数呢？其实，负数是以补码的形式表示，
//   其转换方式，简单的一句话就是：先按正数转换，然后取反加1。
    public static void main(String[] args) {

        //整数10      0000 0000 0000 1010     找1  8+2=10
        //负数-10     1111 1111 1111 0110     找0  8+1+1=10（负）
        int a = 10, b = 20;
        a = a ^ b;
        System.out.println("a:" + a);
        b = b ^ a;
        a = a ^ b;

        System.out.println("a:" + a);
        System.out.println("b:" + b);

        //左移   右移
        System.out.println(3 << 2);
        System.out.println(8 >> 2);
        System.out.println(-3 << 2);
        System.out.println(-8 >> 2);
        //无符号右移
        System.out.println(-18 >>> 2);
        System.out.println(2 >>> 32);


//        求绝对值：
        long x = -10737418191L;
        System.out.println(x >> 31 == 0 ? x : (~x + 1));
    }
}
