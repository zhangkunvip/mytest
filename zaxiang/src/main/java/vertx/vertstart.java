package vertx;

import io.vertx.core.Vertx;

/**
 * PROJECT_name: github
 * package: vertx
 * creat_user: zhangkun19
 * creat_date: 2019/08/23
 * creat_time: 9:21
 * describe: TODO
 **/
public class vertstart {
//    public static void main(String[] args) {
//        Vertx vertx = Vertx.vertx();
////        vertx.deployVerticle(new MyVerticle());
//        vertx.deployVerticle("vertx.MyVerticle");
//    }
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RestfulVerticle());
    }


}
