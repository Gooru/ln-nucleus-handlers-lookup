package org.gooru.nucleus.handlers.lookup.bootstrap;

import org.gooru.nucleus.handlers.lookup.bootstrap.shutdown.Finalizer;
import org.gooru.nucleus.handlers.lookup.bootstrap.shutdown.Finalizers;
import org.gooru.nucleus.handlers.lookup.bootstrap.startup.Initializer;
import org.gooru.nucleus.handlers.lookup.bootstrap.startup.Initializers;
import org.gooru.nucleus.handlers.lookup.constants.MessagebusEndpoints;
import org.gooru.nucleus.handlers.lookup.processors.ProcessorBuilder;
import org.gooru.nucleus.handlers.lookup.processors.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

/**
 * Created by ashish on 25/12/15.
 */
public class LookupVerticle extends AbstractVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookupVerticle.class);

    @Override
    public void start(Future<Void> voidFuture) throws Exception {

        EventBus eb = vertx.eventBus();

        vertx.executeBlocking(blockingFuture -> {
            startApplication();
            blockingFuture.complete();
            LOGGER.debug("Application machinery startup successfully completed");
        }, startApplicationFuture -> {
            if (startApplicationFuture.succeeded()) {
                eb.consumer(MessagebusEndpoints.MBEP_LOOKUP, message -> vertx.executeBlocking(future -> {
                    MessageResponse result = ProcessorBuilder.build(message).process();
                    future.complete(result);
                }, res -> {
                    MessageResponse result = (MessageResponse) res.result();
                    message.reply(result.reply(), result.deliveryOptions());
                })).completionHandler(result -> {
                    if (result.succeeded()) {
                        LOGGER.info("Lookup end point ready to listen");
                        voidFuture.complete();
                    } else {
                        LOGGER.error("Error registering the lookup handler. Halting the Lookup machinery");
                        voidFuture.fail(result.cause());
                        Runtime.getRuntime().halt(1);
                    }
                });
            } else {
                voidFuture.fail("Not able to initialize the Lookup machinery properly");
            }
        });

    }

    @Override
    public void stop() throws Exception {
        shutDownApplication();
        super.stop();
    }

    private void startApplication() {
        Initializers initializers = new Initializers();
        try {
            for (Initializer initializer : initializers) {
                initializer.initializeComponent(vertx, config());
            }
        } catch (IllegalStateException ie) {
            LOGGER.error("Error initializing application", ie);
            Runtime.getRuntime().halt(1);
        }
    }

    private void shutDownApplication() {
        Finalizers finalizers = new Finalizers();
        for (Finalizer finalizer : finalizers) {
            finalizer.finalizeComponent();
        }

    }
}
