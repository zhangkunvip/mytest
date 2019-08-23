package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * PROJECT_name: github
 * package: vertx
 * creat_user: zhangkun19
 * creat_date: 2019/08/22
 * creat_time: 21:56
 * describe: TODO
 **/
public class MyVerticle extends AbstractVerticle {

    /**
     * Start the verticle.<p>
     * This is called by Vert.x when the verticle instance is deployed. Don't call it yourself.<p>
     * If your verticle does things in it's startup which take some time then you can override this method
     * and call the startFuture some time later when start up is complete.
     *
     * @param startFuture a future which should be called when verticle start-up is complete.
     * @throws Exception
     */
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        System.out.println("myverticle started");

    }

    /**
     * Stop the verticle.<p>
     * This is called by Vert.x when the verticle instance is un-deployed. Don't call it yourself.<p>
     * If your verticle does things in it's shut-down which take some time then you can override this method
     * and call the stopFuture some time later when clean-up is complete.
     *
     * @param stopFuture a future which should be called when verticle clean-up is complete.
     * @throws Exception
     */
    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        System.out.println("myverticle started");
    }




}
