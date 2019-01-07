package org.gooru.nucleus.handlers.lookup.app.components;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.gooru.nucleus.handlers.lookup.bootstrap.shutdown.Finalizer;
import org.gooru.nucleus.handlers.lookup.bootstrap.startup.Initializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
// import com.google.firebase.auth.FirebaseCredentials;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class FirebaseInitializer implements Initializer, Finalizer {

  private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseInitializer.class);
  private volatile boolean initialized = false;

  private FirebaseInitializer() {
    // TODO Auto-generated constructor stub
  }

  public static FirebaseInitializer getInstance() {
    return Holder.FIREBASEINSTANCE;
  }

  @Override
  public void finalizeComponent() {
    // TODO Auto-generated method stub
  }

  @Override
  public void initializeComponent(Vertx vertx, JsonObject config) {
    // TODO Auto-generated method stub
    // Skip if we are already initialized
    LOGGER.debug("Initialization called upon.");
    if (!initialized) {
      LOGGER.debug("May have to do initialization");
      // We need to do initialization, however, we are running it via
      // verticle instance which is going to run in
      // multiple threads hence we need to be safe for this operation
      synchronized (Holder.FIREBASEINSTANCE) {
        LOGGER.debug("Will initialize after double checking");
        if (!initialized) {
          LOGGER.debug("Initializing now");
          try {

            String str = config.getJsonObject("firebase.config").toString();
            InputStream firebaseConfig = new ByteArrayInputStream(str.getBytes());

            FirebaseOptions options =
                new FirebaseOptions.Builder().setServiceAccount(firebaseConfig)// "knowledgeHub-service/googleServiceKeyFirstChat.json"))
                    .setDatabaseUrl("https://firstproject-fd791.firebaseio.com").build();

            FirebaseApp.initializeApp(options);
            initialized = true;
          } catch (Exception e) {
            LOGGER.error(e.toString());
          }

        }
      }
    }

  }

  private static final class Holder {
    private static final FirebaseInitializer FIREBASEINSTANCE = new FirebaseInitializer();
  }

}
