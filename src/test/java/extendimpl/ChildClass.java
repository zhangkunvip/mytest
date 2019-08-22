package extendimpl;

/**
 * PROJECT_name: github
 * package: extendimpl
 * creat_user: zhangkun19
 * creat_date: 2019/08/22
 * creat_time: 14:46
 * describe: TODO
 **/
public class ChildClass extends ParentClass implements Inter {

    public static void main(String[] args) {
        Inter c = new ChildClass();
        c.methodA();
    }
}
