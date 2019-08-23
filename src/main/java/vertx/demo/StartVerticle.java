package vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class StartVerticle extends AbstractVerticle {

    public static void main(String[] args) {


        DeploymentOptions options = new DeploymentOptions().setInstances(2);

        // 获取vertx基类
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));


        // 部署发布服务
        vertx.deployVerticle("vertx.demo.RestfulVerticle");
        vertx.deployVerticle("vertx.demo.EventBusConsumer1");
        vertx.deployVerticle("vertx.demo.EventBusConsumer2");

        vertx.deployVerticle("vertx.demo.BlockVerticle");


        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(vertx.deploymentIDs().getClass());

    }

}
