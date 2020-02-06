package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PROJECT_name: mytest
 * package: log
 * creat_user: zhangkun19
 * creat_date: 2020/02/05
 * creat_time: 11:23
 * describe: TODO
 **/
public class 子类 extends 抽象父类{

    public static void main(String[] args) {
        子类 子 = new 子类();
        子.getLog().error("1", 子);
    }
}
