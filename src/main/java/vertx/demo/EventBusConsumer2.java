package vertx.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;


public class EventBusConsumer2 extends AbstractVerticle {


    @Override
    public void start() {
        EventBus();

    }

    public void EventBus() {
        EventBus eb = vertx.eventBus();

        MessageConsumer<String> consumer = eb.consumer("news.uk.sport");
        consumer.handler(message -> {
            System.out.println("2-I have received a message: " + message.body());
            message.reply("2-how interesting!");
        });

        consumer.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println("2-The handler registration has reached all nodes");
            } else {
                System.out.println("Registration failed!");
            }
        });

    }


}