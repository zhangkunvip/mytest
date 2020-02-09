package 算法;

/**
 * PROJECT_name: mytest
 * package: 算法
 * creat_user: zhangkun19
 * creat_date: 2020/02/08
 * creat_time: 17:37
 * describe: TODO
 **/
public class 取余数再取与 {
    public static int mask = 15;

    public static void main(String[] args) {
        String ori = "zhangkun";
        int hash = ori.hashCode();
        System.out.print(hash);
        System.out.print("  ");
        System.out.print(hash % mask & mask );
        System.out.print("  ");
        System.out.print(hash % mask);
        System.out.print("  ");
        System.out.print(hash & mask);
        System.out.println();
        ori = "zhangkun1";
        hash = ori.hashCode();
        System.out.print(hash);
        System.out.print("  ");
        System.out.print(hash % mask & mask);
        System.out.print("  ");
        System.out.print(hash % mask);
        System.out.print("  ");
        System.out.print(hash & mask);
        System.out.println();
        ori = "zhangkun2";
        hash = ori.hashCode();
        System.out.print(hash);
        System.out.print("  ");
        System.out.print(hash % mask & mask);
        System.out.print("  ");
        System.out.print(hash % mask);
        System.out.print("  ");
        System.out.print(hash & mask);
        System.out.println();
        ori = "zhangkun3";
        hash = ori.hashCode();
        System.out.print(hash);
        System.out.print("  ");
        System.out.print(hash % mask & mask);
        System.out.print("  ");
        System.out.print(hash % mask);
        System.out.print("  ");
        System.out.print(hash & mask);
        System.out.println();
        ori = "zhangkun4";
        hash = ori.hashCode();
        System.out.print(hash);
        System.out.print("  ");
        System.out.print(hash % mask & mask);
        System.out.print("  ");
        System.out.print(hash % mask);
        System.out.print("  ");
        System.out.print(hash & mask);
        System.out.println();
    }
}
