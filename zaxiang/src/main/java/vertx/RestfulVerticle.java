package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * PROJECT_name: github
 * package: vertx
 * creat_user: zhangkun19
 * creat_date: 2019/08/22
 * creat_time: 21:56
 * describe: TODO
 **/
public class RestfulVerticle extends AbstractVerticle {

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
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        router.get("/get/:param1/:param2").handler(this::handleGet);
        router.route("/assert/*").handler(StaticHandler.create("assets"));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    }

    private void handleGet(RoutingContext routingContext) {

        String param1 = routingContext.request().getParam("param1");
        String param2 = routingContext.request().getParam("param2");

        if (isBlank(param1) ||isBlank(param2)){
            routingContext.response().setStatusCode(400).end();
        }
        JsonObject obj = new JsonObject();
        obj.put("method","get").put("param1",param1).put("param2",param2);

        routingContext.response().putHeader("content-type","application/json").end(obj.encodePrettily());
    }

    private boolean isBlank(String str) {
        if (str == null || "".equals(str))
            return true;
        return false;
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
