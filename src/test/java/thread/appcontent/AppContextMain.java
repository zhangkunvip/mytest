package thread.appcontent;

/**
 * PROJECT_name: github
 * package: thread
 * creat_user: zhangkun19
 * creat_date: 2019/07/17
 * creat_time: 14:32
 * describe: TODO
 **/
public class AppContextMain {

    public static void main(String[] args) {
        String[] names = { "骆昊", "王大锤", "张三丰", "杨过", "李莫愁" };   // 5位哲学家的名字
//      ExecutorService es = Executors.newFixedThreadPool(AppContext.NUM_OF_PHILO);
// 创建固定大小的线程池
//      for(int i = 0, len = names.length; i < len; ++i) {
        //          es.execute(new Philosopher(i, names[i]));
// 启动线程

//      }

//      es.shutdown();
        for(int i = 0, len = names.length; i < len; ++i) {
            new Thread(new Philosopher(i, names[i])).start();
        }
    }

}
